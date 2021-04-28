


package Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import Models.Users;
import Services.ServiceUser;

import utils.MyConnection;


public class ConnexionBDJavafx extends Application {


    
    
     public static int USER_ID = 0;
   
    public static Stage mainStage;
    private static ConnexionBDJavafx instance;
        private double loginWidth, loginHeight;
    
     public static ConnexionBDJavafx getInstance(){
        return instance;
    }
    private static Scene scene;
    private static Stage window;
    
    
    public static void setScene(Parent root){
        scene.setRoot(root);
    }
     /*@Override
   public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Hello World");
        
        parentPage = FXMLLoader.load(getClass().getResource("/view/InsView.fxml"));
        Scene scene = new Scene(parentPage);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();

    }*/
    
     @Override
      public void start(Stage primaryStage) throws IOException {
        instance = this;
        mainStage = primaryStage;
      
        mainStage.setResizable(false);
        mainStage.setScene(scene);
        mainStage.initStyle(StageStyle.DECORATED);
        mainStage.show();
         
        mainStage.setOnCloseRequest(e -> {
            if(USER_ID == 0)
                return;
            Users user =(Users) ServiceUser.getInstance().get(new Users(USER_ID));
          
            USER_ID = 0;
        });}
      
      
         
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    public static void showAlert(Alert.AlertType alertType, String content, ButtonType... buttonType){
        Platform.runLater(() -> {
            Alert alert = new Alert(alertType, content, buttonType);
            alert.show();
        });
    }
  
    }