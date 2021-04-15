/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Interfaces.IServiceTopic;
import Models.Topics;
import com.mysql.cj.xdevapi.PreparableStatement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MaConnexion;

/**
 *
 * @author cyrinaa belguith
 */
public class ServiceTopics implements IServiceTopic{
    
    Connection cnx;

    public ServiceTopics() {
        cnx = MaConnexion.getInstance().getCnx();
    }
    
    ObservableList<Topics> Topics = FXCollections.observableArrayList();

    
    

    @Override
    public void AjouterTopic(Topics t){
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());         

        try {
            String query="INSERT into topics(titre, contenu, date)" + "VALUES(?,?, ?)";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setString(1, t.getTitre());
            preparedStatement.setString(2, t.getContenu());
            preparedStatement.setString(3, date);
            preparedStatement.executeUpdate();
            System.out.println("Topic ajouté");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
                
        
    }
    
    public void supprimerTopic(Topics t) {
        try {
            String requete = "DELETE FROM topics WHERE titre=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getTitre());
            pst.executeUpdate();
            System.out.println("Topic supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Topics> AfficherTopic()throws SQLException{
        
            String query="select * from topics";
            Statement st=MaConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                Topics t = new Topics();
                t.setTitre(rs.getString("titre"));
                t.setContenu(rs.getString("contenu"));
                t.setDate(rs.getDate("date"));
                Topics.add(t);
            }
        
        return Topics;
    }
//    public List<Topics> AfficherTopic() throws SQLException{
//        
////            ObservableList<Topics> TopicList = FXCollections.observableArrayList();
////            Statement stm = cnx.createStatement();
////            String query= "SELECT * from topics";
////            
////            ResultSet rst = stm.executeQuery(query);
//////            List<Topics> topics= new ArrayList<>();
////            while(rst.next()){
////                Topics t = new Topics();
////                t.setId(rst.getInt("id"));
////                t.setTitre(rst.getString("titre"));
////                t.setContenu(rst.getString("contenu"));
////                t.setDate(rst.getDate("date"));
////                TopicList.add(t);
////            }
////        
////        return TopicList;    
//    }
    
}
