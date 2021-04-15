/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.forum.gui;

import Models.Topics;
import Service.ServiceTopics;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;
import utils.MaConnexion;


/**
 * FXML Controller class
 *
 * @author cyrinaa belguith
 */
public class TopicController implements Initializable {

    @FXML
    private TextField tfTitre;
    @FXML
    private TextArea taContenu;
    @FXML
    private Button btnvalider;
    @FXML
    private Button btnaffiche;
    private TableView<Topics> LAfficher;
    @FXML
    private TableColumn<Topics, String> ColTitre;
    @FXML
    private TableView<Topics> TopicTable;
//    ObservableList<Topics> TopicList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Topics, String> ColContenu;
    @FXML
    private TableColumn<Topics, Date> ColDate;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void AjouterTopic(ActionEvent event) {
        ServiceTopics st = new ServiceTopics();
        Topics t = new Topics();
        t.setTitre(tfTitre.getText());
        t.setContenu(taContenu.getText());
        st.AjouterTopic(t);
    }

    private void AfficherTopic(ActionEvent event) {
        ObservableList<Topics> Topics = AfficherTopic();
        ColTitre.setCellValueFactory(new PropertyValueFactory<Topics, String>("titre"));
        ColContenu.setCellValueFactory(new PropertyValueFactory<Topics, String>("contenu"));
        ColDate.setCellValueFactory(new PropertyValueFactory<Topics, Date>("date"));
        TopicTable.setItems(Topics);
 
    }
      @FXML
   private ObservableList<Topics> AfficherTopic(){
      ObservableList<Topics> Topics = FXCollections.observableArrayList();

      try {
            String query="SELECT * from topics";
            Statement st=MaConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                Topics t = new Topics();
//                t.setId(rs.getInt("id"));
                t.setTitre(rs.getString("titre"));
                t.setContenu(rs.getString("contenu"));
                t.setDate(rs.getDate("date"));
                Topics.add(t);
                System.out.println(Topics);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
          
        return Topics;
    }
    
}
