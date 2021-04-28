/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Users;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
public class AdminController implements Initializable {

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
Connection cnx;
  private ObservableList<Users> data;
    @FXML
    private TextField tfrecherche;
    @FXML
    private ImageView affiche;
    @FXML
    private Button btnQr;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        affiche();
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
    private void godash(ActionEvent event) throws IOException {
       Parent globalPane = FXMLLoader.load(getClass().getResource("/GUI/Stat.fxml"));
        Scene scene = new Scene(globalPane);
         Stage mainStage=new Stage();
        mainStage.setScene(scene);
        mainStage.show(); 
    }

    @FXML
    private void gouser(ActionEvent event) throws IOException {
         Parent globalPane = FXMLLoader.load(getClass().getResource("/GUI/Admin.fxml"));
        Scene scene = new Scene(globalPane);
         Stage mainStage=new Stage();
        mainStage.setScene(scene);
        mainStage.show();
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
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
                        fileOut.close();
                        System.out.println("Data is saved in excel file.");
                        rs.close();
                        
                
}
    }
    

