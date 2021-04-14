/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entities.Concour;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import utils.MyConnection;
import entities.Participation;
import entities.Users;
import entities.Video;
import java.sql.SQLException;
import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Hiba
 */
public class ParticipationServices {
    

    private Connection connection;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public ParticipationServices() {
            Connection connection = MyConnection.getInstance().getCnx();}
    

    public void create(Participation p, Video v){

                long id=0;
        String req="INSERT INTO `video`(`url`, `title`, `publish_date`, `owner`) values(?,?,?,(select id from users where nom=?))";
        try {
            ResultSet rs = connection.createStatement().executeQuery(req);

            pst=connection.prepareStatement(req,Statement.RETURN_GENERATED_KEYS);
            pst.setString(1,v.getUrl());
            pst.setString(2,v.getTitle());
            pst.setTimestamp(3,v.getPublish_date());
            pst.setObject(4,v.getOwner().getNom());
            pst.executeUpdate();
                     if (rs.next()) {
                  id = rs.getLong(1);
                 
}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String req2="INSERT INTO `participation`(`concour_id`, `video_id`, `date_participation`, `user_id`) "
                + "values((select id from concour where id=?),(select id from video where id=?),?,(select id from user where nom=?))";
        try {
            pst=connection.prepareStatement(req2);
            pst.setObject(1,p.getConcourId());
            pst.setObject(2,p.getUserId());
            pst.setTimestamp(3, (Timestamp) p.getDateParticipation());
            pst.setObject(4,id);
            
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
//    public void  delete(Video v){
//        String req="delete from participation where video_id=?";
//        try {
//            pst=connection.prepareStatement(req);
//            pst.setInt(1,v.getId());
//
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        String req2="delete from video where id=?";
//        try {
//            pst=connection.prepareStatement(req2);
//            pst.setInt(1,v.getId());
//
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public void update( Video v){
//        String req="update video set url=?,title=? where id=?";
//        try {
//            pst=connection.prepareStatement(req);
//            pst.setString(1,v.getUrl());
//            pst.setString(2,v.getTitle());
//            pst.setInt(3,v.getId());
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    
//        }
//     public ObservableList<Video> getAll(Concour c){
//      String req="select * from video inner join user on(video.owner=user.id) where video.id in(select video_id from participation where concour_id=?)";
//      ObservableList<Video> list=FXCollections.observableArrayList();
//      try {
//             pst=connection.prepareStatement(req);
//            pst.setInt(1,c.getId());
//            rs=pst.executeQuery();
//            
//            while(rs.next()){
//                
//                list.add(new Video(rs.getInt("id"),rs.getString("url"),rs.getString("title"),rs.getTimestamp("publish_date"),  
//                                                new Users(0, req, req, 0, req, req, req, req, true, 0, req);
//
////                        new Users(rs.getInt("user.id"),rs.getString("username"),rs.getString("email"), rs.getString("adresse"),rs.getString("sexe"), rs.getString("name"), rs.getString("first_name"),rs.getString("telephone_number"),rs.getString("roles"))));
//            }
//    
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//     }
//     public competition_participant getWinners(int vid_id){
//      String req="select * from competition_participant "
//              + "inner join competition on(competition.id=competition_participant.competition_id) "
//              + "inner join user on(competition_participant.user_id=user.id) "
//              + "inner join video on(competition_participant.video_id=video.id) "
//             
//              + " where competition_participant.video_id = ?";
//              
//    competition_participant cp=null;
//      try {
//             pst=connection.prepareStatement(req);
//            pst.setInt(1,vid_id);
//            rs=pst.executeQuery();
//            
//            if(rs.next()){
//                
//                cp= (new competition_participant(new Competition(rs.getInt("competition.id"),rs.getString("subject"), rs.getTimestamp("competition_date"), rs.getTimestamp("competition_end_date"))
//                        , 
//                         new User(rs.getInt("user.id"),rs.getString("username"),rs.getString("email"), rs.getString("adresse"),rs.getString("sexe"), rs.getString("name"), rs.getString("first_name"),rs.getString("telephone_number"),rs.getString("roles"))
//                        , rs.getTimestamp("participation_date"), 
//                        new video(rs.getInt("id"),rs.getString("url"),rs.getString("title"),rs.getTimestamp("publish_date"),new User(rs.getInt("user.id"),rs.getString("username"),rs.getString("email"), rs.getString("adresse"),rs.getString("sexe"), rs.getString("name"), rs.getString("first_name"),rs.getString("telephone_number"),rs.getString("roles")))));
//                        
//            }
//    
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//         
//        return cp;
//     }
//     
//      public Boolean findParticipation(Competition c, User u) {
//        String req = "select * from competition_participant where competition_id=? and user_id=?";
//        try {
//            pst = connection.prepareStatement(req);
//            pst.setInt(1, c.getId());
//            pst.setInt(2, u.getId());
//
//            rs = pst.executeQuery();
//            return rs.next();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//      public ObservableList<video> getAllOrdered(Competition c){
//      String req="select * from video inner join user on(video.owner=user.id) where video.id in(select video_id from competition_participant where competition_id=?) order by video.publish_date";
//      ObservableList<video> list=FXCollections.observableArrayList();
//      try {
//             pst=connection.prepareStatement(req);
//            pst.setInt(1,c.getId());
//            rs=pst.executeQuery();
//            
//            while(rs.next()){
//                
//                list.add(new video(rs.getInt("id"),rs.getString("url"),rs.getString("title"),rs.getTimestamp("publish_date"),  
//                        new User(rs.getInt("user.id"),rs.getString("username"),rs.getString("email"), rs.getString("adresse"),rs.getString("sexe"), rs.getString("name"), rs.getString("first_name"),rs.getString("telephone_number"),rs.getString("roles"))));
//            }
//    
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//     }
//}

}
