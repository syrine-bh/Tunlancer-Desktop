/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer_pi;

import Utils.MyConnection;
import entities.Annonce;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
   // private TableColumn<Annonce,Void> fxAction   = new TableColumn("");
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         FXtabNom1.setCellValueFactory(new PropertyValueFactory<Annonce, String>("nom"));
          tadescription1.setCellValueFactory(new PropertyValueFactory<Annonce, String>("description"));
           tabdate.setCellValueFactory(new PropertyValueFactory<Annonce,Date>("Date"));
           
            tablieux.setCellValueFactory(new PropertyValueFactory<Annonce, String>("lieux"));
             tabrenumeration.setCellValueFactory(new PropertyValueFactory<Annonce, String>("renumeration"));
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
                                pct.setId(data.getId());
                                pct.setNommodif(data.getNom());
                                pct.setDate((Date) data.getDate());
                              
                                pct.setDescriptionfx(data.getDescription());
                                pct.setLieux(data.getLieux());
                                pct.setRenumtaion(data.getRenumeration());
                               
                               // n.getScene().setRoot(root);
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
    }

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
        //return annonce;
        
        tvannonce.setItems(annonce);
           
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
    @FXML
    private void modifier(ActionEvent event) {
       
         Annonceservice as=new Annonceservice();
       // Annonce a = new Annonce(Integer.parseInt( Fxnom.getText()),FXdesc.getText(), Fxlieux.getText(),Fxrenu.getText());
  
    JOptionPane.showMessageDialog(null,"Competence modifié");
    }
    
}
