/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.ServiceUser;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import Models.Users;
import com.jfoenix.controls.JFXTextField;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.Chart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import utils.MyConnection;
/**
 * FXML Controller class
 *
 * @author asus
 */
public class UsersController implements Initializable {

    @FXML
    private PieChart pieChart;
    @FXML
    private AnchorPane statgender = new AnchorPane(); 
    
     private ObservableList data_pie;
       private ObservableList data_pie1;
    
    
    private List<Chart> charts = new ArrayList<>();
     int idf;
    private Pane paneview;
    private Button user;
  Connection cnx = MyConnection.getInstance().getConnection();
    @FXML
    private StackPane contentPane;
    @FXML
    private AnchorPane pageHolder;
    @FXML
    private PieChart pieChart1;
    @FXML
    private Label label;
    @FXML
    private AnchorPane slider;
    @FXML
    private StackPane contentPane1;
    @FXML
    private BarChart<String, Integer> bar;
    @FXML
    private AnchorPane pageHolder1;
    @FXML
    private TableView<Users> tvusers;
    @FXML
    private TableColumn<Users, Integer> colid;
    @FXML
    private TableColumn<Users, String> colnom;
    @FXML
    private TableColumn<Users, String> colprenom;
    @FXML
    private TableColumn<Users, String> colemail;
    @FXML
    private TableColumn<Users, Integer> coltel;
    @FXML
    private TableColumn<Users, String> colpays;
    @FXML
    private TableColumn<Users, Integer> colage;
    @FXML
    private TableColumn<Users, String> colsexe;
    @FXML
    private JFXTextField tfrecherche;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            affiche();
            createPie();
             Load();
             //statsexe();
             
             pieChart = new PieChart(data_pie);
               pieChart1 = new PieChart(data_pie1);

        //pieChart.getData().addAll(data_pie);
        contentPane1.getChildren().add(pieChart1);
        contentPane.getChildren().add(pieChart);
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
        
