/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Front.Concours;

import entities.Concour;
import entities.Quiz;
import entities.questiontab;
import entities.reponsetab;
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
public class ServiceQuiz {
    
     public Quiz getQuiz (int idQ,int idC) {
        System.out.println("teeeest get Questions");

        Quiz q = null;
        try {
            String query = "SELECT id,concour_id,nom,nb_questions FROM quiz WHERE id_concour=" + idC;

            Connection connection = MyConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = connection.createStatement().executeQuery(query);

            while (rs.next()) {
                Quiz quiz =new Quiz();
//String reponse = result.getString("reponses");
//            boolean statu = result.getBoolean("statu");
quiz.setConcour_id(rs.getInt("id_concour"));
                quiz.setId(rs.getInt("id"));
                quiz.setNom(rs.getString("nom"));
                quiz.setNb_questions(rs.getInt("nb_questions"));

//                String url = "file:///" + projectPath + "/src/coheal/resources/images/tasks/" + rs.getString("imageName");
//                img = new ImageView(url);
//                conco.setImg(img);
//                task.setCreatedAt(rs.getTimestamp("created_at"));
                q = quiz;
            }

        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        return q;
    }
   


    public List<Quiz> ListQuiz() {
        ArrayList<Quiz> q = new ArrayList();
        System.out.println("TEST list quiz");
        try {

            String requette;
            requette = "SELECT * FROM `quiz`";

            
            Connection connection = MyConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement(requette);
            ResultSet rs = connection.createStatement().executeQuery(requette);

            while (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setId(rs.getInt("id"));
                quiz.setConcour_id(rs.getInt("id_concour"));
                quiz.setNom(rs.getString("nom"));
                quiz.setNb_questions(rs.getInt("nb_questions"));
//                questions.setReponses((reponsetab[]) rs.getObject("reponses"));
              
                System.out.println(quiz);
                q.add(quiz);
                 System.out.println(q);
            }
           
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage");
        }
        return q;
    }
    
    
    
    public questiontab getQuestions(int idQ) {
        System.out.println("teeeest get Questions");

        questiontab q = null;
        try {
            String query = "SELECT id,questions FROM questiontab WHERE quiz_id=" + idQ;

            Connection connection = MyConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = connection.createStatement().executeQuery(query);

            while (rs.next()) {
questiontab questions = new questiontab();
//String reponse = result.getString("reponses");
//            boolean statu = result.getBoolean("statu");
                questions.setReponses((reponsetab[]) rs.getObject("reponse"));
                questions.setQuestion_id(rs.getInt("id"));

//                String url = "file:///" + projectPath + "/src/coheal/resources/images/tasks/" + rs.getString("imageName");
//                img = new ImageView(url);
//                conco.setImg(img);
//                task.setCreatedAt(rs.getTimestamp("created_at"));
                q = questions;
            }

        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        return q;
    }
   


    public List<questiontab> ListQuestions() {
        ArrayList<questiontab> q = new ArrayList();
        System.out.println("TEST list questions");
        try {

            String requette;
            requette = "SELECT * FROM `questiontab` WHERE quiz_id=1";

            
            Connection connection = MyConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement(requette);
            ResultSet rs = connection.createStatement().executeQuery(requette);

            while (rs.next()) {
                questiontab questions= new questiontab();
                questions.setId(rs.getInt("id"));
                questions.setQuestions(rs.getString("questions"));
//                questions.setReponses((reponsetab[]) rs.getObject("reponses"));
              
                System.out.println(questions);
                q.add(questions);
                 System.out.println(q);
            }
           
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage");
        }
        return q;
    }
    
    
    
    
         public reponsetab getReponses(int idQs) {
        System.out.println("teeeest get Rep");

        reponsetab r = null;
        try {
            String query = "SELECT * FROM reponsetab WHERE question_id=" + idQs;

            Connection connection = MyConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = connection.createStatement().executeQuery(query);

            while (rs.next()) {
reponsetab reponses = new reponsetab();
//String reponse = result.getString("reponses");
//            boolean statu = result.getBoolean("statu");
                reponses.setReponses(rs.getString("reponses"));
                reponses.setStatu(rs.getBoolean("statu"));

//                String url = "file:///" + projectPath + "/src/coheal/resources/images/tasks/" + rs.getString("imageName");
//                img = new ImageView(url);
//                conco.setImg(img);
//                task.setCreatedAt(rs.getTimestamp("created_at"));
                r = reponses;
            }

        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        return r;
    }
   


    public List<reponsetab> ListReponses() {
        ArrayList<reponsetab> r = new ArrayList();
        System.out.println("TEST list reponsetab");
        try {

            String requette;
            requette = "SELECT * FROM `reponsetab` WHERE question_id=5 AND question_id=6 AND question_id=7 AND questions_id=8";

            
            Connection connection = MyConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement(requette);
            ResultSet rs = connection.createStatement().executeQuery(requette);

            while (rs.next()) {
                reponsetab reponses= new reponsetab();
                reponses.setReponses(rs.getString("reponses"));
                reponses.setStatu(rs.getBoolean("statu"));
//                questions.setReponses((reponsetab[]) rs.getObject("reponses"));
              
                System.out.println(reponses);
                r.add(reponses);
                 System.out.println(r);
            }
           
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'affichage");
        }
        return r;
    }
    
    
    
    
    
    
    
}
