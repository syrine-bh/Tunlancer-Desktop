 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Competence;
import Models.Variable;
import Services.ServiceCompetence;
import com.jfoenix.controls.JFXMasonryPane;
import static java.lang.Math.exp;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class OtherProfileController implements Initializable {

    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private AnchorPane couverture;
    @FXML
    private ImageView coverImage;
    @FXML
    private Label nameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private AnchorPane profileImgPane;
    @FXML
    private ImageView changerC;
    @FXML
    private AnchorPane profileImgPane2;
    @FXML
    private ImageView profileImage;
    @FXML
    private TextArea aboutTextarea;
    @FXML
    private Text aboutText;
    @FXML
    private Label tfsexe;
    @FXML
    private Label tfage;
    @FXML
    private VBox date;
    @FXML
    private Label telelabel;
    @FXML
    private VBox answersVBox;
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
    private TextField mtitrep;
    @FXML
    private TextField msecteurp;
    @FXML
    private TextField mperiodep;
   
 
    private VBox profile3;
    private List <Competence> listc;
    @FXML
    private ScrollPane photoScrollPane1;
    @FXML
    private VBox photosVBox1;
    @FXML
    private Label employeur11;
    @FXML
    private Label poste11;
    @FXML
    private Label periode11;
    @FXML
    private JFXMasonryPane listEvenements1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
   
    @FXML
    private void showProfilePic(MouseEvent event) {
    }

    @FXML
    private void Edit(ActionEvent event) {
    }

    @FXML
    private void AjouterPic(ActionEvent event) {
    }

    @FXML
    private void changerCover(MouseEvent event) {
    }

    @FXML
    private void AjouterPic(MouseEvent event) {
    }

    @FXML
    private void editAbout(MouseEvent event) {
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
    
    private void affichecompetence() {
         {
               try {
            ServiceCompetence ExService = new ServiceCompetence();
           // listc = ServiceCompetence.getInstance().findAll(Variable.getUsers().getId());
           
            listc.forEach(i->{
             VBox v = new VBox();
             v.getStylesheets().add("@style/ProfileStyleSheet.css");
             v.getStyleClass().add("paragraph_container");
            v.setMinSize(280,147);
            v.setMaxSize(380,147);
            v.spacingProperty();
            v.setPadding(new Insets(13, 10, 10, 14));
            v.setSpacing(8);
            //v.setAlignment(Pos.CENTER);
            Label id = new Label("Id: "+i.getId()); 
            id.setStyle("-fx-text-fill: #323030;");
           
            id.setFont(new Font("System ", 14));
		
            Label emp = new Label("Titre: "+i.getTitre()); 
            emp.setStyle("-fx-text-fill: #323030;");
           
            emp.setFont(new Font("System ", 14));
            Label poste2 = new Label("Categorie:"+i.getDomaine() );
            poste2.setStyle("-fx-text-fill: #323030;");
            poste2.setFont(new Font("System ", 14));
            
          //  v.getChildren().addAll(emp,poste2,exp);
            profile3.getChildren().add(v);
            profile3.setSpacing(8.0);});		
        } catch (Exception ex) {
         Logger.getLogger(OtherProfileController.class.getName()).log(Level.SEVERE, null, ex);        }
         }}
    
}
