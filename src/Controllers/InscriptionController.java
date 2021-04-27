/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Crypt;
import Models.Users;
import Services.ServiceUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class InscriptionController implements Initializable {

    @FXML
    private Label labelfirst;
    @FXML
    private Label labelname;
    @FXML
    private Label labelps;
    @FXML
    private Label labelrepass;
    @FXML
    private Label labeldate;
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
   ObservableList<String> list = FXCollections.observableArrayList("Freelancer","Entreprise");
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
   
          
           TrayNotification tray = new TrayNotification("Inscritpion avec succès ", "Vous pouvez rejoindre nous !", NotificationType.SUCCESS);

            tray.showAndDismiss(Duration.seconds(5));
            send.getScene().getWindow().hide();
        Parent globalPane = FXMLLoader.load(getClass().getResource("/GUI/login.fxml"));
        Scene scene = new Scene(globalPane);
         Stage mainStage=new Stage();
        mainStage.setScene(scene);
        mainStage.show();
    }
    
}
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