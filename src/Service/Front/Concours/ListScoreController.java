/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Front.Concours;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Concour;
import entities.Score;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Hiba
 */
public class ListScoreController implements Initializable {

    @FXML
    private TableView<Score> tabListS;
    @FXML
    private TableColumn<Score, String> pseudo;
    @FXML
    private TableColumn<Score, String>  score;
    @FXML
    private FontAwesomeIconView close;
    Connection connection;
        ObservableList<Score> TabViewListS = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableauScore();
    }

    private void loadTableauScore() {
        this.connection = MyConnection.getInstance().getConnection();
        try {
            TabViewListS = FXCollections.observableArrayList();
            String rq = "SELECT * FROM `score` WHERE quiz_id=1";

            ResultSet rs = connection.createStatement().executeQuery(rq);

            while (rs.next()) {
                TabViewListS.add(
                        new Score(rs.getInt("id"), rs.getString("pseudo"), rs.getString("score")));
            }

           
            this.pseudo.setCellValueFactory(new PropertyValueFactory<Score, String>("pseudo"));
            this.score.setCellValueFactory(new PropertyValueFactory<Score, String>("score"));
          
            this.tabListS.setItems(null);
            this.tabListS.setItems(this.TabViewListS);

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        

    }

  @FXML
    private void minAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) close.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void closeAction(MouseEvent event) {
//        Stage stage = new Stage();
//        stage = (Stage) lblTitre.getScene().getWindow();
//        stage.close();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/displayConcoursPage.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
