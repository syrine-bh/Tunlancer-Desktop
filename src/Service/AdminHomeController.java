/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * FXML Controller class
 *
 * @author Hiba
 */
public class AdminHomeController implements Initializable {

    @FXML
    private TabPane adminTabPane;
    private Tab ajouterQuizTab;
    @FXML
    private Tab gestionConcours;
    @FXML
    private Tab gestionQuiz;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
          
           GestionConcours();
                      GestionQuiz();

    }    
   
    public void GestionConcours () {
        try {
           
            Parent node = FXMLLoader.load(getClass().getResource("/FXML/GestionConcours.fxml"));
            gestionConcours.setContent(node);
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }
     public void GestionQuiz () {
        try {
           
            Parent node = FXMLLoader.load(getClass().getResource("/FXML/GestionQuiz.fxml"));
            gestionQuiz.setContent(node);
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }
    }    
   