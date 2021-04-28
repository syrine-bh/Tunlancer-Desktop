/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.forum.gui;

import Models.Post_like;
import Models.Replies;
import Service.ServiceReplies;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ASuS
 */
public class CommController implements Initializable {

    @FXML
    private Button xbtn;
    @FXML
    private Button home;
    @FXML
    private TableView<Replies> table;
    @FXML
    private TableColumn<Replies, String> com;
    @FXML
    private TableColumn<Replies, Date> date;
    
    private ObservableList<Replies> dataList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Replies, ?> react;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Tableview Dynamic Display
        com.setCellValueFactory(new PropertyValueFactory<Replies, String>("contneu"));   
        date.setCellValueFactory(new PropertyValueFactory<Replies, Date>("date_com"));
        ServiceReplies scom = new ServiceReplies();
        
        scom.getCommentaires(4).forEach(e->{dataList.add(e);});
        table.setItems(dataList);  
        
        //Edit Table
        table.setEditable(true);
        com.setCellFactory(TextFieldTableCell.forTableColumn());
        ContextMenu cm = new ContextMenu();
        MenuItem Delete = new MenuItem("Delete");
        MenuItem Like = new MenuItem("Like");
        Delete.setOnAction(new EventHandler <ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
          Object p = table.getSelectionModel().getSelectedItem();
          Replies com = (Replies) p;
          ServiceReplies  scom = new ServiceReplies();
          System.out.println(com.toString());
          scom.SupprimerCommentaire(com);
          Notifications notificationBuilder = Notifications.create()
            .title("Succes").text("Votre commentaire a été supprimé !!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
       RefreshCom();
            }
        });
        Like.setOnAction(new EventHandler <ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
          Object p = table.getSelectionModel().getSelectedItem();
          Replies com = (Replies) p;
          ServiceReplies  scom = new ServiceReplies();
          System.out.println(com.toString());
          scom.like(com);
          
          Notifications notificationBuilder = Notifications.create()
            .title("Succes").text("like !!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
       RefreshCom();
            }
        });
        cm.getItems().add(Delete);
        cm.getItems().add(Like);
        table.setContextMenu(cm);
        
        //Message Welcome
        JOptionPane.showMessageDialog(null,"Welcome !!");
    }    
    
    @FXML
    private void Menu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("..//AllTopics.fxml"));
        Parent root = loader.load(); 
        home.getScene().setRoot(root);
        JOptionPane.showMessageDialog(null, "Bienvenue !!");
    }
    
    public ObservableList<Replies> getCom(List<Replies> l){
        ObservableList<Replies> data = FXCollections.observableArrayList();
        for (int i =0; i<=l.size()-1; i++){
            data.add(l.get(i));
        }
        return (ObservableList<Replies>) data;
    }
    
    private void RefreshCom() {
        ServiceReplies scom = new ServiceReplies();
        com.setCellValueFactory(new PropertyValueFactory<Replies, String>("contenu"));   
        date.setCellValueFactory(new PropertyValueFactory<Replies, Date>("created_at"));
        scom.afficher().forEach(e->{dataList.add(e);});
        table.setItems(getCom(scom.afficher()));
        System.out.println(dataList);
    }
    
    @FXML
    private void EditSujCom(TableColumn.CellEditEvent edittedCell) {
        if(com.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Please fill in the empty field");
            a.showAndWait();
        }
         else{
        Replies co = table.getSelectionModel().getSelectedItem();
        co.setContenu(edittedCell.getNewValue().toString());
        ServiceReplies scom = new ServiceReplies();
        scom.modifier(co);
        JOptionPane.showMessageDialog(null, "Success !!");  
        Notifications notificationBuilder = Notifications.create()
            .title("Succes").text("Your comment has been updated !!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();}
    } 
    
    @FXML
    private void Exit(ActionEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
        JOptionPane.showMessageDialog(null, "Are you sure ? :(");
    }
}
