/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.sql.* ;
import javafx.scene.Node;
import javafx.stage.Stage;
import utils.MyConnection;
/**
 * FXML Controller class
 *
 * @author Hiba
 */
public class UpdateQuizController implements Initializable {

    @FXML
    private TextField txt_Nom;
    @FXML
    private TextField txt_nbQ;
    @FXML
    private TextField concour_id;
String requette ;
int id;
String concour_id0 ;
    @FXML
    private TextField txt_id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifierQuiz(ActionEvent event) throws SQLException {
        

String query = "UPDATE `quiz` SET `concour_id`=?,`nom`=?,`nb_questions`=? WHERE id=?";

    Connection cn = MyConnection.getInstance().getConnection();
    PreparedStatement ps = cn.prepareStatement(query);
        ps.setString(1, concour_id.getText());

    ps.setString(2, txt_Nom.getText());
    ps.setString(3, txt_nbQ.getText());
            ps.setString(4, txt_id.getText());

    ps.executeUpdate();
    System.out.println("Quiz Modifié avec succees !");
    
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.close();


}


    void text( int id , String nom, String nb_questions, String concour_id0 ) {

        txt_id.setText(String.valueOf(id));
        txt_Nom.setText(nom);
        txt_nbQ.setText(nb_questions) ;
        concour_id.setText(concour_id0 ) ;
    }
    
    }

    
    

