/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Front.Concours;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Hiba
 */
public class ProgressCircleController implements Initializable {

    @FXML
    private Circle circle;
    @FXML
    private Label number;

    /**
     * Initializes the controller class.
     */
    
    public void setNumber(Integer number){
        this.number.setText(number.toString());
    }
    
    public void setDefaultColor(){
        circle.setFill(Color.web("#E8DAEF"));
        number.setTextFill(Color.valueOf("black"));
    }
     public void setCurrentQuestionColor(){
        circle.setFill(Color.web("#C39BD3"));
        number.setTextFill(Color.valueOf("black"));
    }
       public void setWrongAnswerColor(){
        circle.setFill(Color.web("#EC7063"));
        number.setTextFill(Color.valueOf("black"));
    }
        public void setRightAnswerColor(){
        circle.setFill(Color.web("#76D7C4"));
        number.setTextFill(Color.valueOf("black"));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
