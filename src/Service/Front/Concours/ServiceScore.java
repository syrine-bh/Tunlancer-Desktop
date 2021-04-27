/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Front.Concours;

import entities.Concour;
import entities.Score;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.ImageView;
import utils.MyConnection;

/**
 *
 * @author Hiba
 */
public class ServiceScore {
     private Connection con;
    private static String projectPath = System.getProperty("user.dir").replace("\\", "/");

    
    public Score getScore(int idS) {
        System.out.println("teeeest get score");

          Score s = null;
        try {
            String query = "SELECT * from score WHERE id=" + idS;

            Connection connection = MyConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = connection.createStatement().executeQuery(query);

            while (rs.next()) {
                Score score = new Score();

                score.setId(rs.getInt("id"));
                score.setPseudo(rs.getString("pseudo"));
                score.setScore(rs.getString("score"));
                score.getQuiz_id();
                

//                String url = "file:///" + projectPath + "/src/coheal/resources/images/tasks/" + rs.getString("imageName");
//                img = new ImageView(url);
//                conco.setImg(img);
//                task.setCreatedAt(rs.getTimestamp("created_at"));
                s = score;
            }

        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        return s;
    }
  


    public List<Score> ListScores() {
        ArrayList<Score> s = new ArrayList();
        ImageView img = null;
        System.out.println("TEST listscore");
        try {

            String requette;
            requette = "SELECT * FROM `score`";

            Connection connection = MyConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement(requette);
            ResultSet rs = connection.createStatement().executeQuery(requette);

            while (rs.next()) {
                Score score = new Score();
                score.setId(rs.getInt("id"));
                score.setPseudo(rs.getString("pseudo"));
                                



//                String url = "file:///" + projectPath + "/src/coheal/resources/images/tasks/" + rs.getString("img_url");
//                img = new ImageView(url);
//                c.setImg(img);
                System.out.println(score);
                s.add(score);
            }
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage");
        }
        return s;
    }
}