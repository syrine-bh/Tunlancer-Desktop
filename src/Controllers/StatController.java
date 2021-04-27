/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.ServiceUser;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import Models.Users;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.Chart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.MyConnection;
/**
 * FXML Controller class
 *
 * @author asus
 */
public class StatController implements Initializable {

    private PieChart pieChart;
    private AnchorPane statgender = new AnchorPane(); 
    
    
    
    private List<Chart> charts = new ArrayList<>();
     int idf;
    @FXML
    private Pane paneview;
    @FXML
    private Button user;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          
         paneview.getChildren().clear();
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
      Connection cnx = MyConnection.getInstance().getConnection();
       try{
           
            PreparedStatement pt=cnx.prepareStatement("select  user_id from competence");
          
            ResultSet rs= pt.executeQuery();
            int somme=0;
            while(rs.next()){
                somme=somme+rs.getInt(1);
            }
        
                  PreparedStatement pt2=cnx.prepareStatement("select titre,user_id from competence");
          
            ResultSet rs2= pt2.executeQuery();
            while(rs2.next()){
         list.add(new PieChart.Data(rs2.getString(1),rs2.getInt(2)));

            }
            } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
   
        PieChart Chart= new PieChart(list);
      Chart.setTitle("Nombre de profil Selon Competence");
      
        paneview.getChildren().add(Chart);   
    }
   
    
    void initData() throws SQLException {
   
      
    }
    
   public void createPie() throws SQLException {
      
   } 

    private void Load(ActionEvent event) throws SQLException {
        
         
    }

    private void Load(MouseEvent event) {
         paneview.getChildren().clear();
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
      Connection cnx = MyConnection.getInstance().getConnection();
       try{
           
            PreparedStatement pt=cnx.prepareStatement("select  user_id from competence");
          
            ResultSet rs= pt.executeQuery();
            int somme=0;
            while(rs.next()){
                somme=somme+rs.getInt(1);
            }
        
                  PreparedStatement pt2=cnx.prepareStatement("select titre,user_id from competence");
          
            ResultSet rs2= pt2.executeQuery();
            while(rs2.next()){
         list.add(new PieChart.Data(rs2.getString(1),rs2.getInt(2)));

            }
            } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
   
        PieChart Chart= new PieChart(list);
        Chart.setTitle("Nombre de profil Selon Competence");
      
        paneview.getChildren().add(Chart);
        
    }

    @FXML
    private void godash(ActionEvent event) throws IOException {
        Parent globalPane = FXMLLoader.load(getClass().getResource("/GUI/Stat.fxml"));
        Scene scene = new Scene(globalPane);
         Stage mainStage=new Stage();
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private void gouser(ActionEvent event) throws IOException {
         user.getScene().getWindow().hide();
         Parent globalPane = FXMLLoader.load(getClass().getResource("/GUI/Admin.fxml"));
        Scene scene = new Scene(globalPane);
         Stage mainStage=new Stage();
        mainStage.setScene(scene);
        mainStage.show();
        
    }
    
    
}

  

