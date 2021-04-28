/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.forum.gui;

import Models.Topics;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cyrinaa belguith
 */
public class StatistiqueController implements Initializable {

    
    @FXML
    private LineChart<String, Number> LineChart;
    @FXML
    private JFXButton home;
    @FXML
    private TextField year;
    @FXML
    private JFXButton search;

    private ScheduledExecutorService scheduledExecutorService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

   
    @FXML
    private void Switch1(ActionEvent event) {
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("Statistique.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(tableViewScene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        scheduledExecutorService.shutdownNow();
    }

    @FXML
    private void getStats(ActionEvent event) {
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        
        LineChart.getData().add(series);
        LineChart.setLegendVisible(true);
        LineChart.setAnimated(true);
        
        String an = year.getText();
        Topics mtd = new Topics();

        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            // Update the chart
            Platform.runLater(() -> {
                series.getData().clear();
                LinkedHashMap<Date, Integer> map = new LinkedHashMap<>();
                    map = mtd.AfficheStatisticTopics(an);
                    map.entrySet().forEach(entry -> {

                        DateFormat dateFormat = new SimpleDateFormat("MM");
                        String strDate = dateFormat.format(entry.getKey());
                        System.out.println(strDate);
                        int i = Integer.parseInt(strDate);

                        String month = nomMois(i);
                        System.out.println(month);
                        System.out.println(entry.getValue());
                        
                        series.getData().add(new XYChart.Data<String, Number>(month, entry.getValue()));
                   

                });
                   
            });
        }, 0, 1, TimeUnit.SECONDS);

    }
    

      
    public void killThread() throws Exception {
        
        if(!scheduledExecutorService.isShutdown()){
            scheduledExecutorService.shutdownNow();
        }
    }
    
    public String nomMois(int i) {
        String month = "";
        if (i == 1) {
            month = "January";
        } else if (i == 2) {
            month = "February";
        } else if (i == 3) {
            month = "March";
        } else if (i == 4) {
            month = "April";
        } else if (i == 5) {
            month = "May";
        } else if (i == 6) {
            month = "June";
        } else if (i == 7) {
            month = "July";
        } else if (i == 8 ){
            month = "August";
        } else if (i == 9) {
            month = "September";
        } else if (i == 10) {
            month = "October";
        } else if (i == 11) {
            month = "November";
        } else if (i == 12) {
            month = "December";
        }

        return month;
    }
;
    
}
