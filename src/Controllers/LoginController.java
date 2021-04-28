/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Crypt;
import static Models.Crypt.getMd5;
import Models.Users;
import Models.Variable;
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
import com.jfoenix.controls.JFXButton;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import java.io.File;
import javafx.scene.control.Alert;
import javax.imageio.ImageIO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


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
    @FXML
    private JFXButton admin;
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
            
              // System.out.println("Utilisateur:"+Variable.getUsers());
           Users user=new Users();
     
    
    }
   
    @FXML
    private void authentification(ActionEvent event) throws IOException {
              Connection cnx = MyConnection.getInstance().getConnection();
         // Users m = new Users();
           
         String sql ="select * from users where email= ? and password=?";
         try {
             PreparedStatement ps = cnx.prepareStatement(sql);
             ps.setString(1,email.getText());
             ps.setString(2,Crypt.getMd5(password.getText()));
              
             ResultSet rs =ps.executeQuery();
             if (rs.next()){
            JOptionPane.showMessageDialog(null,"email et password corrects"); 
               Users user=new Users();
//             user = new Users(rs.getInt("id"), rs.getString("Nom"),rs.getString("Prenom"), rs.getInt("tel"),rs.getString("email"), rs.getString("password"), rs.getString("pays"), rs.getString("role"), rs.getInt("age"), rs.getString("sexe"));
              TunlancerDesktop.getInstance().setLoggedUser(user);
           //  ConnexionBDJavafx.USER_ID = m.getId();
             login.getScene().getWindow().hide();
             
             Parent globalPane = FXMLLoader.load(getClass().getResource("/GUI/Profile.fxml"));
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

    private void goFb(MouseEvent event) {
        String appSecret = "6978d1ae59f8faa91610701759bfd6ec";
        String domain = "http://localhost";
        String appId = "2311995475747658";
 String auth = "http://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appId + "&redirect_uri=" + domain + "&scope=user_birthday,user_hometown,user_location,,user_likes,user_events,user_photos,user_videos,user_friends,user_status,user_tagged_places,user_posts,user_gender,user_link,user_age_range,email,read_insights,read_audience_network_insights,publish_video,manage_pages,pages_manage_cta,pages_manage_instant_articles,pages_show_list,publish_pages,read_page_mailboxes,ads_management,ads_read,business_management,pages_messaging,pages_messaging_phone_number,pages_messaging_subscriptions,instagram_basic,instagram_manage_comments,instagram_manage_insights,publish_to_groups,groups_access_member_info,leads_retrieval,public_profile";
        System.setProperty("Webdriver.chrome.driver", "C:/Users/asus/Desktop/chromedriver.exe");
        WebDriver dr1 = new ChromeDriver();
        String accessToken ="EAAg2v2UN40oBAKVI2MmbPXNhae1jCGvrMG0W35rAUz2eLcab1WqZANuaF1ZALjkxZAWbFKkjjVsYzNLw8QJe1omlk4qXqXNzMPXte3SybDqX56jOhy8SCT3RNDhjtjCibbcrfdmi61V6Rugi9Aed5P8vRIaz8S6oXp7wgIi3ZBJNIOJxWeVfZAruu8ELP37wgdaZB8hXwueZAu2ZBftwWgs6TAom2CZCQL0GZCQVmUu6bWZBJ6siNejl1WdSLPPfCbEMZCcZD";
    
        dr1.get(auth);
        while (true) {
            if (!dr1.getCurrentUrl().contains("www.facebook.com")) {

               // String url = dr1.getCurrentUrl();
                //accessToken = accessToken.substring(13,accessToken.lastIndexOf("&"));
                
              // accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");

               //FacebookClient fbClient = new DefaultFacebookClient(accessToken);
               // User user = fbClient.fetchObject("me", User.class);
               // System.out.println(" name: " + user.getName());
                
               // System.out.println(" Firstname: " + user.getFirstName());
               // System.out.println(" Email: " + user.getEmail());
               //  System.out.println(" picture: " + user.getPicture());
                if (dr1.getCurrentUrl().contains("localhost")) {
                    return;
                }

                dr1.quit();
                dr1 = null;
            }
        }
    }

    @FXML
    private void Admin(MouseEvent event) throws IOException {
        admin.getScene().getWindow().hide();
        Parent globalPane = FXMLLoader.load(getClass().getResource("/GUI/LoginAdminView.fxml"));
        Scene scene = new Scene(globalPane);
        Stage mainStage=new Stage();
        mainStage.setScene(scene);
        mainStage.show();
    }


    
    
}
