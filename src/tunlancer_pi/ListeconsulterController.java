/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer_pi;

import entities.Annonce;
import java.io.IOException;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import services.Annonceservice;
import javafx.fxml.FXMLLoader;

/**
 * FXML Controller class
 *
 * @author siwar
 */
public class ListeconsulterController implements Initializable {

    @FXML
    private HBox vHBox;
    @FXML
    private Label nlabel;
   
    private List<Annonce> listeann;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         Annonceservice es = new Annonceservice();
           listeann  = es.getbyid(19);
           listeann .forEach(ev -> {

            try {
                Pane paneEv = new Pane();

                 vHBox.setPadding(new Insets(20));
                vHBox.setSpacing(30);
                paneEv.setPrefWidth(200);
                paneEv.setPrefHeight(200);
               /* ImageView evImage = new ImageView(new Image(new File(ev.getImage()).toURI().toURL().toExternalForm()));
                Pane paneEv2 = new Pane();
                paneEv.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5);");
                System.out.println("im " + evImage.getImage());
                evImage.setFitWidth(200);
                evImage.setFitHeight(200);*/
                TextFlow textFlow = new TextFlow();
                textFlow.setPrefWidth(147);
                textFlow.setPrefHeight(150);
                textFlow.setLayoutX(5);
                textFlow.setLayoutY(230);
               // getListForEVDesc(es., textFlow);
                Label EvTitle = new Label(ev.getNom());
                EvTitle.getStyleClass().add("EventTitle");
                EvTitle.setPrefWidth(668);
                EvTitle.setPrefHeight(36);
                EvTitle.setLayoutX(5);
                EvTitle.setLayoutY(200);
                EvTitle.setStyle("-fx-text-fill:#ffffff");
               Button viewMore = new Button();
                viewMore.setText("View More");
                viewMore.getStyleClass().add("btnMore");
                viewMore.setStyle("-fx-font-size:20");
                viewMore.setStyle("-fx-text-fill:#ffffff");
                viewMore.setLayoutX(5);
                viewMore.setLayoutY(300);
                viewMore.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {

                        try {
                            FXMLLoader loader;
                            loader = new FXMLLoader(getClass().getResource("consulterannonce.fxml"));
                            Parent root = loader.load();
                            Scene s = new Scene(root);
                            ConsulterannonceController evController = loader.getController();
                            evController.intData(ev.getId(), viewMore.getScene());
                            Stage primaryStage = new Stage();
                            primaryStage.setScene(s);
                            primaryStage.show();
                        } catch (Exception ex) {
                            Logger.getLogger(ListeconsulterController .class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                paneEv.getChildren().addAll(textFlow, EvTitle, viewMore);
                vHBox.getChildren().add(paneEv);
            } catch (Exception ex) {
                Logger.getLogger(ListeconsulterController .class.getName()).log(Level.SEVERE, null, ex);
            }
        });
            
    }    
    
}
