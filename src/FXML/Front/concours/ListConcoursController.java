/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML.Front.concours;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author Hiba
 */
public class ListConcoursController implements Initializable {

    @FXML
    private FlowPane concoursListContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addConcoursItemScreen();
    }    
     private void addConcoursItemScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/concoursItem.fxml"));
        Node node;
        try {
            node = fxmlLoader.load();
            concoursListContainer.getChildren().add(node);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
