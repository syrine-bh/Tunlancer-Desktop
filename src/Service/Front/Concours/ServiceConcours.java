/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Front.Concours;

import Iservices.concours.IservicesConcours;
import entities.Concour;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import utils.MyConnection;

/**
 *
 * @author Hiba
 */
public class ServiceConcours implements IservicesConcours {

    private Connection connection;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    ObservableList<Concour> list = FXCollections.observableArrayList();

    private static String projectPath = System.getProperty("user.dir").replace("\\", "/");

    Concour concours;
    concoursHolder th = concoursHolder.getINSTANCE();

    @Override
    public Concour getConcours(int id) {
        System.out.println("teeeest get Concours");

        Concour c = null;
        ImageView img = null;
        try {
            String query = "SELECT * from concour WHERE id=" + id;

            Connection connection = MyConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = connection.createStatement().executeQuery(query);

            while (rs.next()) {
                Concour concour = new Concour();

                concour.setId(rs.getInt("id"));
                concour.setDescription(rs.getString("description"));
                concour.setNom(rs.getString("nom"));
                concour.setIsVideo(rs.getBoolean("is_video"));

//                String url = "file:///" + projectPath + "/src/coheal/resources/images/tasks/" + rs.getString("imageName");
//                img = new ImageView(url);
//                conco.setImg(img);
//                task.setCreatedAt(rs.getTimestamp("created_at"));
                c = concour;
            }

        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        return c;
    }

    @Override
    public List<Concour> searchConcourByName(String nom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Concour> ListConcours() {
        ArrayList<Concour> c = new ArrayList();
        ImageView img = null;
        System.out.println("TEST list");
        try {

            String requette;
            requette = "SELECT * FROM `concour`";

            Connection connection = MyConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement(requette);
            ResultSet rs = connection.createStatement().executeQuery(requette);

            while (rs.next()) {
                Concour concour = new Concour();
                concour.setId(rs.getInt("id"));
                concour.setDescription(rs.getString("description"));
                concour.setNom(rs.getString("nom"));
                concour.setSujet(rs.getString("sujet"));
                concour.setIsVideo(rs.getBoolean("is_video"));
                concour.setCreated_at(rs.getTimestamp("created_at"));
                concour.setDateDebut(rs.getDate("date_debut"));
                concour.setDateFin(rs.getTimestamp("date_fin"));

//                String url = "file:///" + projectPath + "/src/coheal/resources/images/tasks/" + rs.getString("img_url");
//                img = new ImageView(url);
//                c.setImg(img);
                System.out.println(concour);
                c.add(concour);
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage");
        }
        return c;
    }

    public Concour get(int id) {
        Concour c = null;
        String req = "select * from concour where id=?";
        try {
            PreparedStatement pst = connection.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                c = new Concour(rs.getInt("id"), rs.getString("description"), rs.getString("nom"), rs.getBoolean("is_video"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
    public ObservableList<Concour> getAll() throws SQLException{
         String req="select * from concour";
                     Connection connection = MyConnection.getInstance().getConnection();

        ObservableList<Concour> list=FXCollections.observableArrayList();
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
                list.add(new Concour(rs.getInt("id"),rs.getString("nom"),rs.getDate("date_debut"),rs.getTimestamp("date_fin")));
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

//        public ObservableList<Concour> getAll(){
//   Concour c=null;
//        String req="select * from concour ";
//
//        try {
//           PreparedStatement pst=con.prepareStatement(req);
//           ResultSet rs=pst.executeQuery();
//    
//            while(rs.next()){
//                list.add(new Concour(rs.getInt("id"),rs.getString("nom"),rs.getDate("date_debut"),rs.getDate("date_fin")));
//            }
//    ;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
}
