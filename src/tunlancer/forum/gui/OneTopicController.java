/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.forum.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author cyrinaa belguith
 */
public class OneTopicController implements Initializable {

    @FXML
    private Pane box2;
    @FXML
    private ImageView avatar;
    @FXML
    private Label titre;
    @FXML
    private TextArea contenu;
    @FXML
    private Label date;
    @FXML
    public FontAwesomeIconView editBtn;
    @FXML
    public FontAwesomeIconView supprimerBtn;
    @FXML
    private AnchorPane box;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   

    public OneTopicController() {
    }

    public void setTitre(String titre) {
        this.titre.setText(titre);
    }
    
    public void setContenu(String contenu) {
        this.titre.setText(contenu);
    }
    
    public void setAvatar(ImageView avatar) {
        this.avatar = avatar;
    }
    
    public void setDate(String date) {
        this.titre.setText(date);
    }

    public Label getTitre() {
        return titre;
    }

    public TextArea getContenu() {
        return contenu;
    }

    public Label getDate() {
        return date;
    }

    public ImageView getAvatar() {
        return avatar;
    }
    
    
    
    
    
}
