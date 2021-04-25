package entities ;
import java.sql.* ;



public class Quiz {

    private int id ;
    private int concour_id ;
    private String nom ;
    private int nb_questions ;
    private String couleur ;

    public Quiz(int id, int concour_id, String nom, int nb_questions, String couleur) {
        this.id = id;
        this.concour_id = concour_id;
        this.nom = nom;
        this.nb_questions = nb_questions;
        this.couleur = couleur;
    }

    public Quiz(int id, int concour_id, String nom, int nb_questions) {
        this.id = id;
        this.concour_id = concour_id;
        this.nom = nom;
        this.nb_questions = nb_questions;
       
    }

    
    public Quiz() {
    }

    
  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConcour_id() {
        return concour_id;
    }

    public void setConcour_id(int concour_id) {
        this.concour_id = concour_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNb_questions() {
        return nb_questions;
    }

    public void setNb_questions(int nb_questions) {
        this.nb_questions = nb_questions;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
    
//    public static createTable(){
//        
//    }
    
}