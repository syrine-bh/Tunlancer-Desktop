/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Back;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Hiba
 */
public class AdminPageHolderController implements Initializable {

    @FXML
    private AnchorPane pageHolder;
    @FXML
    private Label label;
    @FXML
    private AnchorPane slider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void dashboardPageAction(MouseEvent event) {
    }

    @FXML
    private void usersPageAction(MouseEvent event) {
    }

    @FXML
    private void offresPageAction(MouseEvent event) {
    }

    @FXML
    private void recrutementsPageAction(MouseEvent event) {
    }

    @FXML
    private void concoursPageAction(MouseEvent event) {
    }

    @FXML
    private void forumsPageAction(MouseEvent event) throws IOException {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToY(80);
        slide.play();
        forumsMenu();
    }

    @FXML
    private void publicationsPageAction(MouseEvent event) {
    }

    private void forumsMenu() throws IOException {
         

        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/tunlancer/forum/gui/ForumBack.fxml")));

    }
    
    
}
