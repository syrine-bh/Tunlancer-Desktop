
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Models.Users;
import Service.Front.Concours.ServiceConcours;
import Service.Front.Concours.concoursHolder;
import com.jfoenix.controls.JFXButton;
import entities.Concour;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import utils.MyConnection;
import entities.Participation;
import entities.Users2;
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
    private ServiceConcours st = new ServiceConcours();
    ObservableList<Video> list = FXCollections.observableArrayList();

    Concour c;
    concoursHolder th = concoursHolder.getINSTANCE();
// private Concour c;

    public void setConcour(Concour c) {
        c = st.getConcours(th.getId());

        this.c = c;
    }

    public ParticipationServices() {
        Connection connection = MyConnection.getInstance().getConnection();

    }

    public void create(Participation p, Video v) throws SQLException {
        c = st.getConcours(th.getId());

        long id = 0;
        String req;

        req = "INSERT INTO `video`(`url`, `title`, `publish_date`, `owner`) values (?,?,?,(select id from users where id=?))";
        Connection connection = MyConnection.getInstance().getConnection();

        try {
            PreparedStatement pst = connection.prepareStatement(req);

            pst = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, v.getUrl());
            pst.setString(2, v.getTitle());
            pst.setTimestamp(3, v.getPublish_date());
            pst.setObject(4, v.getOwner().getId());
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String req2 = "insert into participation(concour_id,user_id,date_participation,video_id) values((select id from concour where id=?),(select id from users where nom=?),?,(select id from video where id=?))";
        try {
            
            PreparedStatement pst = connection.prepareStatement(req);

            pst = connection.prepareStatement(req2);
            pst.setObject(1, p.getConcour_id());
            System.out.println(p.getConcour_id());
            pst.setObject(2, p.getUser_id());
            System.out.println(p.getUser_id());
            pst.setTimestamp(3, (Timestamp) p.getDate_participation());
            System.out.println(p.getDate_participation());
            pst.setObject(4, id);

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Video v) {
        String req = "delete from participation where video_id=?";
        try {
            Connection connection = MyConnection.getInstance().getConnection();

            PreparedStatement pst = connection.prepareStatement(req);

            pst = connection.prepareStatement(req);
            pst.setInt(1, v.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String req2 = "delete from video where id=?";
        try {
            Connection connection = MyConnection.getInstance().getConnection();

            PreparedStatement pst = connection.prepareStatement(req);

            pst = connection.prepareStatement(req2);
            pst.setInt(1, v.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Video v) {
        String req = "update video set url=?,title=? where id=?";
        try {
            Connection connection = MyConnection.getInstance().getConnection();

            PreparedStatement pst = connection.prepareStatement(req);

            pst = connection.prepareStatement(req);
            pst.setString(1, v.getUrl());
            pst.setString(2, v.getTitle());
            pst.setInt(3, v.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<Video> getAll(Concour c) {

        String req = "select * from video inner join users on(video.owner=users.id) where video.id in(select video_id from participation where concour_id=?)";
        System.out.println(c.getId());
        try {
            Connection connection = MyConnection.getInstance().getConnection();

            pst = connection.prepareStatement(req);
            pst.setInt(1, c.getId());
            rs = pst.executeQuery();

            while (rs.next()) {

                list.add(new Video(rs.getInt("id"), rs.getString("url"), rs.getString("title"), rs.getTimestamp("publish_date"),
                        new Users(rs.getInt("Id"), rs.getString("Nom"), rs.getString("Role"), rs.getString("Prenom"), rs.getString("Password"), rs.getInt("Tel"), rs.getString("Pays"), rs.getInt("is_Enabled"),
                                rs.getInt("Age"), rs.getString("Sexe"), rs.getInt("super_admin"))));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Participation getWinners(int vid_id) {
        String req = "select * from participation "
                + "inner join concour on(concour.id=participation.concour_id) "
                + "inner join users on(participation.user_id=users.id) "
                + "inner join video on(participation.video_id=video.id) "
                + " where participation.video_id = ?";

        Participation cp = null;
        try {
            Connection connection = MyConnection.getInstance().getConnection();

            PreparedStatement pst = connection.prepareStatement(req);

            pst = connection.prepareStatement(req);
            pst.setInt(1, vid_id);
            rs = pst.executeQuery();

            if (rs.next()) {

                cp = (new Participation(new Concour(rs.getInt("concour.id"), rs.getString("sujet"), rs.getDate("date_debut"), rs.getTimestamp("date_fin")) // new Users(rs.getInt("user.id"),rs.getString("nom"),rs.getString("email"), rs.getString("adresse"),rs.getString("sexe"), rs.getString("name"), rs.getString("first_name"),rs.getString("telephone_number"),rs.getString("roles"))
                        ,
                         new Users(rs.getInt("Id"), rs.getString("Nom"), rs.getString("Role"), rs.getString("Prenom"), rs.getString("Password"), rs.getInt("Tel"), rs.getString("Pays"), rs.getInt("is_Enabled"),
                                rs.getInt("Age"), rs.getString("Sexe"), rs.getInt("super_admin")),
                        rs.getTimestamp("participation_date"),
                        new Video(rs.getInt("id"), rs.getString("url"), rs.getString("title"), rs.getTimestamp("publish_date"))));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cp;
    }

    public Boolean findParticipation(Concour c, Users u) {
        String req = "select * from participation where concour_id=? and user_id=?";
        try {
            Connection connection = MyConnection.getInstance().getConnection();

            pst = connection.prepareStatement(req);
            pst.setInt(1, c.getId());
            pst.setInt(2, u.getId());

            rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ObservableList<Video> getAllOrdered(Concour c) {
        String req = "select * from video inner join users on(video.owner=users.id) where video.id in(select video_id from participation where concour_id=?) order by video.publish_date";
        try {
            Connection connection = MyConnection.getInstance().getConnection();

            PreparedStatement pst = connection.prepareStatement(req);

            pst = connection.prepareStatement(req);
            pst.setInt(1, c.getId());
            rs = pst.executeQuery();

            while (rs.next()) {

                list.add(new Video(rs.getInt("id"), rs.getString("url"), rs.getString("title"), rs.getTimestamp("publish_date"),
                        new Users(rs.getInt("Id"), rs.getString("Nom"), rs.getString("Role"), rs.getString("Prenom"), rs.getString("Password"), rs.getInt("Tel"), rs.getString("Pays"), rs.getInt("is_Enabled"),
                                rs.getInt("Age"), rs.getString("Sexe"), rs.getInt("super_admin"))));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
