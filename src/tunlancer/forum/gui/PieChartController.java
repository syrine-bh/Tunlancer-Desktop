/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.forum.gui;

import Interfaces.IServiceTopic;
import Service.ServiceTopics;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import utils.MaConnexion;

/**
 * FXML Controller class
 *
 * @author cyrinaa belguith
 */
public class PieChartController implements Initializable {

    @FXML
    private PieChart pieChart;
    
    java.sql.Connection cnx = MaConnexion.getInstance().getCnx();
    private ObservableList data;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        data = FXCollections.observableArrayList();
          try{
            String SQL = "SELECT titre from topics";
            String query="SELECT COUNT(*) FROM topics";
            PreparedStatement st= cnx.prepareStatement(query);
            ResultSet rs= st.executeQuery(); 
            PreparedStatement ste = (PreparedStatement) cnx.prepareStatement(SQL);
            ResultSet rst = ste.executeQuery();
            while((rs.next())&&(rst.next())){
                System.out.println(rst.getString("titre"));
                data.add(new PieChart.Data(rst.getString("titre"), rs.getInt(1)));
                pieChart.setData(data);
            }
          }catch(SQLException e){
              System.out.println("Error on DB connection"+e.getMessage());
          }
    }    
    
}
