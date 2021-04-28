/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer_pi;

import entities.Annonce;
import entities.postuler;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import services.Annonceservice;
import services.postuleservice;

/**
 * FXML Controller class
 *
 * @author siwar
 */
public class ConsulterannonceController implements Initializable {
    int id;
    private Scene s;

    @FXML
    private TextField nomfx;
    @FXML
    private TextField prenomfx;
    @FXML
    private TextField emailfx;
    @FXML
    private TextField telephonefx;
    @FXML
    private TextField cvfx;
    @FXML
    private TextField messagefx;
    @FXML
    private AnchorPane nom;
    @FXML
    private Label no;
    @FXML
    private Label descrition;
    @FXML
    private Label date;
    @FXML
    private Label lieux;
    @FXML
    private Label renu;
    @FXML
    private TextField cvfx1;
   
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
           
    @FXML
    private void postuleraction(ActionEvent event) {
        //stage.setTitle("File Chooser Sample");
 
 
        
         postuleservice ps = new postuleservice();
        postuler p = new postuler();
        p.setNom(nomfx.getText());
        p.setPrenom(prenomfx.getText());
        p.setTelephone(telephonefx.getText());
        p.setEmail(emailfx.getText());
        p.setMessage(messagefx.getText());
        p.setCv(cvfx1.getText());
        p.setAnnonce_id(4);
        ps.addpostuler(p);
       
    }
    public void intData(int ida, Scene label) {
        try {
            Annonceservice es = new Annonceservice();
            this.id = ida;
          Annonce a = es.getannoncebyid(ida);
            
            no.setText(a.getNom());
            descrition.setText(a.getDescription());
            date.setText(a.getDate().toString());
            lieux.setText(a.getLieux());
            renu.setText(a.getRenumeration());
            
            s = label;
           
        
           /* likes.setText(es.getLikes(idE) + "");
            dislikes.setText(es.getDisikes(idE) + "");
            membreCom.setCellValueFactory(new PropertyValueFactory<CommentaireReact, String>("nomMembre"));
            commentaire.setCellValueFactory(new PropertyValueFactory<CommentaireReact, String>("commentaire"));
            createQR(e.getDescription());
            initTable();*/
        } catch (Exception  ex) {
            Logger.getLogger(ConsulterannonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void choose(ActionEvent event) throws FileNotFoundException, IOException {
         FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Open Resource File");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("pdf Files", "*.pdf"));
        File selectedFile = fileChooser.showOpenDialog(null);
        
        if (selectedFile != null) {
            /*BufferedImage bufferedImage = ImageIO.read(selectedFile);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);*/
            //this.image.setText(selectedFile.getName());
            this.cvfx1.setText(selectedFile.toURI().toURL().toString());
           // imview.setImage(image);
        }
        
        
        
 
        
    }

    
}
