
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Front.Concours;

import animatefx.animation.ZoomIn;
import entities.Concour;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import Service.Front.Concours.AfficheQuizController;

/**
 * FXML Controller class
 *
 * @author Hiba
 */
public class DetailsController implements Initializable {

    @FXML
    private ScrollPane ConcoursActionsPane;
    @FXML
    private Label concoursDescription;
    @FXML
    private Label concoursDate;
    @FXML
    private Label TaskNumDays;
    @FXML
    private ImageView concoursImg;
    @FXML
    private Label concoursTitle;
    @FXML
    private Button showScoresButton;
    @FXML
    private Button participationBtn;
    
     private ServiceConcours st = new ServiceConcours();
    
    Concour concours;
        concoursHolder th = concoursHolder.getINSTANCE();
    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane menuId;
    @FXML
    private Label typeConcours;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ConcoursActionsPane.setVvalue(0);
        new ZoomIn(ConcoursActionsPane).play();
        concours = st.getConcours(th.getId());
//        participateButton.setVisible(true);

        int y = 0;
        int x = 0;
        ServiceConcours sc = new ServiceConcours();
        List<Concour> concours = sc.ListConcours();
        System.out.println(concours);

        concoursTitle.setText(this.concours.getNom());
//            taskImg.setImage(pt.getImg().getImage());
System.out.println(this.concours.getId());
        concoursDescription.setText(this.concours.getDescription());
        typeConcours.setText(String.valueOf(this.concours.getIsVideo()));

        System.out.println("test lowel");
                System.out.println(String.valueOf(this.concours.getIsVideo()));


//            TaskNumDays.setText(String.valueOf(c.getNumOfDays()));
//            taskMinUsers.setText(String.valueOf(pt.getMinUsers()));
//            TaskMaxUsers.setText(String.valueOf(pt.getMaxUsers()));
//            String date = this.concours.getCreated_at().toLocalDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("dd")) +
//                    this.concours.getCreated_at().toLocalDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("MMM")) +
//                     this.concours.getCreated_at().toLocalDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("YY"));
//concoursDate.setText(java.sql.Date.valueOf(this.concours.getDateDebut() ));
//            concoursDate.setText(date);

    }
 private void addScreenToStackPane(Node node){
     this.stackPane.getChildren().add(node);
 }
        

    @FXML
    private void participerConcours(ActionEvent event) throws IOException {
                System.out.println("test 2");

        System.out.println(String.valueOf(this.concours.getIsVideo()));
if (this.typeConcours.getText() == "false"){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/afficheQuiz.fxml"));
        Node node = fxmlLoader.load();
        stackPane.getChildren().add(node);}
else{
     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/Front/concoursVusers2.fxml"));
        Node node = fxmlLoader.load();
        stackPane.getChildren().add(node);
}
 
}

    @FXML
    private void showScores(ActionEvent event) throws IOException {
        if (this.typeConcours.getText() == "false"){

           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/listScore.fxml"));
        Node node = fxmlLoader.load();
        stackPane.getChildren().add(node);
    }
        else{
     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/Front/concoursVusers2.fxml"));
        Node node = fxmlLoader.load();
        stackPane.getChildren().add(node);
}
}
}