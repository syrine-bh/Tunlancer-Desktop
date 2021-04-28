/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.ServiceUser;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.chart.Chart;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class StatistiquesViewController implements Initializable {

    @FXML
    private PieChart statistique;
    @FXML
    private AnchorPane statmois;
    @FXML
    private PieChart statistique2;
 private List<Chart> charts = new ArrayList<>();
     int idf;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void print(ActionEvent event) throws SQLException {
        
   
        ServiceUser sc= new ServiceUser();
        
    ObservableList<PieChart.Data> x=FXCollections.observableArrayList(
                new PieChart.Data("Homme", sc.sex1(idf)),  
                new PieChart.Data("Femme", sc.sex2(idf)) 
              );
         PieChart chart = new PieChart(x);
    statistique.setData(x);
    charts.add(statistique);
    }
    }
    
   




