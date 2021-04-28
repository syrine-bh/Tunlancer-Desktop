/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.forum.gui;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author cyrinaa belguith
 */
public class FXMLCommentaireController implements Initializable {

    @FXML
    private JFXTextField post_search_text;
    @FXML
    private ComboBox<?> post_type;
    @FXML
    private TextArea post_src_text;
    @FXML
    private Button post_src_other;
    @FXML
    private Button post_add_button;
    @FXML
    private TextField post_title;
    @FXML
    private ListView<?> comments_view;
    @FXML
    private TextField comments_src;
    @FXML
    private Button comments_post_button;
    @FXML
    private ScrollPane affichage_publication;
    @FXML
    private VBox vbox_publication;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleb1Action(ActionEvent event) {
    }

    @FXML
    private void searchPostAction(ActionEvent event) {
    }
    
}
