/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.MyConnection;
import entities.reponsetab;

/**
 *
 * @author Hiba
 */
public class Manager {

    private questiontab[] questions = new questiontab[500];
    private reponsetab[]reponses=new reponsetab[4];
        private int numReponses = 0;

    private int numQuestions;
    private int level=3;
    private int lives;
    private static MyConnection cnx;
    private questiontab currentQuestion;
        private reponsetab currentReponse;

    public static int getRandom(int iMin, int iMax) {
        int iRand = 0;
        iRand = (int) Math.round(Math.random() * (iMax - iMin)) + iMin;
        return iRand;
    }

    public Manager() throws SQLException {
        level = 1;
        populateQuestions();
    }

    public void addQuestion(String question, int question_id,String answer) throws SQLException {
        questions[numQuestions] = new questiontab(question, question_id,answer);
        numQuestions++;
    }

    public void populateQuestions() throws SQLException {
        numQuestions = 0;
        
        String SQL = "SELECT id,questions,answer FROM questiontab WHERE quiz_id=1 AND level=" + level;
        Connection connection = MyConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement(SQL);
        ResultSet result = connection.createStatement().executeQuery(SQL);
        while (result.next()) {
            int question_id = result.getInt("id");
            String question = result.getString("questions");
            String answer = result.getString("answer");
            addQuestion(question, question_id,answer);
        }
    }
 public void increaseLevel() throws SQLException{
     level++;
     
     populateQuestions();
     
 }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
 public String getQuestion(){
     
        currentQuestion=questions[getRandom(0, numQuestions-1)];
        
        return currentQuestion.toString();
     
 }
 
 
 
 
 
 public boolean reponse (char reponse) throws SQLException{
     if(currentQuestion.isCorrect(reponse)){
         level++;
         populateQuestions();
        return true;
     }else{
         lives--;
         return false;
     }
 }
}
