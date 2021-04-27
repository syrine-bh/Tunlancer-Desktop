/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Hiba
 */
public class Score {
    

private int id;
private String pseudo;
private String score;
private Quiz quiz_id;
private Users2 user_id;

    public Score() {
    }

    public Score(int id, String score) {
        this.id = id;
        this.score = score;
    }

    public Score(int id, String pseudo, String score) {
        this.id = id;
        this.pseudo = pseudo;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Quiz getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(Quiz quiz_id) {
        this.quiz_id = quiz_id;
    }

    public Users2 getUser_id() {
        return user_id;
    }

    public void setUser_id(Users2 user_id) {
        this.user_id = user_id;
    }









}
