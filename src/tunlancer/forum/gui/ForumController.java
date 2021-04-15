/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.forum.gui;

import Models.Topics;
import Service.ServiceTopics;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.geometry.Insets;
import javafx.util.Callback;
import utils.MaConnexion;

/**
 * FXML Controller class
 *
 * @author cyrinaa belguith
 */
public class ForumController implements Initializable {

    @FXML
    private TableView<Topics> TopicTable;
    @FXML
    private TableColumn<Topics, String> titreCol;
    @FXML
    private TableColumn<Topics, String> contenuCol;
    @FXML
    private TableColumn<Topics, String> dateCol;
    @FXML
    private TableColumn<Topics, String> actionCol;
    
    Connection cnx=null;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Topics topics = null;
    
    ObservableList<Topics> TopicsList = FXCollections.observableArrayList();

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }    

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
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

    @FXML
    private void refreshTable(MouseEvent event) {
        try {
            TopicsList.clear();
            String query="SELECT * FROM `topics`";
            preparedStatement = cnx.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                TopicsList.add(new Topics(
                        resultSet.getString("titre"), 
                        resultSet.getString("contenu"),
                        resultSet.getDate("date")));
                TopicTable.setItems(TopicsList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ForumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadData() {
        
        cnx = MaConnexion.getInstance().getCnx();
        
        try {
            TopicsList.clear();
            String query="SELECT * FROM `topics`";
            preparedStatement = cnx.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                TopicsList.add(new Topics(
                        resultSet.getString("titre"), 
                        resultSet.getString("contenu"),
                        resultSet.getDate("date")));
                TopicTable.setItems(TopicsList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ForumController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        titreCol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        contenuCol.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        //add cell of button edit 
         Callback<TableColumn<Topics, String>, TableCell<Topics, String>> cellFoctory = (TableColumn<Topics, String> param) -> {
            // make cell containing buttons
            final TableCell<Topics, String> cell = new TableCell<Topics, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                Object item = TopicTable.getSelectionModel().getSelectedItem();
                                Topics t=(Topics) item;
                                ServiceTopics st= new ServiceTopics();
                                System.out.println(t.toString());
                                st.supprimerTopic(t);
                                
                                
                                
                                
//                            try {
//                                topics = TopicTable.getSelectionModel().getSelectedItem();
//                                String query = "DELETE FROM `Topics` WHERE `titre` ="+topics.getTitre();
//                                cnx = MaConnexion.getInstance().getCnx();
//                                preparedStatement = cnx.prepareStatement(query);
//                                preparedStatement.execute();
//                                
//                            } catch (SQLException ex) {
//                                System.out.println(ex.getMessage());
//                            }
                            }
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            topics = TopicTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/tunlancer/forum/gui/AddTopic.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                            
                            AddTopicController addTopicController = loader.getController();
                            addTopicController.setUpdate(true);
                            addTopicController.setTextField(topics.getId(), topics.getTitre(), topics.getContenu(),
                            topics.getDate().toLocalDate());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            

                           

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         actionCol.setCellFactory(cellFoctory);
         TopicTable.setItems(TopicsList);
        
    }
    
}
