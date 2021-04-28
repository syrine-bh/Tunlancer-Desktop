/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.forum.gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Pane;
import utils.MaConnexion;

/**
 * FXML Controller class
 *
 * @author cyrinaa belguith
 */
public class StatForumController implements Initializable {

    @FXML
    private Pane paneview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }

private void loadData(){
        paneview.getChildren().clear();
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        Connection c = MaConnexion.getInstance().getCnx();
       try{
        
            PreparedStatement pt2=c.prepareStatement("SELECT DISTINCT titre as 'Java',((select count(*) FROM topics WHERE titre=Java)/(select count(*) from topics))*100 as 'Statique' from topics");
          
            ResultSet rs2= pt2.executeQuery();
            while(rs2.next()){
                list.add(new PieChart.Data(rs2.getString(1),rs2.getDouble(2)));
//            list.add(new PieChart.Data(rs2.getString(1)+" "+String.valueOf(Math.round((float)(rs2.getInt(2)*100)/somme))+"%",Math.round(((float)(rs2.getInt(2)*100)/somme))));
            }
            } catch (SQLException ex) {
                System.out.println("not ok"+ex.getMessage());
        }
            
           
        PieChart Chart= new PieChart(list);
      
        paneview.getChildren().add(Chart);
        
    }    
    
}
