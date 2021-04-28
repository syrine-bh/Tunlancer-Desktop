/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.forum.gui;

import Models.Replies;
import Models.Topics;
import Service.ServiceReplies;
import Service.ServiceTopics;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author cyrinaa belguith
 */
public class DisplayRepliesController implements Initializable {

    @FXML
    private ImageView avatar;
    @FXML
    private Label Ltitre;
    @FXML
    private Label Lcontenu;
    @FXML
    private Label Ldate;
    @FXML
    private Label date;
    @FXML
    private FontAwesomeIconView EditTopic;
    @FXML
    private FontAwesomeIconView deletebtnTopic;
    @FXML
    private Label titre;
    @FXML
    private TextArea contenu;
    @FXML
    private VBox vbox;
    @FXML
    private JFXListView<Replies> listCom;
    private static Topics to = new Topics();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void AfficherTopicDet(Topics t) {
        ServiceTopics st = new ServiceTopics();
        t = st.AfficherTopicDetails(t.getId());
        to = t;
        titre.setText(t.getTitre());
        contenu.setText(t.getContenu());
    }

    public void showReplies() {
        ServiceReplies sr = new ServiceReplies();
        ObservableList<Replies> TopicsList = sr.AfficherReplies(getTopicsId());
        listCom.setCellFactory((Callback<ListView<Replies>, ListCell<Replies>>) param -> {
            return new ListCell<Replies>() {
                @Override
                protected void updateItem(Replies r, boolean empty) {
                    super.updateItem(r, empty);

                    if (r == null || empty) {
                        setText(null);
                    } else {

                        Label contenu = new Label(String.valueOf(r.getContenu()) + "\n");

                        HBox Hbx = new HBox(100);

                        Hbx.getChildren().addAll(contenu);
                        setText(null);
                    }

                }
            };
        });
        listCom.setItems(TopicsList);
        vbox.getChildren().add(listCom);
    }

    public int getTopicsId() {
        TopicsHolder holder = TopicsHolder.getInstance();
        int t = holder.getId();
        return t;
    }

    @FXML
    private void AjouterRep(MouseEvent event) {

    }

//    private int getTopicsId() {
//        Topics topic1 = Topics.getInstance();
//        int t = topic1.getTopic_id();
//        return t;
//    }
}
