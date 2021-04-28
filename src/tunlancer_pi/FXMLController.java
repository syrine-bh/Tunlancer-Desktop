/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer_pi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author siwar
 */
public class FXMLController implements Initializable {

    @FXML
    private Button stat;
    @FXML
    private Tab ann;
    @FXML
    private Tab type;
    @FXML
    private Tab Stati;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         Annonce ();
         Gestiontype();
         statistiques();
    }    

    @FXML
   
         private void statistiques()  {
       // stat.getScene().getWindow().hide();
        
         try {
           
            Parent node = FXMLLoader.load(getClass().getResource("statchart.fxml"));
            Stati.setContent(node);
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }
          public void Annonce () {
        try {
           
            Parent node = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            ann.setContent(node);
        } catch (Exception e) {
            e.printStackTrace();
        
        }
        }
          private void Gestiontype(){
              try {
        Parent node = FXMLLoader.load(getClass().getResource("categorie.fxml"));
            type.setContent(node);
        } catch (Exception e) {
            e.printStackTrace();
    }
          }
           }



    
    
    

