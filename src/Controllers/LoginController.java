/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Crypt;
import static Models.Crypt.getMd5;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import Services.ServiceUser;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.util.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import Models.mailing;
import com.github.sarxos.webcam.Webcam;
import java.io.File;
import javax.imageio.ImageIO;


import tunlancer.TunlancerDesktop;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class LoginController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;
    @FXML
    private Label outputlogin;
    @FXML
    private Button login1;
    Stage stage;
    @FXML
    private Label frpassword;
    private JFXCheckBox showpassword;
    private JFXTextField visible;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceUser su = new ServiceUser();
       TrayNotification tray = new TrayNotification("Welcome To Tunlancer", "Please Login Or Create Your Account", NotificationType.SUCCESS);

            tray.showAndDismiss(Duration.seconds(3));
           
}
    @FXML
    private void authentification(ActionEvent event) throws IOException {
              Connection cnx = MyConnection.getInstance().getConnection();
    
         String sql ="select * from users where email= ? and password=?";
         try {
             PreparedStatement ps = cnx.prepareStatement(sql);
             ps.setString(1,email.getText());
             ps.setString(2,Crypt.getMd5(password.getText()));
              
             ResultSet rs =ps.executeQuery();
             if (rs.next()){
            JOptionPane.showMessageDialog(null,"email et password corrects"); 
             login.getScene().getWindow().hide();
             
             Parent globalPane = FXMLLoader.load(getClass().getResource("/GUI/Stat.fxml"));
        Scene scene = new Scene(globalPane);
        Stage mainStage=new Stage();
        mainStage.setScene(scene);
        mainStage.show();        
         } else 
            JOptionPane.showMessageDialog(null,"invalid email or password ");  
           
               Parent tableViewParent = FXMLLoader.load(getClass().getResource("/GUI/captcha.fxml"));
               Scene scene = new Scene(tableViewParent);
                Stage mainStage=new Stage();
               mainStage.setScene(scene);
                 mainStage.show();        
         }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);  
         }
    }

   

    @FXML
    private void recup_pass(MouseEvent event) throws IOException, InterruptedException {
        ServiceUser pcd = new ServiceUser();
        boolean ok = pcd.findUtilisateur(email.getText());
        if ("".equals(email.getText())){
            
            String tilte = "Reset password";
            String message = "Enter a mail address to reset ur password";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
        
            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
            
            
        }else if (ok == true){
            
            
        try {
            int code = (int) ((Math.random() * (9999 -1000 )) + 1000);
            System.out.println(code);
            
            mailing mail = new mailing();
            mail.sendMail(code, email.getText());
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/test.fxml"));
            Parent root = loader.load();          
            ResetPSWController dpc = loader.getController();
            
            dpc.setcode(Integer.toString(code));
            dpc.setmail(email.getText());
            
            email.getScene().setRoot(root);
          
        } catch (IOException ex) {
            Logger.getLogger(ResetPSWController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }else{
            String tilte = "Reset password";
            String message = "No such mail was found";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
        
            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        
        }
    }
   
    

    @FXML
    private void goInscrit(MouseEvent event) throws IOException {
        login1.getScene().getWindow().hide();
        Parent globalPane = FXMLLoader.load(getClass().getResource("/GUI/Inscription.fxml"));
        Scene scene = new Scene(globalPane);
         Stage mainStage=new Stage();
        mainStage.setScene(scene);
        mainStage.show();
    }

    private void passwordVisible(ActionEvent event) {
         if (showpassword.isSelected()){
                 visible.toFront();
            }else{
                password.toFront();
            } 
    }
    }

