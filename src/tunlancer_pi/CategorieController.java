/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer_pi;

import Utils.MyConnection;
import entities.Annonce;
import entities.Categorie;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import services.Annonceservice;
import services.categorieservice;

/**
 * FXML Controller class
 *
 * @author siwar
 */
public class CategorieController implements Initializable {

    @FXML
    private TextField typefx;
    @FXML
    private TableColumn<Categorie, String> tablefx;
    @FXML
    private TableView<Categorie> tableview;
    @FXML
    private Label label;
    private Tab Annonce;
    private Tab statistiquesfx;
    private Tab statistiques;
    @FXML
    private Button stat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           tablefx.setCellValueFactory(new PropertyValueFactory<Categorie, String>("type"));
            Afficher();
        addButtonSupprimerToTable();
        addButtonModifierToTable();
        // TODO
       // annonce();
        
    }    
       private void addButtonSupprimerToTable() {
        TableColumn<Categorie, Void> colBtn = new TableColumn("");

        Callback<TableColumn<Categorie, Void>, TableCell<Categorie, Void>> cellFactory = new Callback<TableColumn<Categorie, Void>, TableCell<Categorie, Void>>() {
            @Override
            public TableCell<Categorie, Void> call(final TableColumn<Categorie, Void> param) {
                final TableCell<Categorie, Void> cell = new TableCell<Categorie, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setTextFill(Paint.valueOf("#f8f7f7"));
                        btn.setStyle("-fx-background-color: #6f1075");
                        btn.setOnAction(event -> {
                           Categorie data = getTableView().getItems().get(getIndex());
                            JFrame f = new JFrame();

                            int a = JOptionPane.showConfirmDialog(f, "Êtes-vous sûr?");
                            if (a == JOptionPane.YES_OPTION) {
                               categorieservice cs=new categorieservice();
        
                                cs.delete(data.getId());
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

        tableview.getColumns().add(colBtn);

    }
          private void addButtonModifierToTable() {
        TableColumn<Categorie, Void> colBtn = new TableColumn("");

        Callback<TableColumn<Categorie, Void>, TableCell<Categorie, Void>> cellFactory = new Callback<TableColumn<Categorie, Void>, TableCell<Categorie, Void>>() {
            @Override
            public TableCell<Categorie, Void> call(final TableColumn<Categorie, Void> param) {
                final TableCell<Categorie, Void> cell = new TableCell<Categorie, Void>() {


                    private final Button btnU = new Button("Modifier");

                    {
                        btnU.setTextFill(Paint.valueOf("#f8f7f7"));
                        btnU.setStyle("-fx-background-color: #6f1075");
                        btnU.setOnAction(event -> {
                           Categorie data = getTableView().getItems().get(getIndex());
                            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("modificationCategorie.fxml"));
                            Parent root;
                            try {
                                root = loader.load();
                               ModificationCategorieController pct = loader.getController();
                                pct.setId(data.getId());
                                
                                pct.setTexttype(data.getType());
                               
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

        tableview.getColumns().add(colBtn);

    }
  public void Annonce () {
        try {
           
            Parent node = FXMLLoader.load(getClass().getResource("/FXMLDocument.fxml"));
            Annonce.setContent(node);
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        categorieservice cs=new categorieservice();
        Categorie c = new Categorie(); 
        c.setType(typefx.getText());
        cs.addcategorie(c);
    }

    @FXML
    private void Afficher() {
        ObservableList<Categorie> cat =FXCollections. observableArrayList();
        try {
            String requete = "SELECT * FROM categorie";
            Statement st = new MyConnection ().getConnection()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Categorie c = new Categorie();
                c.setId(rs.getInt("id"));
                c.setType(rs.getString("Type"));
                
                cat.add(c);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println( cat);
        //return  cat;
        tableview.setItems(cat);
         
    }

   

    @FXML
    private void statistiques(ActionEvent event) throws IOException {
        stat.getScene().getWindow().hide();
         Parent node = FXMLLoader.load(getClass().getResource("statchart.fxml"));
         Scene scene = new Scene(node);
         Stage st  = new Stage();
         st.setScene(scene);
         st.show();
    }

   /* private void annonce() {
         try {
           
            Parent node = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            ann.setContent(node);
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }*/
    
}
