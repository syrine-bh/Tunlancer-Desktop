/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev2;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.input.MouseEvent;
/**
 *
 * @author Hiba
 */
public class Pidev2 extends Application{
double xOffset,yOffset;
    
    public void start(Stage stage) throws Exception {    
//        Parent root = FXMLLoader.load(getClass().getResource("/HomePageHolder.fxml"));
   Parent root = FXMLLoader.load(getClass().getResource("/GUI/login.fxml"));
//   Parent root = FXMLLoader.load(getClass().getResource("/FXML/Front/concourVList.fxml"));
//   Parent root = FXMLLoader.load(getClass().getResource("/concoursV.fxml"));
////        Parent root = FXMLLoader.load(getClass().getResource("/ConcourV_Participate.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Back/concours/AdminPageHolder.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/ConcoursItem.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/GridConcours.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/DisplayConcoursPage.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/concoursDetails.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/afficheQuiz.fxml"));
//        Pare
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
            stage.setOpacity(0.85f);
        });
        root.setOnMouseReleased((MouseEvent event) -> {
            stage.setOpacity(1.0f);
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
