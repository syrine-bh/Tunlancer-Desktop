/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Front.Concours;

import animatefx.animation.ZoomIn;
import animatefx.animation.ZoomOut;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Boolean;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import entities.Concour;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Hiba
 */
public class ConcoursItemController implements Initializable {

    @FXML
    private AnchorPane menuId;
    private Label concoursDesc;
    @FXML
    private Label concoursTitle;
    private boolean menuIsDisplayed = false;
    int id = 0;
    @FXML
    private Label concoursSujet;

    Concour c = new Concour();
    @FXML
    private Label typeConcours;
    @FXML
    private Pane showDetailsConcours;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
            setData(c);
//            showDetailsConcours();
//        } catch (IOException ex) {
//            System.out.println("errrr");        }
    }

    public void setData(Concour c) {
        System.out.println("test set data");

        concoursTitle.setText(c.getNom());
        concoursSujet.setText(c.getSujet());
        String test = String.valueOf(c.getIsVideo());
        if (test == "false") {
            typeConcours.setText("Quiz");
        } else {
            typeConcours.setText("Video");
        }
//typeConcours.setText();
////        Boolean test = c.getIsVideo();

        id = c.getId();
    }

    private void dotsAction(MouseEvent event) {
        if (menuIsDisplayed) {
            menuIsDisplayed = false;
            new ZoomOut(menuId).play();
            menuId.setDisable(true);
        } else {
            menuId.setVisible(true);
            menuId.setDisable(false);
            menuIsDisplayed = true;
            new ZoomIn(menuId).play();
        }
    }

    @FXML
    private void showDetailsConcours() throws IOException {
        concoursHolder th = concoursHolder.getINSTANCE();
        th.setId(id);

       AnchorPane pageHolder = (AnchorPane) concoursTitle.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
        pageHolder.getChildren().removeAll(pageHolder.getChildren());
        pageHolder.getChildren().add(FXMLLoader.load(getClass().getResource("/concoursDetails.fxml")));
    }

//    @FXML
//    private void showConcoursDetails(javafx.scene.input.MouseEvent event) {
//    }
   

}
