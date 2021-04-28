/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Cyrina
 */
public class TunlancerDesktop extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("/tunlancer/forum/gui/AllTopics.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/HomePageHolder.fxml"));
//       Parent root = FXMLLoader.load(getClass().getResource("/tunlancer/forum/gui/AdminPageHolder.fxml"));
               Parent root = FXMLLoader.load(getClass().getResource("/tunlancer/forum/gui/AllTopics.fxml"));

Stage stage= new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
