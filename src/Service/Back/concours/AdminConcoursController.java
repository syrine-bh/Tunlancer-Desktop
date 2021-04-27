/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Back.concours;

import Service.Front.Concours.ServiceConcours;
import Service.Front.Concours.ServiceQuiz;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import entities.Concour;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import org.controlsfx.control.table.TableFilter;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Hiba
 */
public class AdminConcoursController implements Initializable {
   @FXML
    private AnchorPane moderationPane;
    @FXML
    private FontAwesomeIconView close;
    private TableColumn<?, String> titleCol;
    private TableColumn<?, String> descriptionCol;
    private TableColumn<?, ?> catgCol;
    private TableView<Concour> concoursTable;
    ServiceConcours st = new ServiceConcours();
    ServiceQuiz sq = new ServiceQuiz();
    Concour t = new Concour();
//    ServicePaidTask spt = new ServicePaidTask();
//    PaidTask pt = new PaidTask();
    double xOffset, yOffset;
    @FXML
    private PieChart pieChart;
    @FXML
    private LineChart<?, ?> lineChart;
//    private ServiceUserTask sut = new ServiceUserTask();
    private Pagination pagination;
    private int itemsPerPage = 5;
    private int from, to, size;
    ObservableList<Concour> DataList = FXCollections.observableArrayList();
    Connection connection;
    ObservableList<Concour> TabViewListC = FXCollections.observableArrayList();
    
    @FXML
    private Button btAjouter;
    @FXML
    private Button btSupprimer;
    @FXML
    private Button btModifier;
    @FXML
    private TableView<Concour> tabListC;
    @FXML
    private Button btRefresh;
    @FXML
    private TableColumn<?, ?> col_id;
    @FXML
    private TableColumn<?, ?> col_nom;
    @FXML
    private TableColumn<?, ?> col_sujet;
    @FXML
    private TableColumn<?, ?> col_categorie;
    @FXML
    private TableColumn<?, ?> col_dateDebut;
    @FXML
    private TableColumn<?, ?> col_dateFin;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     //----------------PieChart----------
        ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList(
                new PieChart.Data("Concours", st.ListConcours().size()),
                new PieChart.Data("Quiz", sq.ListQuestions().size()));
        pieChart.setTitle("Concours");
        pieChart.setData(valueList);
        pieChart.getData().forEach(data -> {
            String percentage = String.format("%.2f%%", (data.getPieValue() / 100));
            Tooltip toolTip = new Tooltip(percentage);
            Tooltip.install(data.getNode(), toolTip);
        });
//        //----------------LineChart----------
//        lineChart.setTitle("Number of participations by date");
//        XYChart.Series dataSeries = new XYChart.Series();
//        dataSeries.setName("test");
//        for (int i = 0; i < sut.getNbrParticipateByDate().size(); i++) {
//            dataSeries.getData().add(new XYChart.Data(String.valueOf(sut.getNbrParticipateByDate().get(i).getCreatedAt()), sut.getNbrParticipateByDate().get(i).getNbr()));
//        }
//        lineChart.getData().add(dataSeries);

        init();    }    
    
    
    public void init() {
//        ArrayList<Concour> concours = new ArrayList();
////        tasks = Stream.concat(st.ListConcours().stream(), .listPaidTask().stream())
////                .collect(Collectors.toList());
//        ObservableList<Concour> l = FXCollections.observableList(concours);
//        titleCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
////        DaysCol.setCellValueFactory(new PropertyValueFactory<>("numOfDays"));
////        catgCol.setCellValueFactory(new PropertyValueFactory<>("cat"));
////        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
//       concoursTable.setItems((ObservableList<Concour>) l);
//        TableFilter filter = new TableFilter(concoursTable);

     this.connection = MyConnection.getInstance().getConnection();
        try {
            TabViewListC = FXCollections.observableArrayList();
            String rq = "SELECT * FROM `concour`";

            ResultSet rs = connection.createStatement().executeQuery(rq);

            while (rs.next()) {
                TabViewListC.add(
                        new Concour(rs.getInt("id"), rs.getString("nom"), rs.getString("sujet"), rs.getString("categorie"),
                                rs.getDate("date_debut"), rs.getTimestamp("date_Fin")));
            }

  this.col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            this.col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            this.col_sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
            this.col_categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            this.col_dateDebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
            this.col_dateFin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
            this.tabListC.setItems(null);
            this.tabListC.setItems(this.TabViewListC);

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }


    }
    
    @FXML
    private void closeAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) concoursTable.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) concoursTable.getScene().getWindow();
        stage.setIconified(true);
    }


    
    private static void AlertBox(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @FXML
    private void ajouterConcours(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/AjouterConcours.fxml"));
            loader.load();
            Parent parent = loader.getRoot();
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UTILITY);
                    stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void DeleteConcours(ActionEvent event) throws SQLException {
        boolean choix0 = tabListC.getSelectionModel().isEmpty();

        if (!choix0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression !!");
            alert.setHeaderText(null);
            alert.setContentText("Voulez-vous vraiment de supprimer cette ligne");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                Concour tab1 = tabListC.getSelectionModel().getSelectedItem();
                String querry = "DELETE FROM `concour` WHERE `id` = " + tab1.getId();

                connection = MyConnection.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(querry);
                ps.execute();
                System.out.println("Supprimer avec success  !!!");

            }
        } else if (choix0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selectionner");
            alert.setHeaderText(null);
            alert.setContentText("Selectionner une ligne ");
            alert.showAndWait();
        }

    }

    @FXML
    private void UpdateConcours(ActionEvent event) {
        
            boolean choix = tabListC.getSelectionModel().isEmpty();


            if(!choix) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de votre modification ");
            alert.setHeaderText(null);
            alert.setContentText("Voulez-vous vraiment de modifier cette ligne ??");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {


                try {

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/FXML/UpdateConcours.fxml"));
                    loader.load();

                   Concour tab2 = tabListC.getSelectionModel().getSelectedItem();
                 UpdateConcoursController quizC = loader.getController();


//                    quizC.text( tab2.getId() ,
//                    tab2.getSujet(), tab2.getNom(), tab2.getDescription(),tab2.getDateDebut().toLocalDate() 
////                    ,tab2.getDateFin().toLocalDateTime()
//                            ,tab2.getImageName(),tab2.getCategorie()
//                    );

                    
                    
        

                    
                    Parent parent = loader.getRoot();
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UTILITY);
                    stage.show();

                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }  else if (choix) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Selectionner");
                alert.setHeaderText(null);
                alert.setContentText("Selectionner une ligne ");
                alert.showAndWait();
            }
    }
@FXML
    private void RefreshConcours(ActionEvent event) {
init();    }
}
