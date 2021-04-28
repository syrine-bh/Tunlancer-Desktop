/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

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
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Hiba
 */
public class HomePageHolderController implements Initializable {

    @FXML
    private AnchorPane slider;
    @FXML
    private Label label;
    @FXML
    private AnchorPane pageHolder;
    @FXML
    private Label userName1;
    @FXML
    private AnchorPane homeSideBar;
    @FXML
    private AnchorPane profileSlider;
    @FXML
    private Label userName;
    @FXML
    private Label userEmail;
    @FXML
    private GridPane taskGrid;
    @FXML
    private AnchorPane profileSideBar;
    @FXML
    private AnchorPane offresSideBar;
    @FXML
    private AnchorPane recrutementsSideBar;
    @FXML
    private AnchorPane concoursSideBar;
    @FXML
    private AnchorPane forumsSideBar;
    @FXML
    private AnchorPane publicationsSideBar;
    @FXML
    private AnchorPane pageHolder1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void minAction(MouseEvent event) {
    }

    @FXML
    private void closeAction(MouseEvent event) {
    }

    @FXML
    private void profileAction(MouseEvent event) {
    }

    @FXML
    private void homePageAction(MouseEvent event) {
    }

    @FXML
    private void ShowOngoingTasksAction(MouseEvent event) {
    }

    @FXML
    private void userPorfileAction(MouseEvent event) {
    }

    @FXML
    private void backAction(MouseEvent event) {
    }

    @FXML
    private void signOutAction(MouseEvent event) {
    }
    @FXML
    private void profilePageAction(MouseEvent event) {
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
    private void ForumsPageAction(MouseEvent event) throws IOException {
     
    
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
    

public void forumsMenu() throws IOException {
       
        concoursSideBar.getStyleClass().removeAll(concoursSideBar.getStyleClass());
       concoursSideBar.getStyleClass().add("menu");
        concoursSideBar.getStyleClass().add("selectedMenu");

        profileSideBar.getStyleClass().removeAll(profileSideBar.getStyleClass());
        profileSideBar.getStyleClass().add("menu");
        profileSideBar.getStyleClass().add("unselectedMenu");

       offresSideBar.getStyleClass().removeAll(offresSideBar.getStyleClass());
        offresSideBar.getStyleClass().add("menu");
        offresSideBar.getStyleClass().add("unselectedMenu");

        forumsSideBar.getStyleClass().removeAll(forumsSideBar.getStyleClass());
        forumsSideBar.getStyleClass().add("menu");
        forumsSideBar.getStyleClass().add("unselectedMenu");

       publicationsSideBar.getStyleClass().removeAll(publicationsSideBar.getStyleClass());
        publicationsSideBar.getStyleClass().add("menu");
        publicationsSideBar.getStyleClass().add("unselectedMenu");

        recrutementsSideBar.getStyleClass().removeAll( recrutementsSideBar.getStyleClass());
        recrutementsSideBar.getStyleClass().add("menu");
        recrutementsSideBar.getStyleClass().add("unselectedMenu");

        homeSideBar.getStyleClass().removeAll(homeSideBar.getStyleClass());
        homeSideBar.getStyleClass().add("menu");
        homeSideBar.getStyleClass().add("unselectedMenu");

        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/tunlancer/forum/gui/AllTopics.fxml")));

    }
}
    