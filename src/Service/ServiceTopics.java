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
import tunlancer.forum.gui.TopicsHolder;
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
    List<Topics> ListT = new ArrayList();
    Topics t= new Topics();
    TopicsHolder th = TopicsHolder.getInstance();



    
    

    @Override
    public void AjouterTopic(Topics t){
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());         

        try {
            String query="INSERT into topics(titre, contenu, date, user_id)" + "VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setString(1, t.getTitre());
            preparedStatement.setString(2, t.getContenu());
            preparedStatement.setString(3, date);
            preparedStatement.setInt(4, t.getUser_id());
            preparedStatement.executeUpdate();
            System.out.println("Topic ajouté");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
                
        
    }
    
    public void AddNote(Topics t){
        int id=4;
        try {
            String query="UPDATE topics set note='"+1+"'where id='"+id+"'";

            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            //preparedStatement.setInt(1, t.getNote());
            preparedStatement.executeUpdate();
            System.out.println("Note ajouté");
        } catch (SQLException ex) {
            System.out.println("failed to add note"+ex.getMessage());
        }


        
    }
    
    public void modifierTopic(Topics t) throws SQLException{
        Statement stm = cnx.createStatement();
        String query = "UPDATE topics SET titre= '"+t.getTitre()+"', contenu='"+t.getContenu()+"'";
        stm.executeUpdate(query);  
    }
    
    public void supprimerTopic(Topics t) {
        try {
            String requete = "DELETE FROM topics WHERE contenu=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getContenu());
            pst.executeUpdate();
            System.out.println("Topic supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Topics> AfficherTopic()throws SQLException{
        
            String query="select * from topics ORDER BY `date` DESC";
            Statement st=MaConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                Topics t = new Topics();
                t.setId(rs.getInt("id"));
                t.setTitre(rs.getString("titre"));
                t.setContenu(rs.getString("contenu"));
                t.setDate(rs.getDate("date"));
                Topics.add(t);
            }
        
        return Topics;
    }
    
    
    @Override
    public List<Topics> getAllTopics() throws SQLException{
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String query="SELECT * from topics";
        Statement stm = cnx.createStatement();
        ResultSet rst= stm.executeQuery(query);
        while(rst.next()){
            Topics topics = new Topics();
            topics.setId(rst.getInt("id"));
            topics.setTitre(rst.getString("titre"));
            topics.setContenu(rst.getString("contenu"));
            topics.setDate(rst.getDate("date"));
            ListT.add(topics); 
        }
        return ListT;
    }
    
    @Override
    public List<Topics> getAll() throws SQLException{
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        try{
        String query="SELECT * from topics";
        Statement stm = cnx.createStatement();
        ResultSet rst= stm.executeQuery(query);
        while(rst.next()){
            Topics topics = new Topics();
            topics.setId(rst.getInt("id"));
            topics.setTitre(rst.getString("titre"));
            topics.setContenu(rst.getString("contenu"));
            topics.setDate(rst.getDate("date"));
            ListT.add(topics); 
        }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTopics.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ListT;
           
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

    @Override
    public void supprimerTopic(int id) throws SQLException{
        String requete = "DELETE FROM topics WHERE id="+id;
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            //pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Topic supprimé !");

        } catch (SQLException ex) {
            System.err.println("non supprimé"+ex.getMessage());
        }
    }

    public Topics AfficherTopicDetails(int id) {
        String query = "SELECT * from topics WHERE id="+id+"";
        
        Topics t= new Topics();
       try {
           Statement stm = cnx.createStatement();
           ResultSet rst = stm.executeQuery(query);
           while(rst.next()) {
               t.setId(rst.getInt("id"));
               t.setTitre(rst.getString("Titre"));
               t.setContenu(rst.getString("Contenu"));
               t.setDate(rst.getDate("date"));
               t.setUser_id(rst.getInt("user_id"));
           
           }
           
       } catch (SQLException ex) { 
           System.out.println("probleme de"+ex.getMessage());

       }
            
           return t; 
    }
    
    public int getNbrTopic() {
        String sql="SELECT COUNT(*) FROM topics";
        ResultSet rs;
        int countIdRec=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdRec= res.getInt(1);
                        }
        }catch(SQLException e) {
        }
        return countIdRec;
    }

    @Override
    public int updateTopic(int id, Topics topics) throws SQLException {
        try {
            
            Connection cnx = MaConnexion.getInstance().getCnx();
            String update = "UPDATE topics SET titre=?, Contenu=?, date=? where id=?;";
            PreparedStatement stm = cnx.prepareStatement(update);
            stm.setString(1, topics.getTitre());
            stm.setString(2, topics.getContenu());
            stm.setDate(3, topics.getDate());
            
           stm.executeUpdate(update);

        } catch (SQLException ex) {
            System.out.println("not updated"+ex.getMessage());
        }
        return id;
    }
    
    public Topics get(int id) {
        Topics t = null;
        String req = "select * from topics where id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                t = new Topics(rs.getString("titre"), rs.getString("contenu"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }
    
      
    
}
