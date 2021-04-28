/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Competence;
import Models.Users;
import Services.ServiceCompetence;
import Services.ServiceUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.twilio.rest.serverless.v1.service.environment.Variable;
import java.awt.image.BufferedImage;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.io.FileUtils;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class SelfPController implements Initializable {
     @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private ImageView coverImage; 
    @FXML
    private ImageView profileImage;
    @FXML
    private AnchorPane profileImgPane;
    @FXML
    private Label nameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private VBox answersVBox;
      @FXML
    private VBox date;
    @FXML
    private TextArea aboutTextarea;
    @FXML
    private Text aboutText;
 Connection cnx;
    
     ServiceCompetence sc = new ServiceCompetence();
   

    private Users Users;
    String  path="C:\\wamp\\www\\uploads\\images\\";
    @FXML
    private ImageView changerC;
    @FXML
   
private AnchorPane profileImgPane2;
    @FXML
    private ImageView addcom;
    @FXML
    private AnchorPane couverture;
    @FXML
    private TableView<Competence> tvcomp;
    @FXML
    private TableColumn<Competence, String> coltitre;
    @FXML
    private TableColumn<Competence, String> coldomaine;
    @FXML
    private Label tfsexe;
    @FXML
    private Label tfage;
    @FXML
    private Label telelabel;
    @FXML
    private WebView webimg;
    @FXML
    private VBox answersVBox1;
    @FXML
    private TableView<?> table1;
    @FXML
    private TableColumn<?, ?> titre3tab;
    @FXML
    private TableColumn<?, ?> Secteurtab;
    @FXML
    private TableColumn<?, ?> periode3tab;
    @FXML
    private Button bt_suppP;
    @FXML
    private ScrollPane photoScrollPane;
    @FXML
    private VBox photosVBox;
    @FXML
    private Label employeur;
    @FXML
    private Label poste;
    @FXML
    private Label periode;
    @FXML
    private ImageView editex;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> employeurtab;
    @FXML
    private TableColumn<?, ?> postetab;
    @FXML
    private TableColumn<?, ?> periodetab;
    @FXML
    private Button bt_supp;
    @FXML
    private TextField txt_id;
         public ImageView getProfileImage() {
        return profileImage;
    }

  public void setProfileImage(ImageView profileImage) {
        this.profileImage = profileImage;
    }

    
 @Override
    public void initialize(URL url, ResourceBundle rb) {

 showcom();
    }
    
     private void populateFields(){
        try {
            
              //     String age = "" + ((new Date()).getYear() - fosuser.getBirth_date().getYear());
            //ageLabel.setText(age);    
            
         //   nameLabel.setText(Variable.getUsers().getFirstname() + " " + Variable.getUsers().getLastname());
          //  addressLabel.setText(Variable.getgetUsers().getPays());
           
//           bdLabel.setText(new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH).format(fosuser.getBirth_date()));
          //  aboutText.setText(Variable.getUsers().getDescription());
                     
      
        }catch (Exception ex){
  Logger.getLogger(SelfPController.class.getName()).log(Level.SEVERE, null, ex);        }
    }
    
    
    FileChooser fc = new FileChooser();
    File selectedFile;
     @FXML
    private void changerCover (MouseEvent event) throws SQLException, IOException {
       File dest=new File("C:\\wamp\\www\\uploads\\images");
        
        fc.setInitialDirectory(new File("C:\\"));
        selectedFile = fc.showOpenDialog(null);
        if(selectedFile!=null){
            
        FileUtils.copyFileToDirectory(selectedFile, dest);
        
        File newFile = new File("C:\\wamp\\www\\uploads\\images\\"+selectedFile.getName());
        
        FileInputStream input = new FileInputStream(newFile);
        
      ServiceUser   us = new ServiceUser();
        //  us.modifierPhotoCover(Variable.getUsers().getId(), selectedFile.getName());
           System.out.println("Photo de couverture");
           
           File file2 = new File("C:\\wamp\\www\\uploads\\images\\"+selectedFile.getName());
        Image image = new Image(file2.toURI().toString());
        Image image3 = new Image(file2.toURI().toString());
            
            coverImage.setImage(image3);
       }        
        
       }   
    
   
     
    
     @FXML
    private void getSelectedExperience(MouseEvent event) throws SQLException, IOException {
       
        
    }
    
     
       @FXML
    private void editAbout(MouseEvent event) {
       
        aboutTextarea.setText(aboutText.getText());
        aboutText.setVisible(false);
        aboutTextarea.setVisible(true);
        aboutTextarea.requestFocus();
    }
       
    
    
  private void updateAbout(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue){
        if(!newPropertyValue){
            aboutTextarea.setVisible(false);
            aboutText.setVisible(true);
            populateFields();
        }
    }
 


    @FXML
    private void showProfilePic(MouseEvent event) {
       
    }
      private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
       alert.show();
    }


    @FXML
    private void modifierCompetence(MouseEvent event) throws IOException {
        
      Parent globalPane = FXMLLoader.load(getClass().getResource("/GUI/Modifier Competence.fxml"));
        Scene scene = new Scene(globalPane);
        Stage mainStage=new Stage();
        mainStage.setScene(scene);
        mainStage.show();      
    }

    @FXML
    private void AjoutCompetence(MouseEvent event) throws IOException {
        Parent globalPane = FXMLLoader.load(getClass().getResource("/GUI/Ajout Competence.fxml"));
        Scene scene = new Scene(globalPane);
        Stage mainStage=new Stage();
        mainStage.setScene(scene);
        mainStage.show();  
 
    }

    @FXML
    private void Edit(ActionEvent event) throws IOException {
        
    }

    @FXML
    private void delete(MouseEvent event) throws SQLException {
        
         Competence tab1 = tvcomp.getSelectionModel().getSelectedItem();
                String querry = "DELETE FROM competence WHERE id = " + 	tab1.getId();

            cnx= MyConnection.getInstance().getConnection();
                PreparedStatement ps = cnx.prepareStatement(querry);
                ps.execute();
                System.out.println("competence supprimé avec succés  !!!");
    }

    private void showcom() {
        
      ObservableList<Competence> Competence = showcomp();
     
      coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
      coldomaine.setCellValueFactory(new PropertyValueFactory<>("domaine"));
     
      
    tvcomp.setItems(Competence);
}
 private ObservableList<Competence> showcomp() {
        
        ObservableList<Competence> Comp =FXCollections. observableArrayList();
      try {
        String query = "select * from Competence";
        
	 Statement st = MyConnection.getInstance().getConnection()
                    .createStatement();
            ResultSet rs =  st.executeQuery(query);
	
	while (rs.next()) {
	    Competence ex =new Competence();
	  
	    ex.setTitre(rs.getString("titre"));
            ex.setDomaine(rs.getString("domaine"));
           
	    Comp.add(ex);
        }
       
	 } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	return Comp;
        }
   


    /**
     * Initializes the controller class.
     */
   
 /*
       @FXML
    private void Afficher() {
        ObservableList<Competence> comp =FXCollections. observableArrayList();
      try {
        String requete = "SELECT titre,domaine FROM competence";
            Statement st = new MyConnection ().getConnection()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Competence a = new Competence();
                a.setTitre(rs.getString("titre"));
                 a.setDomaine(rs.getString("domaine"));
               

                comp.add(a);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println( comp );
        //return annonce;
        fxlist.setItems(comp);
       // fxlist.setOrientation(Orientation.HORIZONTAL);
       // fxlist.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
 
       //fxlist.getSelectionModel().selectIndices(1, 2);
        // fxlist.setOrientation(Orientation.HORIZONTAL);
        //
       // tvannonce.setItems(annonce);
     }
*/
 
  void setlist() {     



        }

    @FXML
    private void AjouterPic(ActionEvent event) {
       try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("/GUI/WebCamPreview.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(tableViewScene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SelfPController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    

    public BufferedImage buffImg;
  public void setImg(BufferedImage imgg) throws IOException {

        buffImg = imgg;
        Image image = SwingFXUtils.toFXImage(imgg, null);
        profileImage.setImage(image);
        profileImage.setVisible(true);
        profileImage.toFront();
        
        webimg.setVisible(false);
        // cloudinary.uploader().upload("my_picture.jpg", ObjectUtils.emptyMap());
        System.err.println(imgg);
//changepic.fire();

    }
    @FXML
    private void AjouterPic(MouseEvent event) {
    }

    @FXML
    private void getSelectedProjet(MouseEvent event) {
    }

    @FXML
    private void supprimerProjet(ActionEvent event) {
    }

    @FXML
    private void modifierProjet(MouseEvent event) {
    }

    @FXML
    private void affiche(MouseEvent event) {
     ObservableList<Competence> Competence = showcomp();
     
     coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
     coldomaine.setCellValueFactory(new PropertyValueFactory<>("domaine"));
     
      
    tvcomp.setItems(Competence);
    }

    @FXML
    private void modifierExperience(MouseEvent event) {
    }

    @FXML
    private void supprimerExperience(ActionEvent event) {
    }

    @FXML
    private void modifierExperience2(MouseEvent event) {
    }
}