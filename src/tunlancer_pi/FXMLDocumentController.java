/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer_pi;

import Utils.MyConnection;
import entities.Annonce;
import java.awt.TrayIcon.MessageType;
//import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.controlsfx.control.Notifications;
import services.Annonceservice;

/**
 *
 * @author siwar
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private TextField Fxnom;
    @FXML
    private TextField FXdesc;
    @FXML
    private TextField Fxlieux;
    @FXML
    private DatePicker Fxdate;
    @FXML
    private TextField Fxrenu;
    private Label laaffiche;
    @FXML
    private TableColumn<Annonce, Integer> fxid;
   
    @FXML
    private TableColumn<Annonce, String> tadescription1;
   
    @FXML
    private TableColumn<Annonce, String> FXtabNom1;
    @FXML
    private TableColumn<Annonce, Date> tabdate;
    @FXML
    private TableColumn<Annonce, String> tablieux;
    @FXML
    private TableColumn<Annonce, String> tabrenumeration;
    @FXML
    private TableView< Annonce > tvannonce;
    @FXML
    private Button modifierfx;
    
   // private TableColumn<Annonce,Void> fxAction   = new TableColumn("");
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         FXtabNom1.setCellValueFactory(new PropertyValueFactory<Annonce, String>("nom"));
          tadescription1.setCellValueFactory(new PropertyValueFactory<Annonce, String>("description"));
           tabdate.setCellValueFactory(new PropertyValueFactory<Annonce,Date>("Date"));
           
            tablieux.setCellValueFactory(new PropertyValueFactory<Annonce, String>("lieux"));
             tabrenumeration.setCellValueFactory(new PropertyValueFactory<Annonce, String>("renumeration"));
             fxid.setCellValueFactory(new PropertyValueFactory<Annonce, Integer>("id"));
       
             Afficher();
        addButtonSupprimerToTable();
        addButtonModifierToTable();
  
        
    }   
      private void addButtonSupprimerToTable() {
        TableColumn<Annonce, Void> colBtn = new TableColumn("");

        Callback<TableColumn<Annonce, Void>, TableCell<Annonce, Void>> cellFactory = new Callback<TableColumn<Annonce, Void>, TableCell<Annonce, Void>>() {
            @Override
            public TableCell<Annonce, Void> call(final TableColumn<Annonce, Void> param) {
                final TableCell<Annonce, Void> cell = new TableCell<Annonce, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setTextFill(Paint.valueOf("#f8f7f7"));
                        btn.setStyle("-fx-background-color: #6f1075");
                        btn.setOnAction(event -> {
                            Annonce data = getTableView().getItems().get(getIndex());
                            JFrame f = new JFrame();

                            int a = JOptionPane.showConfirmDialog(f, "Êtes-vous sûr?");
                            if (a == JOptionPane.YES_OPTION) {
                               Annonceservice as=new Annonceservice();
                                as.deleteAnnonce(data.getId());
                                 Afficher();
                            }

                        });

                        
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

         tvannonce.getColumns().add(colBtn);

    }
 
      private void addButtonModifierToTable() {
        TableColumn<Annonce, Void> colBtn = new TableColumn("");

        Callback<TableColumn<Annonce, Void>, TableCell<Annonce, Void>> cellFactory = new Callback<TableColumn<Annonce, Void>, TableCell<Annonce, Void>>() {
            @Override
            public TableCell<Annonce, Void> call(final TableColumn<Annonce, Void> param) {
                final TableCell<Annonce, Void> cell = new TableCell<Annonce, Void>() {

                    private final Button btnU = new Button("Modifier");

                    {
                        btnU.setTextFill(Paint.valueOf("#f8f7f7"));
                        btnU.setStyle("-fx-background-color: #6f1075");
                        btnU.setOnAction(event -> {
                           Annonce data = getTableView().getItems().get(getIndex());
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("modificationAnnonce.fxml"));
                            Parent root;
                            try {
                                root = loader.load();
                               ModificationAnnonceController pct = loader.getController();
                                
                                pct.setNommodif(data.getNom());
                                pct.setDate((Date) data.getDate());
                              
                                pct.setDescriptionfx(data.getDescription());
                                pct.setLieux(data.getLieux());
                                pct.setRenumtaion(data.getRenumeration());
                               pct.setId(data.getId());
                                label.getScene().setRoot(root);
                            } catch (IOException ex) {
                               // Logger.getLogger(ListeEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnU);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

         tvannonce.getColumns().add(colBtn);

    }

    @FXML
     
    private void Ajouter(ActionEvent event) {
        Annonceservice as=new Annonceservice();
        Annonce a = new Annonce();
       a.setNom(Fxnom.getText());
       a.setDescription(FXdesc.getText());
      
       a.setDate(java.sql.Date.valueOf(Fxdate.getValue()));
       a.setLieux(Fxlieux.getText());
       a.setRenumeration(Fxrenu.getText());
      as.addAnnonce(a);
     
      if(as.addAnnonce(a)==true) {
          Notifications.create()
            .title("Ajout")
            .text("operation reussite").hideAfter(javafx.util.Duration.seconds(2))
	.position(Pos.BOTTOM_RIGHT)
	.darkStyle()
	.showInformation();
          
      }
      else{
           Notifications.create()
            .title("Ajout")
            .text("Erreur d'ajout").hideAfter(javafx.util.Duration.seconds(2))
	.position(Pos.BOTTOM_RIGHT)
	.darkStyle()
	.showInformation();
          
      }
     
}
    

     /*protected void showNotification( ) {
    Notifications notificationPopup = Notifications.create().title(notification.getTitle()).text(notification.getText());
    if(MessageType.INFO.equals(notification.getMessageType())) {
        notificationPopup.showConfirm();
    } else if(MessageType.INFO.equals(notification.getMessageType())) {
        notificationPopup.showInformation();
    } else if(MessageType.WARNING.equals(notification.getMessageType())) {
        notificationPopup.showWarning();
    } else if(MessageType.ERROR.equals(notification.getMessageType())) {
        notificationPopup.showError();
    } else {
        notificationPopup.show();
    }
    }*/
    @FXML
    private void Afficher() {
        /* Annonceservice as=new Annonceservice();
         tvannonce.setText(as.DisplayAnnonce().toString());*/
       // ObservableList<Annonce> annonce= Afficher();
       // fxid.setCellValueFactory(new PropertyValueFactory<Annonce, Integer>("id"));
         /*FXtabNom1.setCellValueFactory(new PropertyValueFactory<Annonce, String>("nom"));
          tadescription1.setCellValueFactory(new PropertyValueFactory<Annonce, String>("description"));
           tabdate.setCellValueFactory(new PropertyValueFactory<Annonce,Date>("Date"));
           
            tablieux.setCellValueFactory(new PropertyValueFactory<Annonce, String>("lieux"));
             tabrenumeration.setCellValueFactory(new PropertyValueFactory<Annonce, String>("renumeration"));
             tvannonce.setItems(annonce);*/
            
        ObservableList<Annonce> annonce =FXCollections. observableArrayList();
      try {
        String requete = "SELECT * FROM annonce";
            Statement st = new MyConnection ().getConnection()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Annonce a = new Annonce();
               
                a.setNom(rs.getString("nom"));
                 a.setDescription(rs.getString("description"));
                 a.setDate(rs.getDate("date"));
                 a.setLieux(rs.getString("lieux"));
                 a.setRenumeration(rs.getString("renumeration"));
                  a.setId(rs.getInt("id"));

                annonce.add(a);
               
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println( annonce );
        //return annonce;
        
        tvannonce.setItems(annonce);
        //tvannonce.setOrientation(Orientation.HORIZONTAL);
       // tvannonce.setOrientation(Orientation.HORIZONTAL);
           
    }
   /* private ObservableList<Annonce>  Afficher() {
        
        ObservableList<Annonce> annonce =FXCollections. observableArrayList();
      try {
        String requete = "SELECT * FROM annonce";
            Statement st = new MyConnection ().getConnection()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Annonce a = new Annonce();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString("nom"));
                 a.setDescription(rs.getString("description"));
                 a.setDate(rs.getDate("date"));
                 a.setLieux(rs.getString("lieux"));
                 a.setRenumeration(rs.getString("renumeration"));

                annonce.add(a);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println( annonce );
        return annonce;
        }
     */
    private void modifier() {
        modifierfx.setOnAction(event -> {
                          // Annonce data = getTableView().getItems().get(getIndex());
                              FXMLLoader loader = new FXMLLoader(getClass().getResource("Listaffichage.fxml"));
       
                              
                            });
       
        
    }
        @FXML

      private void getSelected(MouseEvent event) {
         /*ObservableList<Annonce> annonce ;
         annonce= fxlist.getSelectionModel().getSelectedItems();
         for(Annonce ann:annonce){
             System.out.println( "" +ann );
                   
         }*/
          try {
            int index = tvannonce.getSelectionModel().getSelectedIndex();

            if (index <= -1) {

                return ;

            }
              System.out.println("" +index);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("consulterannonce.fxml"));
            Parent root;
            root = loader.load();
            ConsulterannonceController  pctC = loader.getController();
            pctC.intData(fxid.getCellData(index), label.getScene());
            
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
           /* primaryStage.getIcons().add(img);
            primaryStage.setTitle(tL.getCellData(index));*/
            primaryStage.setScene(scene);
            primaryStage.show();
        }  catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    
}