        XYChart.Series set1 = new XYChart.Series<>();
            XYChart.Series set2 = new XYChart.Series<>();
         // List<Annonce> ANNList = new ArrayList<>();
        try {
            String requete = "SELECT u.age ,COUNT(u.age) FROM users u  GROUP BY u.age ";
              XYChart.Series<String,Integer> series=new XYChart.Series<>();
            Statement st = new MyConnection ().getConnection()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()) {
               
            series.getData().add(new XYChart.Data<>( rs.getString(1),rs.getInt(2)));
                bar.getData().add(series);
              
           
            }
        } catch (SQLException ex) {    
            Logger.getLogger(StatController .class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    void initData() throws SQLException {
   
      
    }
    
   public void createPie() throws SQLException {
         try {
            data_pie = FXCollections.observableArrayList();
            
            PreparedStatement pt = cnx.prepareStatement("select DISTINCT pays,COUNT(pays) as nbr from users group by pays");
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                //adding data on piechart data
                data_pie.add(new PieChart.Data(rs.getString("pays"),rs.getInt(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
      
   } 

    private void Load() throws SQLException {
        try {
            data_pie1 = FXCollections.observableArrayList();
            
            PreparedStatement pt = cnx.prepareStatement("select DISTINCT c.titre,COUNT(c.titre) as nbr from competence c ,users u where u.id=c.user_id group by c.titre");
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                //adding data on piechart data
                data_pie1.add(new PieChart.Data(rs.getString(1),rs.getInt(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
         
    }

 
    
        // TODO
        
        
    
    private void Load(MouseEvent event) {
         paneview.getChildren().clear();
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
      Connection cnx = MyConnection.getInstance().getConnection();
       try{
           
            PreparedStatement pt=cnx.prepareStatement("select  user_id from competence");
          
            ResultSet rs= pt.executeQuery();
            int somme=0;
            while(rs.next()){
                somme=somme+rs.getInt(1);
            }
        
                  PreparedStatement pt2=cnx.prepareStatement("select titre,user_id from competence");
          
            ResultSet rs2= pt2.executeQuery();
            while(rs2.next()){
         list.add(new PieChart.Data(rs2.getString(1),rs2.getInt(2)));

            }
            } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
   
        PieChart Chart= new PieChart(list);
         paneview.getChildren().add(Chart);
        
    }

    private void godash(ActionEvent event) throws IOException {
        Parent globalPane = FXMLLoader.load(getClass().getResource("/GUI/Stat.fxml"));
        Scene scene = new Scene(globalPane);
         Stage mainStage=new Stage();
        mainStage.setScene(scene);
        mainStage.show();
    }

    private void gouser(ActionEvent event) throws IOException {
         user.getScene().getWindow().hide();
         Parent globalPane = FXMLLoader.load(getClass().getResource("/GUI/Admin.fxml"));
        Scene scene = new Scene(globalPane);
         Stage mainStage=new Stage();
        mainStage.setScene(scene);
        mainStage.show();
        
    }

    
      /**
     *
     * @throws SQLException
     */
    public void statsexe() throws SQLException {
        PieChart pie = new PieChart();
        ObservableList<PieChart.Data> data
                = FXCollections.observableArrayList();
        ServiceUser su = ServiceUser.getInstance();
        ResultSet set = su.getSexe();
        int counter;
        int hommes = 0, femmes = 0;
        while (set.next()) {
            counter = set.getInt("sexe");

            switch (counter) {
                case 1:
                    hommes++;
                    break;
                case 0:
                    femmes++;
                    break;
            }
        }

        data.addAll(new PieChart.Data("femme", hommes),
                new PieChart.Data("homme", femmes)
        );

        pie.setData(data);
        pie.setTitle("Users Men vs Women");
        statgender.getChildren().add(pie);
        charts.add(pie);
    }
    @FXML
    private void dashboardPageAction(MouseEvent event) {
    }

    @FXML
    private void usersPageAction(MouseEvent event) {
    }

    @FXML
    private void offresPageAction(MouseEvent event) {
    }

    @FXML
    private void recrutementsPageAction(MouseEvent event) {
    }

    @FXML
    private void concoursPageAction(MouseEvent event) {
    }

    @FXML
    private void forumsPageAction(MouseEvent event) {
    }

    @FXML
    private void publicationsPageAction(MouseEvent event) {
    }

   
    @FXML
    private void affiche(MouseEvent event) {
        
      ObservableList<Users> Users = affiche();
      colid.setCellValueFactory(new PropertyValueFactory<>("id"));
      colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
      colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
      coltel.setCellValueFactory(new PropertyValueFactory<>("tel"));
       colemail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        colpays.setCellValueFactory(new PropertyValueFactory<>("pays"));

        colage.setCellValueFactory(new PropertyValueFactory<>("age"));
       colsexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
      
    tvusers.setItems(Users);
    }
    
    private ObservableList<Users> affiche() {
        
        ObservableList<Users> Users =FXCollections. observableArrayList();
      try {
        String query = "select * from users";
        
	 Statement st = MyConnection.getInstance().getConnection()
                    .createStatement();
            ResultSet rs =  st.executeQuery(query);
	
	while (rs.next()) {
	   Users ex =new Users();
	    ex.setId(rs.getInt("id"));
	    ex.setNom(rs.getString("nom"));
            ex.setPrenom(rs.getString("prenom"));
            ex.setTel(rs.getInt("tel"));
            ex.setEmail(rs.getString("email"));
            ex.setRole(rs.getString("role"));
            ex.setPays(rs.getString("pays"));
            ex.setSexe(rs.getString("sexe"));
            ex.setAge(rs.getInt("age"));
	    Users.add(ex);
        }
       
	 } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	return Users;
        }

    @FXML
    private void delete(MouseEvent event) throws SQLException {
        Users tab1 = tvusers.getSelectionModel().getSelectedItem();
                String querry = "DELETE FROM users WHERE id = " +tab1.getId();

            cnx= MyConnection.getInstance().getConnection();
                PreparedStatement ps = cnx.prepareStatement(querry);
                ps.execute();
                System.out.println("user supprimé avec succés  !!!");
    }



    @FXML
    private void search1(KeyEvent event) {
              colid.setCellValueFactory(new PropertyValueFactory<>("id"));
            
              ObservableList<Users> oblist=FXCollections.observableArrayList();
           
        try {
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM users WHERE id LIKE  '%" + tfrecherche.getText() + "%'"
                        + " UNION SELECT * FROM users WHERE email LIKE '%" + tfrecherche.getText() + "%'"
                        + " UNION SELECT * FROM users WHERE nom LIKE '%" + tfrecherche.getText() + "%'"
                        + " UNION SELECT * FROM users WHERE prenom LIKE '%" + tfrecherche.getText() + "%'"
                       );
                               
            while (rs.next()) {
               
              oblist.add(new Users (
                          rs.getInt(1),
                          rs.getString(2),
                          rs.getString(3),
                          rs.getInt(4),
                          rs.getString(5),
                          rs.getString(6),
                          rs.getString(7),
                          rs.getString(8),
                          rs.getString(9),
                            rs.getInt(10),
                          rs.getInt(11),
                          rs.getInt(12)));
               
                       }

        } catch (SQLException ex) {
            System.err.println("ERROR" + ex);

        }
        tvusers.setItems(oblist);
    }

   
   
    
    
     @FXML
    private void toExcel(ActionEvent event) throws SQLException, FileNotFoundException, IOException {
                       Statement  ste = cnx.createStatement();
                        ResultSet rs = ste.executeQuery("Select * from users");

                         HSSFWorkbook wb = new HSSFWorkbook();
                        HSSFSheet sheet = wb.createSheet("Liste des utilisateurs");
                        HSSFRow rowhead = sheet.createRow((short) 0);
                        rowhead.createCell((short) 0).setCellValue("id");
                        rowhead.createCell((short) 1).setCellValue("Nom");
                        rowhead.createCell((short) 2).setCellValue("Prenom");
                        rowhead.createCell((short) 3).setCellValue("Email");
                        rowhead.createCell((short) 4).setCellValue("Tel");
                        rowhead.createCell((short) 5).setCellValue("Pays");
                        rowhead.createCell((short) 6).setCellValue("Age");
                        

                        int index = 1;
                        while (rs.next()) {

                                HSSFRow row = sheet.createRow((short) index);
                                row.createCell((short) 0).setCellValue(rs.getInt("id"));
                                row.createCell((short) 1).setCellValue(rs.getString("Nom"));
                                row.createCell((short) 2).setCellValue(rs.getString("Prenom"));
                             
                                row.createCell((short) 3).setCellValue(rs.getString("Email"));
                                row.createCell((short) 4).setCellValue(rs.getInt("Tel"));
                                row.createCell((short) 5).setCellValue(rs.getString("Pays"));
                                row.createCell((short) 6).setCellValue(rs.getInt("Age"));
                                      index++;
                        }
                        FileOutputStream fileOut = new FileOutputStream("C:\\Users\\asus\\Desktop\\Tunlancer-Desktop\\RapportUsers.xls");
        try {
            wb.write(fileOut);
        } catch (IOException ex) {
            Logger.getLogger(GUI.AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
                        fileOut.close();
                        System.out.println("Data is saved in excel file.");
                        rs.close();
                        
                
}

    
}

  

