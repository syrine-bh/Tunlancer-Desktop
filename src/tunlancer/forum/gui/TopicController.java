/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.forum.gui;

import Interfaces.IServiceTopic;
import Models.Topics;
import Service.ServiceTopics;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import utils.MaConnexion;


/**
 * FXML Controller class
 *
 * @author cyrinaa belguith
 */
public class TopicController implements Initializable {

   
    @FXML
    private ImageView avatar;
    @FXML
    private Label Lcontenu;
    @FXML
    private Label contenu;
    @FXML
    private Label created_day;
    @FXML
    private Label created_month;
    @FXML
    private Label created_year;
    @FXML
    private Label Ltitre;
    @FXML
    private Label Ldate;
    @FXML
    private FontAwesomeIconView EditTopic;
    @FXML
    private FontAwesomeIconView deletebtnTopic;
    
    List<Topics> ListTopic = new ArrayList();
    IServiceTopic ISTopic = new ServiceTopics();
    
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshNodes();
        
    }    
    
    
    @FXML
    public List<Topics> refreshNodes(){
        
        
        try {
            ListTopic = ISTopic.getAllTopics();
            System.out.println(ListTopic);
            
     
            if (!ListTopic.isEmpty()){
                ListTopic.forEach((Topics topics)->{
                    int i = ListTopic.indexOf(topics);
                    i++;
                    if(true){
                        Topics top = new Topics();
                        top.setTitre(topics.getTitre());
                        top.setContenu(topics.getContenu());
                        top.setDate(topics.getDate());
                        
                    }
                } );
            }
        } catch (SQLException ex) {
            Logger.getLogger(TopicController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ListTopic;
        
    }
    
    
 

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

    @FXML
    private void AjouterTopic(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/tunlancer/forum/gui/AddTopic.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ForumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setTitre(String titre) {
        this.Ltitre.setText(titre);
    }

    private void setContenu(String contenu) {
        this.Lcontenu.setText(contenu);
    }

    private void setDate(Date date) {
        this.created_day.setText("" +date);
    }
    
    public void setAvatar(ImageView avatar) {
        this.avatar = avatar;
    }
    
    private Label getTitre(){
        return Ltitre;
    }
    
    private Label getContenu(){
        return Lcontenu;
    }
    
    
}
