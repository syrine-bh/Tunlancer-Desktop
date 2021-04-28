/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Interfaces.IServiceReplies;
import Models.Replies;
import Models.Topics;
import Models.Users;
import com.mysql.cj.xdevapi.Result;
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
public class ServiceReplies implements IServiceReplies{
    
    Connection cnx;
    private PreparedStatement ps;


    public ServiceReplies() {
        cnx = MaConnexion.getInstance().getCnx();
    }
    
    
    @Override
     public void AjouterRep(Replies rep){
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());         

        try {
            cnx = MaConnexion.getInstance().getCnx();
            String query="INSERT into replies(contenu, created_at, topic_id, user_id)" + "VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setString(1, rep.getContenu());
            preparedStatement.setString(2, date);
            preparedStatement.setInt(3, rep.getTopic_id());
            preparedStatement.setInt(4, rep.getUser_id());
            preparedStatement.executeUpdate();
            System.out.println("commentaire ajouté");
        } catch (SQLException ex) {
            System.err.println("probleme de .."+ex.getMessage());
        }
    }
     
     public void DeleteRep(int rep){
         
        try {
            String query="DELETE FROM replies` WHERE id=?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setInt(1, rep);
            preparedStatement.executeUpdate();
            System.out.println("commentaire supprimé avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     }
     
     public void updateRep(Users user, Topics t, String contenu) throws SQLException{
         String query="UPDATE replies SET contenu='"+contenu+"' where topics_id='"+t.getId()+"' AND user_id='"+user.getId()+"'";  
         PreparedStatement preparedStatement = cnx.prepareStatement(query);
         preparedStatement.executeUpdate(query);
     }
     
     public List<Replies> afficherRep(Topics t) throws SQLException{
         String query="SELECT * from replies where topic_id='"+t.getId()+"'";
         PreparedStatement preparedStatement = cnx.prepareStatement(query);
         ResultSet rst= preparedStatement.executeQuery(query);
         List<Replies> replies = new ArrayList<>();
         while(rst.next()){
             Replies reply = new Replies();
             reply.setId(rst.getInt("id"));
             reply.setUser_id(rst.getInt("user_id"));
             reply.setTopic_id(rst.getInt("topic_id"));
             reply.setContenu(rst.getString("contenu"));
             replies.add(reply);
         }
         return replies;
     }
     
     public ObservableList<Replies> AfficherReplies(int topic_id) {
       
            ObservableList<Replies> replies = FXCollections.observableArrayList();

        try {
            Statement stm =cnx.createStatement();
//           
            String query="SELECT * FROM replies where topic_id="+topic_id;
            System.out.println(query);
            ResultSet rst =stm.executeQuery(query);
            while (rst.next())
            {
                Replies r = new Replies();
                r.setId(rst.getInt("id"));
                r.setContenu(rst.getString("contenu"));
                r.setCreated_at(rst.getDate("created_at"));
                replies.add(r);
                
              
                
            }
        } catch (SQLException ex) {
            System.out.println("erreur"+ex.getMessage());
        }
            return replies;
    }

    public void SupprimerCommentaire(Replies r) {
 try {
                 String query = "delete from `replies` where `id`="+r.getId();
                 PreparedStatement pst = cnx.prepareStatement(query);
                 pst.execute();
             } catch (SQLException ex) {
                 Logger.getLogger(ServiceReplies.class.getName()).log(Level.SEVERE, null, ex);
             }    }

    public void ModifierCommentaire(Replies r) {
        try {
                 String query = "UPDATE `replies` SET `Contenu`= '"+r.getContenu()+"' WHERE id="+r.getId();
                 
                 PreparedStatement pst = cnx.prepareStatement(query);
                 pst.execute();
             } catch (SQLException ex) {
                 Logger.getLogger(ServiceReplies.class.getName()).log(Level.SEVERE, null, ex);
             }

    }

    public void like(Replies com) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Replies> afficher() {
        List <Replies> replies = new ArrayList<>();
        try {
             Statement stm =cnx.createStatement();
//           
            String query="SELECT * FROM replies ";
            System.out.println(query);
            ResultSet rst =stm.executeQuery(query);
            while (rst.next())
            {
                Replies r = new Replies();
                r.setId(rst.getInt("id"));
                r.setContenu(rst.getString("contenu"));
                r.setCreated_at(rst.getDate("created_at"));
                replies.add(r);
                
              
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return replies;
    }

    public void modifier(Replies co) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void AjouterReponse(Replies r, Topics t) {
        try {
            Statement stm =cnx.createStatement();
            String query="INSERT INTO `replies`(`id`, `Contenu`, `created_at`,`topic_id`, `user_id`) VALUES ("+'"'+r.getId()+'"'+","+'"'+r.getContenu()+'"'+","+'"'+r.getCreated_at()+'"'
                  +","+'"'+t.getId()+'"'+","+'"'+r.getUser_id()+'"'+")";
           
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTopics.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
}

    public void Ajouter(Replies r, int topicsId) {
        try {
            cnx = MaConnexion.getInstance().getCnx();
            Statement stm =cnx.createStatement();
            String query="INSERT INTO `replies`(`id`, `Contenu`, `created_at`,`topic_id`, `user_id`) VALUES ("+'"'+r.getId()+'"'+","+'"'+r.getContenu()+'"'+","+'"'+r.getCreated_at()+'"'
                  +","+'"'+topicsId+'"'+","+'"'+r.getUser_id()+'"'+")";
           
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("failed to add comment"+ex.getMessage());
        }
    }
    
    
     
    public List<Replies> getCommentaires(int topics_id) {
        List<Replies> Replies = new ArrayList<>();
        try
        {
            String req2 = "SELECT * FROM replies INNER JOIN topics ON replies.topic_id = topics.id WHERE topic_id = ? ;";
            ps = cnx.prepareStatement(req2);
            ps.setInt(1, topics_id);
            ResultSet res = ps.executeQuery(req2);
            while(res.next())
            {
                Replies.add(new Replies(res.getInt("id"), res.getString("contenu"), res.getDate("created_at"), res.getInt("topic_id"), res.getInt("user_id")));
            }
        }catch(SQLException e)
        {System.out.println("failed to get comments"+e);}
        return Replies ;
    }
    
    
    public void addCommentaire(Replies C) {
        try
        {
            String req1 = "insert into replies(contenu, created_at,topic_id, user_id) values(?, ?, ?, ?)" ;
            ps = cnx.prepareStatement(req1) ;
            ps.setString(1, C.getContenu()) ;
            ps.setDate(2, C.getCreated_at());
            ps.setInt(3, C.getTopic_id()) ;
            ps.setInt(4, C.getUser_id()) ;
            ps.executeUpdate() ;
        }catch(SQLException e)
        {
            System.out.println("failed to add :"+e);
        }
    }
    
    
    public int getIdByTopic(int id) throws SQLException {
        int nb = 0;
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM `replies` WHERE topic_id'" + id + "';");
        while (rs.next()) {
            nb = rs.getInt("id");
            return nb;
        }
        return nb;
    }
}
