/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer;

import Models.Users;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author siwar
 */
public class TunlancerDesktop extends Application {
    
     private Stage stage;
    private Users loggedUser;
    private static TunlancerDesktop instance;
    private Scene scene,pvsene;
    
    public TunlancerDesktop() throws IOException, InterruptedException {
        instance = this;
        loggedUser = null;
    }

   public static TunlancerDesktop getInstance() {
        return instance;
    }
    
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

     public void changescene(Scene scene) {
        this.scene = scene;
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
      public Users getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Users loggedUser) {
        this.loggedUser = loggedUser;
    }

    public Scene getPvsene() {
        return pvsene;
    }

    public void setPvsene() {
        this.pvsene = this.scene;
    }
    
}