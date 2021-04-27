/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Front.Concours;

import Service.ParticipationServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import entities.Concour;
import entities.Video;
import entities.Vote;
import utils.UserSession;
/**
 * FXML Controller class
 *
 * @author Anis
 */
public class ConcourVListController {

    /**
     * Initializes the controller class.
     */
    public static Timeline oneSecond;
    @FXML
    private ScrollPane scroll;
//    private Concour c;
    @FXML
    private AnchorPane video_list;
    @FXML
    private GridPane video_grid;
    @FXML
    private Label comp;
    @FXML
    private Label time;
    @FXML
    private Label ranks;
    @FXML
    private JFXComboBox<String> orderCombo;
    ObservableList<Video> ordered;
    VoteServices vs = new VoteServices();
    ParticipationServices ps = new ParticipationServices();
     ServiceConcours st = new ServiceConcours();
 
    Concour c;
        concoursHolder th = concoursHolder.getINSTANCE();


    public void initialize() {
                c = st.getConcours(th.getId());
        System.out.println(c);
        Platform.runLater(() -> {
           
            comp.setText(c.getSujet());

            ObservableList<Video> tabs = ps.getAll(c);

            updateRanks();
            if (c.getDateFin().before(new Timestamp(System.currentTimeMillis()))) {
                time.setText("Competition is Over");

            } else {
                 oneSecond = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                Timestamp t = new Timestamp(System.currentTimeMillis());
                long counter;
                     {
                         this.counter = getDateDiff(t, c.getDateFin(), TimeUnit.SECONDS);
                     }
                  
                @Override
                public void handle(ActionEvent event) {

                    long h = TimeUnit.SECONDS.toHours(counter);
                    long m = TimeUnit.SECONDS.toMinutes(counter - h * 3600);
                    long s = TimeUnit.SECONDS.toSeconds(counter - h * 3600 - m * 60);
                    counter = counter - 1;
                    time.setText(String.format("%02d:%02d:%02d", h,m,s));

                }
            }));
            oneSecond.setCycleCount(60);
            oneSecond.play();
                String order[]
                        = {"Votes", "Newest"};
                orderCombo.setItems(FXCollections.observableArrayList(order));

                orderCombo.setOnAction(event);
                NotOver(tabs);
            }

        });

    }

    public static long getDateDiff(Timestamp oldTs, Timestamp newTs, TimeUnit timeUnit) {
        long diffInMS = newTs.getTime() - oldTs.getTime();
        return timeUnit.convert(diffInMS, TimeUnit.MILLISECONDS);
    }

    public void NotOver(ObservableList<Video> tabs) {

        ParticipationServices ps = new ParticipationServices();
        VoteServices vs = new VoteServices();
        tabs.forEach((vid) -> {

            GridPane details = new GridPane();

            Label Title = new Label(vid.getTitle());
            Title.setTextFill(javafx.scene.paint.Color.BLACK);
            Title.setFont(Font.font("Cambria", 22));

            Label user = new Label(vid.getOwner().getNom());
            user.setTextFill(javafx.scene.paint.Color.BLACK);
            user.setFont(Font.font("Cambria", 18));

            Label VoteNum = new Label(Integer.toString(vs.getVotes(vid)));
            VoteNum.setTextFill(javafx.scene.paint.Color.BLACK);
            VoteNum.setFont(Font.font("Cambria", 24));

            Label pubDate = new Label(vid.getPublish_date().toString());
            pubDate.setTextFill(javafx.scene.paint.Color.BLACK);
            pubDate.setFont(Font.font("Cambria", 16));

            JFXButton Delete = new JFXButton("Delete");
            JFXButton Vote = new JFXButton("Vote");
            JFXButton Unvote = new JFXButton("Unvote");

            Delete.resize(150, 250);
            Vote.resize(150, 250);
            Unvote.resize(150, 250);

            Delete.setStyle("-fx-text-fill: white;-fx-font-size:18px;-fx-background-color:#49111C");
            Vote.setStyle("-fx-text-fill: white;-fx-font-size:18px;-fx-background-color:#ACEB98");
            Unvote.setStyle("-fx-text-fill: white;-fx-font-size:18px;-fx-background-color:#92140C");

            WebView preview = new WebView();
            preview.getEngine().load(vid.getUrl());

            UserSession s = UserSession.instance;
            if (s.getU().getNom().equals(vid.getOwner().getNom())) {

                details.add(Delete, 2, 3);
            }
            if (vs.find(vid, s.getU())) {
                Unvote.setVisible(true);
                Vote.setVisible(false);
            } else {
                Unvote.setVisible(false);
                Vote.setVisible(true);
            }
            Vote.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    vs.Add(s.getU(), vid);
                    Unvote.setVisible(true);
                    Vote.setVisible(false);
                    VoteNum.setText(Integer.toString(Integer.parseInt(VoteNum.getText()) + 1));
                    updateRanks();

                }
            });
            Unvote.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    vs.delete(vid, s.getU());
                    Unvote.setVisible(false);
                    Vote.setVisible(true);
                    VoteNum.setText(Integer.toString(Integer.parseInt(VoteNum.getText()) - 1));
                    updateRanks();
                }
            });
            Delete.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Delete Participation");
                    alert.setHeaderText("Are you sure ?");
                    alert.setContentText("You will lose all your votes !");

                    alert.showAndWait();

                    video_grid.getChildren().removeAll(preview, details);
                    video_grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == tabs.indexOf(vid));
                }
            });
            details.add(Unvote, 3, 3);
            details.add(VoteNum, 4, 4);
            details.add(Vote, 3, 3);
            details.add(Title, 1, 0);
            details.add(user, 1, 1);
            details.add(pubDate, 1, 2);
            details.setStyle("-fx-background-color: #0c0527");
            details.setPadding(new Insets(15, 15, 15, 15));
            RowConstraints row = new RowConstraints(200);

            video_grid.getRowConstraints().add(row);

            video_grid.addRow(tabs.indexOf(vid), preview, details);

        });
        video_grid.setPadding(new Insets(10, 10, 10, 10));

        video_grid.setVgap(20);

    }

    public void updateRanks() {
        VoteServices vs = new VoteServices();
        String rank = "";
        int i = 1;
        Iterator<Entry<Video, Integer>> it = vs.ranks(c).entrySet().iterator();
        while (it.hasNext() && i < 4) {
            Map.Entry<Video, Integer> entry = (Map.Entry<Video, Integer>) it.next();
            rank = rank + "RANK " + i + ": \n" + entry.getKey().getOwner().getNom() + " " + entry.getValue() + " Votes\n";
            i++;
        }

        ranks.setText(rank);

    }
    EventHandler<ActionEvent> event
            = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
            if (orderCombo.getValue().equals("Votes")) {

                ordered = FXCollections.observableArrayList(vs.ranks(c).keySet());
                ps.getAll(c).forEach((vid) -> {
                    if (vs.getVotes(vid) == 0) {
                        ordered.add(vid);
                    }
                });
                video_grid.getChildren().clear();
                NotOver(ordered);

            }
            else if(orderCombo.getValue().equals("Newest"))
            {
              video_grid.getChildren().clear();
                NotOver(ps.getAllOrdered(c));
            }
        }
    };

    public void setConcour(Concour c) {
        this.c = c;
    }
}
