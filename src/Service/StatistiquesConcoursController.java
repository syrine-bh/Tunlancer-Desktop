/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entities.Concour;
import entities.Participation;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Hiba
 */
public class StatistiquesConcoursController implements Initializable {

    @FXML
    private PieChart pieChartC;

    /**
     * Initializes the controller class.
     */
    ObservableList<PieChart.Data> piechartdata;
    ArrayList<Integer> concour_id = new ArrayList<Integer>();
        ArrayList<String> nom= new ArrayList<String>();

    
        
        
    private void loadChart() {
        
        
        
               
         piechartdata=FXCollections.observableArrayList();
                 
        try {
            Concour c =new Concour();
            Participation p = new Participation();
            String requete="SELECT * FROM `concour` JOIN participation ON participation.concour_id = concour.id";
//            String requete="SELECT * from Concour ";
//            String req2="SELECT participation.concour_id FROM `participation` WHERE concour_id=4";
//            String req2="SELECT COUNT(participation.concour_id) FROM participation WHERE concour_id=4";


//            XYChart.Series<String,Double> series=new XYChart.Series<>();
            
            //exécuter la requete et la stocker dans un resulttest
            PreparedStatement pst = MyConnection.getInstance().getConnection().prepareStatement(requete);
                        PreparedStatement pst2 = MyConnection.getInstance().getConnection().prepareStatement(requete);

            ResultSet rs =  pst.executeQuery();
                        ResultSet rs2 =  pst2.executeQuery();

            while(rs.next()){
                piechartdata.add(new PieChart.Data(rs2.getString("nom"),rs.getInt("concour_id")));
                nom.add(rs.getString("nom"));
                concour_id.add(rs.getInt("concour_id"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(StatistiquesConcoursController.class.getName()).log(Level.SEVERE, null, ex);
        }

 
    }
        
        
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadChart();
        pieChartC.setData(piechartdata);
    }    
    
}
