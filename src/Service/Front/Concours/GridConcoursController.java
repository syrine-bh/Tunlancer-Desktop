/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Front.concours;

import Service.Front.Concours.ConcoursItemController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import Service.Front.Concours.ServiceConcours;
import entities.Concour;
/**
 * FXML Controller class
 *
 * @author Hiba
 */
public class GridConcoursController implements Initializable {

    @FXML
    private GridPane concoursGrid;
    public int pageCount,currentPage;
//    ServicePaidTask spt = new ServicePaidTask();
  ServiceConcours sc = new ServiceConcours();
//    private final int NUM=9;
   List<Concour> concours = sc.ListConcours();
    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        setData(1, (List<Concour>) concours) ;
        }   
    
    
    
     public void setData(int index,List<Concour> concours){
         System.out.println("testGrid22222222");
//        currentPage=index;
        int y = 0;
        int x = 0;
//        pageCount=concours.size()/NUM;
//        if(concours.size()%NUM>0){
//            pageCount++;
//        }
//        int a,b;
////        a=currentPage*NUM;
//        if(currentPage==(pageCount-1))
//            b=concours.size();
//        else
//            b=a+NUM;
        for (int i = 0; i < concours.size(); i++) {
          
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("/concoursItem.fxml"));
//                try {
//                    Pane pane = loader.load();
//                    ConcoursItemController c = loader.getController();
////                    c.setData((Concour) concours.get(i));
//                    if (x > 2) {
//                        y++;
//                        x = 0;
//                    }
//                    concoursGrid.add(pane, x, y);
//                    x++;
//                } catch (IOException ex) {
//                    System.out.println(ex.getMessage());
//                }
//
//            } else {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/concoursItem.fxml"));
                try {
                    Pane pane = loader.load();
                    ConcoursItemController c = loader.getController();
                    c.setData((Concour) concours.get(i));
                    if (x > 2) {
                        y=y+2;
                        x =0;
                    }
                    concoursGrid.add(pane, x, y);
                    x++;
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
    
    


