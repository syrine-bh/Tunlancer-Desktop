/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Front.Concours;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;
import Models.Users;
import entities.Video;
import entities.Concour;


/**
 *
 * @author Anis
 */
public class VoteServices {

    private Connection connection;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private ServiceConcours st = new ServiceConcours();

    Concour c;
        concoursHolder th = concoursHolder.getINSTANCE();
// private Concour c;
     public void setConcour(Concour c) {
                         c = st.getConcours(th.getId());

        this.c=c;
    }
    public VoteServices() {
      Connection cnx = MyConnection.getInstance().getConnection();
    }

    public void Add(Users u, Video v) {

        String req = "insert into votes values((select id from video where id=?),(select id from users where id=?))";
        try {                    Connection connection = MyConnection.getInstance().getConnection();

            
            System.out.println(v.getId());
            System.out.println(u.getId());
            System.out.println(connection);
            pst = connection.prepareStatement(req);

            pst.setInt(1, v.getId());
            pst.setInt(2, u.getId());
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(Video v, Users u) {
        String req = "delete from votes where video_id=? and user_id=?";
        try {
                                Connection connection = MyConnection.getInstance().getConnection();

            pst = connection.prepareStatement(req);
            pst.setInt(1, v.getId());
            pst.setInt(2, u.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Boolean find(Video v, Users u) {
        String req = "select * from votes where video_id=? and user_id=?";
        try {
                                Connection connection = MyConnection.getInstance().getConnection();

            pst = connection.prepareStatement(req);
            pst.setInt(1, v.getId());
            pst.setInt(2, u.getId());

            rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ObservableList<Users> getAll(Video v){
      String req="select * from votes inner join users on(votes.user_id=users.id) where votes.video_id=?";
      ObservableList<Users> list=FXCollections.observableArrayList();
      try {
          Connection connection = MyConnection.getInstance().getConnection();

             pst=connection.prepareStatement(req);
            pst.setInt(1,v.getId());
            rs=pst.executeQuery();
            while(rs.next()){   
            list.add(new Users(rs.getInt("Id"), rs.getString("Nom"), rs.getString("Role"), rs.getString("Prenom"), rs.getString("Password"), rs.getInt("Tel"), rs.getString("Pays"), rs.getInt("is_Enabled"),
                                rs.getInt("Age"), rs.getString("Sexe"), rs.getInt("super_admin")));
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public HashMap<Video, Integer> ranks(Concour c) {
        String req = "SELECT  COUNT(v.video_id) ,video.*,users.*  FROM votes v "
                + "inner join video  on(video.id=v.video_id) "
                + "inner join users on(video.owner=users.id)"
                + " WHERE v.video_id IN ( SELECT video_id FROM participation c WHERE c.concour_id = ? )"
                + " GROUP by v.video_id "
                + "ORDER by count(v.video_id)"
                + " DESC ";
        HashMap<Video, Integer> l = new HashMap<>();
        try {
                    Connection connection = MyConnection.getInstance().getConnection();

            pst = connection.prepareStatement(req);
            pst.setInt(1, c.getId());
            rs = pst.executeQuery();
            while (rs.next()) {
                l.put(new Video(rs.getInt("id"), rs.getString("url"), rs.getString("title"), rs.getTimestamp("publish_date"),
                        new Users(rs.getInt("Id"), rs.getString("Nom"), rs.getString("Role"), rs.getString("Prenom"), rs.getString("Password"), rs.getInt("Tel"), rs.getString("Pays"), rs.getInt("is_Enabled"),
                                rs.getInt("Age"), rs.getString("Sexe"), rs.getInt("super_admin"))),
                        rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return l;
    }

    public int getVotes(Video v) {
        int i = 0;
        String req = "select Count(video_id) from votes where video_id=?";
        try {
                                Connection connection = MyConnection.getInstance().getConnection();

            pst = connection.prepareStatement(req);
            pst.setInt(1, v.getId());
            rs = pst.executeQuery();
            if (rs.next()) {
                i = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
