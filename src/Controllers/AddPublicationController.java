/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Publication;
import Services.ServicePublication;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cyrina
 */
public class AddPublicationController implements Initializable {

    @FXML
    private TextField typeInput;
    @FXML
    private TextField localisationInput;
    @FXML
    private TextField descriptionInput;
    @FXML
    private TextField fichierInput;
    @FXML
    private ImageView addImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //addImage = new ImageView(new Image("..\\assets\\1.jpg"));
    }    

    @FXML
    private void BrowseAction(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser(); //outil eli nekhdhou bih el fichier
        final Stage stage = null;// el fenetre eli bech tethal 
        
        File file = fileChooser.showOpenDialog(stage); //halina el fenetre w recuperina el fichier
        if (file != null) { //ntestiow est ce que fichier null wale
            Image image1 = new Image(file.toURI().toString());
            addImage.setImage(image1);//badalna el image
            fichierInput.setText(file.getName()); //badalna el input
        }        
    }

    @FXML
    private void addPublicationAction(ActionEvent event) throws SQLException {
        
        if (typeInput.getText().isEmpty()) {
            System.out.println("veuillez remplir le champ type");
        }else if (!isNumeric(typeInput.getText())) {
            System.out.println("le type doit etre entier");
        }else if (localisationInput.getText().isEmpty()) {
            System.out.println("veuillez remplir le champ localisation");
        }else if (descriptionInput.getText().isEmpty()) {
            System.out.println("veuillez remplir le champ description");
        }else if (fichierInput.getText().isEmpty()) {
            System.out.println("veuillez remplir le champ fichier");
        }else{
            
            ServicePublication SP = new ServicePublication();
            Publication publication = new Publication();
            publication.setUtilisateur_id(1);
            publication.setArchive(0);
            publication.setType(Integer.parseInt(typeInput.getText()));
            publication.setLocalisation(localisationInput.getText());
            publication.setDescription(descriptionInput.getText());
            publication.setImage_name(fichierInput.getText());
            
            System.out.println("publication ready");
            SP.ajouterPublication(publication);
            System.out.println("added");
 
            
        }
    }
    
    
    public static boolean isNumeric(String strNum) {
    if (strNum == null) {
        return false;
    }
    try {
        int d = Integer.parseInt(strNum);
    } catch (NumberFormatException nfe) {
        return false;
    }
    return true;
}

}
