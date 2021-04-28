/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.forum.gui;

import Models.Replies;
import Models.Topics;
import Service.ServiceReplies;
import Service.ServiceTopics;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author cyrinaa belguith
 */
public class DetailsController implements Initializable {

    @FXML
    private ImageView avatar;
    @FXML
    private Label Ltitre;
    @FXML
    private Label Lcontenu;
    @FXML
    private Label Ldate;
    @FXML
    private Label date;
    @FXML
    private Label titre;
    @FXML
    private TextArea contenu;
    @FXML
    private VBox vBox;
    @FXML
    private TextArea contenuCommentaire;
    private static Topics to = new Topics();
    @FXML
    private JFXListView<Replies> listView;
    

    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contenu.setEditable(false);
        ShowTopicDetails(to);
    //    ServiceReplies SR= new ServiceReplies();
    //    Replies r = new Replies();
    //    ShowReplies();
        // TODO
    }
    
    public void ShowTopicDetails(Topics t){
        ServiceTopics ST = new ServiceTopics();
        t= ST.AfficherTopicDetails(4);
        //t=ST.AfficherTopicDetails(t.getId());
        to=t;
        titre.setText(t.getTitre());
        contenu.setText(t.getContenu());  
   }
    
    public void ShowReplies(){
        ServiceReplies SR = new ServiceReplies();
        
        vBox.setPadding(new Insets(10));
        vBox.setAlignment(Pos.CENTER);
//        ObservableList<Replies> TopicList = SR.AfficherReplies(to.getId());
        ObservableList<Replies> TopicList = SR.AfficherReplies(4);

        System.out.println(TopicList);
            listView.setCellFactory((Callback<ListView<Replies>, ListCell<Replies>>) param -> {
            return new ListCell<Replies>() {
                @Override
                protected void updateItem(Replies r, boolean empty) {
                    super.updateItem(r, empty);

                    if (r == null || empty) {
                        setText(null);
                    } else {
                       
                        HBox Hbx = new HBox(100);
                        //Hbx.setAlignment(Pos.TOP_CENTER);
                        Hbx.setPadding(new Insets(5, 10, 5, 10));
 Label Contenu= new Label(String.valueOf(r.getContenu())+"\n"+"Ajouté le : "+r.getCreated_at());
                         Contenu.setMinWidth(100);
                        Contenu.setMinHeight(100);
                        Contenu.setCursor(Cursor.HAND);

      
Hbx.getChildren().addAll(Contenu);
                        setText(null);
                        setGraphic(Hbx);
                    }

                }
            };
            
        });
            listView.setItems(TopicList);
            vBox.getChildren().add(listView);
        
        
    }

    @FXML
    private void AffichageTextArea(MouseEvent event) {
        Replies r =listView.getSelectionModel().getSelectedItem();
 //       String s = String.valueOf(r.getId());
//        contenuCommentaire.setText(r.getContenu());
 //        id.setText(s); 
      ShowReplies();
        
    }
    
       public boolean validate(){
         if(contenuCommentaire.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Valider les champs");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir les champs");
            alert.showAndWait();
         return false   ;
        }else{
             JOptionPane.showMessageDialog(null, "succes !!");
        Notifications notificationBuilder = Notifications.create()
            .title("Succes").text("votre commentaire a été ajouté !!").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
         }
         return true;
    }

    @FXML
    private void Envoyer(ActionEvent event) {
        validate();

        ServiceReplies SR = new ServiceReplies();
        Replies r = new Replies();
        Topics to = new Topics();
        java.util.Date d1 = new java.util.Date();
        Date dateToday = new java.sql.Date(d1.getTime());
        r.setCreated_at(dateToday);
        r.setContenu(contenuCommentaire.getText());
        //r.setTopic_id(r.getTopic_id());
        r.setTopic_id(4);
        r.setUser_id(48);
        //SR.Ajouter(r, getTopicsId());
        SR.AjouterRep(r);
        //SR.AjouterReponse(r, r.getTopic_id());
       // SR.AjouterReponse(r, to.getId());
       //SR.addCommentaire(r);
        ShowReplies();

    }
    
    public String CensoredComment(String comment) {
try {
	BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\cyrinaa belguith\\Documents\\badwords.txt"));
	String s ; 
	List<String> words = new ArrayList<String>() ;
	while (( s = br.readLine()) != null )  {
	words.add(s) ;
	}
	
	for (String word : words ) {
		Pattern rx = Pattern.compile("\\b" + word + "\\b", Pattern.CASE_INSENSITIVE);
		comment = rx.matcher(comment ).replaceAll(new String(new char[word.length()]).replace('\0', '*'));
	}
	return comment ;

}catch (Exception ex) {

 System.out.println("failed to read txt") ; 
 System.out.println(ex);
}
return comment ; 
}
    
    
    
    
    public int getTopicsId() {
        TopicsHolder holder = TopicsHolder.getInstance();
        int t = holder.getId();
        return t;
    }
    
    

    
    
}
