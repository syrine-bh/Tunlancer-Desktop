/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer_pi;

import Utils.MyConnection;
import entities.Annonce;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author siwar
 */
public class StatchartController implements Initializable {

    /**
     * Initializes the controller class.
     */
     
    @FXML
    private BarChart<String, Integer> bar;
    @FXML
    private PieChart chartstat;
   // private Pane view;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //view.getChildren().clear();
        //ObservableList<BarChart.Data> list = FXCollections.observableArrayList();
        
     /*  try{
            //String requete = "SELECT COUNT(a.nom) FROM annonce a,postuler p where a.id=p.annonce_id GROUP BY a.nom ";
             String requete2 = "SELECT a.nom , COUNT(a.nom) FROM annonce a,postuler p where a.id=p.annonce_id GROUP BY a.nom ";
              
            Statement st = new MyConnection ().getConnection()
                    .createStatement();
            /* ResultSet rs = st.executeQuery(requete);
             int somme=0;
            while(rs.next()){
                somme=somme+rs.getInt(1);
            }
           
                 
          
            ResultSet rs2=  st.executeQuery(requete2);
            while(rs2.next()){
         list.add(new PieChart.Data(rs2.getString(1),rs2.getInt(2)));
                 }
            } catch (SQLException ex) {
            Logger.getLogger(StatchartController .class.getName()).log(Level.SEVERE, null, ex);
        }*/
            
            
            
        
        /*PieChart Chart= new PieChart(list);
        Chart.setTitle("stat");
      
        view.getChildren().add(bar);*/
        
        
        // TODO
        XYChart.Series set1 = new XYChart.Series<>();
            //XYChart.Series set2 = new XYChart.Series<>();
         // List<Annonce> ANNList = new ArrayList<>();
        try {
            String requete = "SELECT a.nom ,COUNT(a.nom) FROM annonce a,postuler p where a.id=p.annonce_id GROUP BY a.nom ";
              XYChart.Series<String,Integer> series=new XYChart.Series<>();
            Statement st = new MyConnection ().getConnection()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()) {
               
           set1.getData().add(new XYChart.Data<>(rs.getString(1),rs.getInt(2)));
               
                // view.getChildren().add(bar);
              
           
            }
             bar.getData().add(set1);
        } catch (SQLException ex) {    
            Logger.getLogger(StatchartController .class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     

    @FXML
    private void load(ActionEvent event) {
        
    
}
 }  