/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Crypt;
import Models.MailVerification;
import Models.SendMail;
import Models.SendMessage;
import Models.Users;
import Models.mailing;
import Services.ServiceUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.cloudinary.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 * FXML Controller class
 *
 * @author asus
 */
public class InscriptionController implements Initializable {

    @FXML
    private AnchorPane tftel;
    @FXML
    private JFXRadioButton male;
    @FXML
    private JFXRadioButton female;
    @FXML
    private JFXButton send;
    @FXML
    private JFXTextField tfage;
    @FXML
    private JFXTextField tfnom;
    @FXML
    private JFXTextField tfprenom;
    @FXML
    private JFXTextField tfemail;
    @FXML
    private JFXPasswordField tfpassword;
    @FXML
    private JFXTextField tfpays;
    @FXML
    private JFXTextField tftele;
    @FXML
    private JFXComboBox<String> role;
      boolean valid = true;
   ObservableList<String> list = FXCollections.observableArrayList("Freelancer","Entreprise");
    @FXML
    private Label labelprenom;
    @FXML
    private Label labeltel;
    @FXML
    private Label labelage;
    @FXML
    private Label labelemail;
    private Label labelmdp;
    @FXML
    private Label labelpays;
    @FXML
    private Label labelps;
    @FXML
    private Label labelrepass;
    @FXML
    private Label labeldate;
    @FXML
    private Label labelnom;
    @FXML
    private Label labelpassword;
    @FXML
    private Label labelrole;
    @FXML
    private Label labelsexe;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      //  role.getItems().("admin","Freelancer","Entreprise");
       role.setItems(list);
       
       
        
    }    

    @FXML
    private void AjouterUser(ActionEvent event) throws IOException {
        
        
    
      ServiceUser su=new ServiceUser();
        Users u =new Users();
           boolean valid = true;

           if (tfnom.getText().equals("")) {
            labelnom.setText("Le champ est vide !");
            labelnom.setVisible(true);
            valid = false;
        } else {
            labelnom.setText("");
        }

        if (tfprenom.getText().equals("")) {
            labelprenom.setText("Le champ est vide !");
            labelprenom.setVisible(true);
            valid = false;
        } else {
            labelprenom.setText("");
        }

        if (tftele.getText().equals("")) {
            labeltel.setText("Le champ est vide !");
            labeltel.setVisible(true);
            valid = false;
        } else {
            labeltel.setText("");
        }
        if (tfemail.getText().equals("")) {
            labelemail.setText("Le champ est vide !");
            labelemail.setVisible(true);
            valid = false;
        }

        if (!MailVerification.validate(tfemail.getText())) {
            labelemail.setText("E-mail n'est pas valide !");
            labelemail.setVisible(true);
            valid = false;
        } else {
            labelemail.setText("");
        }

        if (tfpassword.getText().equals("")) {
            labelpassword.setText("Le champ est vide !");
            labelpassword.setVisible(true);
            valid = false;
        
        }


        if (tfpays.getText().equals("") ){
            labelpays.setText("Le champ est vide!");
            labelpays.setVisible(true);
            valid = false;
        }
         if (tfage.getText().equals("") ){
            labelage.setText("Le champ est vide!");
            labelage.setVisible(true);
            valid = false;
        }
        if (role.getValue() == null) {
            labelrole.setText("Le champ est vide!");
            labelrole.setVisible(true);
            valid = false;
        }
        if(!valid) return;
        
        u.setNom(tfnom.getText());
        u.setPrenom(tfprenom.getText());
         u.setTel(Integer.parseInt(tftele.getText()));
         
        u.setEmail(tfemail.getText());
        u.setpassword(Crypt.getMd5(tfpassword.getText()));
        u.setPays(tfpays.getText());
         u.setAge(Integer.parseInt(tfage.getText()));
        
          u.setSexe(female.getText());
         u.setSexe(male.getText());
         u.setRole(role.getValue());
          su.AddUser(u);
        SendMail sm = new SendMail(u.getEmail(), " Confirmation d'inscription ", " Bonjour " + u.getPrenom() + " Felicitations! Vous etes maintenant inscrit à Tunlancer");
        System.out.println(tftele.getText());
          
        TrayNotification tray = new TrayNotification("Inscritpion avec succès ", "Vous pouvez rejoindre nous !", NotificationType.SUCCESS);
         System.out.println(tftele.getText());
            SendMessage s = new SendMessage();
            s.sendSms("Felicitations! Vous etes Maintenant Inscrit à Tunlancer", tftele.getText());
             System.out.println("msg envoyé");       
             // code api sms sender
          NexmoClient client = new NexmoClient.Builder()
                    .apiKey("1a851005") //api key  
                    .apiSecret("uwnS6XCLYKjFGZ8U") //api secret 
                    .build();
            
            String messageText = "Felicitations! Vous etes Maintenant Inscrit à Tunlancer ";
            TextMessage message = new TextMessage("Tunlancer", "216"+tftele.getText(), messageText);
            
            SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);
            System.out.println("Message Reçu");
            for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
                System.out.println(responseMessage);
            }
            tray.showAndDismiss(Duration.seconds(5));
            send.getScene().getWindow().hide();
            Parent globalPane = FXMLLoader.load(getClass().getResource("/GUI/login.fxml"));
            Scene scene = new Scene(globalPane);
            Stage mainStage=new Stage();
             mainStage.setScene(scene);
             mainStage.show();
   
     }

          


    @FXML
    private void typeMail(KeyEvent event) {
        
    }

    @FXML
    private void fbAuth(MouseEvent event) {
        
        String appId = "640048036409356";
        String appSecretKey = "0505c4fd73ee51c2dbfb45872581dbcf";
        String domain = "http://www.facebook.com";
        String code="";
        String userAccessToken="";
        
        //permission pour email
        ScopeBuilder scopeBuilder = new ScopeBuilder();
        scopeBuilder.addPermission(FacebookPermissions.EMAIL);
       
        scopeBuilder.addPermission(FacebookPermissions.USER_BIRTHDAY);
        scopeBuilder.addPermission(FacebookPermissions.PUBLIC_PROFILE);
        

       
        FacebookClient client = new DefaultFacebookClient(Version.LATEST);
        String loginDialogUrlString = client.getLoginDialogUrl(appId, domain, scopeBuilder);

        //chome driver
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(loginDialogUrlString);

        while (true) {

            if (driver.getTitle().contains("localhost")) {
                String url = driver.getCurrentUrl();
                if (url.contains("code=") && "".equals(code)) {
                    String[] parts = url.split("code=");
                    String part1 = parts[0];
                    String codeToken = parts[1];
                    code = codeToken;
                    System.out.println(codeToken);

                    try {
                     userAccessToken = InscriptionController.call_me(appId, domain, appSecretKey, codeToken);

                        FacebookClient facebookClient
                                = new DefaultFacebookClient(userAccessToken, appSecretKey, Version.LATEST);
                        User user = facebookClient.fetchObject("me", User.class, Parameter.with("fields", "id,about,email,birthday,name,picture{url},first_name,last_name"));

                 System.out.print("user :" + user.getName() + user.getBirthday() + user.getEmail());

                        
                        
                       
                    //    f = new File("C:\\wamp64\\www\\pidev\\profileimages\\"+user.getEmail()+".png");
                        //f = new File("C:\\wamp64\\www\\pidev\\"+imageName+".png");
                      
                        
                        
                        Users users = new Users();
                        users.setNom(users.getNom());
                        users.setPrenom(users.getPrenom());
                        users.setId(users.getId());
                        users.setEmail(users.getEmail());
                        users.setpassword(users.getPassword());                        
                        System.out.println(users.getNom());
                        System.out.println(users.getPrenom());
                        System.out.println(users.getEmail());
                        System.out.println(users.getPassword());
                        System.out.println(users.getId());
                        tfnom.setText(users.getNom());
                        tfprenom.setText(users.getPrenom());
                        tfemail.setText(users.getEmail());
//                       
                        Parent page = FXMLLoader.load(getClass().getResource("/GUI/Inscription.fxml"));
                        Scene scene = new Scene(page);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.hide();
                        stage.setScene(scene);
                        stage.show();
                        
                        driver.quit();
                        return;

                        
                       

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }

        }

    }

    


 public static String call_me (String appId, String redirectUrl, String appSecret, String code) throws Exception {
        String url = "https://graph.facebook.com/v2.12/oauth/access_token?"+"client_id=" + appId+"&redirect_uri=" + redirectUrl+"&client_secret=" + appSecret+"&code=" + code;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        //System.out.println("\nSending 'GET' request to URL : " + url);
        //System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print in String
        //System.out.println(response.toString());

        //Read JSON response and print
        JSONObject myResponse = new JSONObject(response.toString());
        System.out.println(myResponse.getString("access_token"));
        
        return(myResponse.getString("access_token"));
        //System.out.println("result after Reading JSON Response");
        //System.out.println("origin- "+myResponse.getString("origin"));

    }}

/*
 FileChooser fc = new FileChooser();
        File selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
            getImageUrl = selectedfile.getAbsolutePath();
            System.out.println("s " + selectedfile);
            File file = new File(getImageUrl);
            Image ima = new Image(file.toURI().toString());
            System.out.println(getImageUrl);
            int fileNameIndex = getImageUrl.lastIndexOf("\\") + 1;

            nomimage = getImageUrl.substring(fileNameIndex);
            File dest = new File("C:\\wamp\\www\\affiches\\" + nomimage);
            System.out.println("hello" + nomimage);
            copyFileUsingStream(selectedfile, dest);
        } else {
            System.out.println("file does not exist");
        }
*/