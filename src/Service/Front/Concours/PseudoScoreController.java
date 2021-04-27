/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Front.Concours;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Hiba
 */
public class PseudoScoreController implements Initializable {

    @FXML
    private TextField pseudo;
    
    @FXML
    private TextArea scoreT;
    String scoreFinale2 ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void closeAction(MouseEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/listScore.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void validerPseudo(ActionEvent event) throws SQLException {
   
    String requette;
            requette = "INSERT INTO `score`(`pseudo`,`score`)"
                    + "VALUES (?)";

            Connection connection = MyConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement(requette);
            
            pst.setString(1, pseudo.getText());
            AfficheQuizController qc = new AfficheQuizController();
       int scoreFinale = qc.score;
               String score = String.valueOf(scoreFinale);

//        System.out.println(score);
        System.out.println(scoreFinale);
        System.out.println(score);
            pst.setString(2,score);
//            pst.setString(3, tfDescription.getText());
//            
//            pst.setString(6, ctImage.getText());
//            pst.setString(7, tfCategorie.getText() );
           // pst.setBoolean(8, chkVideo. );
           // pst.setString(9, Couleur.getPromptText());

            pst.executeUpdate();
    }
     public void setScore(){
        
        scoreT.setText(scoreFinale2);
        
    }
}
