/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Hiba
 */
public class DisplayConcoursPageController implements Initializable {

 
    @FXML
    private StackPane concoursPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addConcoursListScreen();
    }

    private void addConcoursListScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GridConcours.fxml"));
        Node node;
        try {
            node = fxmlLoader.load();
            concoursPane.getChildren().add(node);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
