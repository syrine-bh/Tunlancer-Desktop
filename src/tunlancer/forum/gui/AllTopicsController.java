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
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tunlancer.forum.gui.TopicDetailsController;
import utils.MaConnexion;

/**
 * FXML Controller class
 *
 * @author cyrinaa belguith
 */
public class AllTopicsController implements Initializable {

    @FXML
    private StackPane contentPane;
    @FXML
    private VBox pnl_scroll;

    PreparedStatement preparedStatement = null;
    String resultSet = null;
    List<Topics> ListT = new ArrayList();
    IServiceTopic Top = new ServiceTopics();
    Topics t;
    TopicsHolder th = TopicsHolder.getInstance();
    Connection cnx = MaConnexion.getInstance().getCnx();

    List<Topics> ListTa;
    String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    
    

    public AllTopicsController() {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addTopicsListScreen();
        refresh();
    }

    public void supprimerTopic(MouseEvent e, Topics t) throws SQLException {
        FXMLLoader loader = new FXMLLoader(AllTopicsController.this.getClass().getResource("/tunlancer/forum/gui/TopicDetails.fxml"));

        TopicDetailsController topic = loader.getController();
        if (e.getSource() == topic.deletebtnTopic) {
            IServiceTopic ST = new ServiceTopics();
            ST.supprimerTopic(t.getId());
            refresh();

        }

    }

    public void refresh() {

        try {
            pnl_scroll.getChildren().clear();
            ListT = Top.AfficherTopic();
            System.out.println(ListT);

            System.out.println(ListT);
            if (!ListT.isEmpty()) {
                Node nodes[] = new Node[ListT.size() + 1];
                ListT.forEach((Topics top) -> {
                    try {
                        int i = ListT.indexOf(top);
                        i++;
                        FXMLLoader loader = new FXMLLoader(AllTopicsController.this.getClass().getResource("/tunlancer/forum/gui/TopicDetails.fxml"));
                        nodes[i] = loader.load();
                        TopicDetailsController item = loader.getController();
                        item.setTitre(top.getTitre());
                        item.setContenu(top.getContenu());
                        item.setDate(date);
                        pnl_scroll.getChildren().addAll(nodes[i]);
                        EventHandler<MouseEvent> supprimerTopic = (MouseEvent e) -> {

                            if (e.getSource() == item.deletebtnTopic) {
                                try {
                                    IServiceTopic ST = new ServiceTopics();
                                    ST.supprimerTopic(top.getId());
                                    refresh();
                                } catch (SQLException ex) {
                                    Logger.getLogger(AllTopicsController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            };
                            refresh();
                        };

                        EventHandler<MouseEvent> editHandler = (MouseEvent e) -> {
                            if (e.getSource() == item.EditTopic) {
                                System.out.print("here");
                                //                                    FXMLLoader loader2 = new FXMLLoader(AllTopicsController.this.getClass().getResource("/tunlancer/forum/gui/EditTopic.fxml"));
//                                    contentPane.getChildren().clear();
//                                    Parent root = (Parent) loader2.load();
//                                    EditTopicController et = loader2.getController();
//                                    et.setTopics(top);
//                                    et.setTopicId(top.getId());
//                                    System.out.println(top.getId());
//                                    et.setTitre(top.getTitre());
//                                    et.setContenu(top.getContenu());
//                                    contentPane.getChildren().add(root);
                                FXMLLoader loader2 = new FXMLLoader();
                                loader2.setLocation(getClass().getResource("/tunlancer/forum/gui/EditTopic.fxml"));
                                try {
                                    loader2.load();
                                } catch (IOException ex) {
                                    System.out.println("failed to redirect to"+ex.getMessage());
                                }
                                EditTopicController et = loader2.getController();
                                et.setTopics(top);
                                et.setTopicId(top.getId());
                                System.out.println(top.getId());
                                et.setTitre(top.getTitre());
                                et.setContenu(top.getContenu());
                                Parent parent = loader2.getRoot();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(parent));
                                stage.initStyle(StageStyle.UTILITY);
                                stage.show();
                            }
                        };
                        item.deletebtnTopic.addEventHandler(MouseEvent.MOUSE_CLICKED, supprimerTopic);
                        item.EditTopic.addEventHandler(MouseEvent.MOUSE_CLICKED, editHandler);

                    } catch (IOException ex) {
                        System.out.println("not ok .." + ex.getMessage());
                    }
                });

            }
        } catch (SQLException ex) {
            System.out.println("à vérifier" + ex.getMessage());
        }

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
            refresh();

        } catch (IOException ex) {
            Logger.getLogger(ForumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void AjouterRep(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/tunlancer/forum/gui/AddReply.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            refresh();
        } catch (IOException ex) {
            System.out.println("probleme" + ex.getMessage());
        }
    }

    private void addTopicsListScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tunlancer/forum/gui/TopicDetails.fxml"));
        Node node;
        try {
            node = fxmlLoader.load();
            contentPane.getChildren().add(node);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void refreshNodes(MouseEvent event) {
        refresh();
    }

}
