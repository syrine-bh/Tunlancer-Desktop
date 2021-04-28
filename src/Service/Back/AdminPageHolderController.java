/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Back;

import Controllers.StatController;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AdminPageHolderController implements Initializable {

    @FXML
    private AnchorPane pageHolder;
    @FXML
    private Label label;
    @FXML
    private AnchorPane slider;
    @FXML
    private Pane paneview;
    @FXML
    private PieChart pieChart;
  private ObservableList data_pie;
   Connection cnx = MyConnection.getInstance().getConnection();
    @FXML
    private StackPane contentPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
 try {
            // TODO
            createPie();
             pieChart = new PieChart(data_pie);

        //pieChart.getData().addAll(data_pie);
        contentPane.getChildren().add(pieChart);
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
         paneview.getChildren().clear();
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
     
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
     
      
        paneview.getChildren().add(Chart);   
    }
      

    
     public void createPie() throws SQLException {
         try {
            data_pie = FXCollections.observableArrayList();
            
            PreparedStatement pt = cnx.prepareStatement("select DISTINCT pays,COUNT(pays) as nbr from users group by pays");
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                //adding data on piechart data
                data_pie.add(new PieChart.Data(rs.getString("pays"),rs.getInt(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
      
   } 

    @FXML
    private void dashboardPageAction(MouseEvent event) {
    }

    @FXML
    private void usersPageAction(MouseEvent event) throws IOException {
        Parent globalPane = FXMLLoader.load(getClass().getResource("/GUI/users.fxml"));
        Scene scene = new Scene(globalPane);
        Stage mainStage=new Stage();
        mainStage.setScene(scene);
        mainStage.show();  
    }

    @FXML
    private void offresPageAction(MouseEvent event) {
    }

    @FXML
    private void recrutementsPageAction(MouseEvent event) {
    }

    @FXML
    private void concoursPageAction(MouseEvent event) {
    }

    @FXML
    private void forumsPageAction(MouseEvent event) {
    }

    @FXML
    private void publicationsPageAction(MouseEvent event) {
    }
    
}
