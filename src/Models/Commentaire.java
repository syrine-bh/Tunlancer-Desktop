/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;



/**
 *
 * @author Cyrina
 */
public class Commentaire {
    			
    private int id_commentaire;
    private int publication_id;
    private int id_utilisateur_id;
    private String contenuCommentaire;

    private Users utilisateur;
    private Publication publication;
    
    public Commentaire() {
        utilisateur = new Users(id_utilisateur_id);
        publication = new Publication(publication_id);
    }

    public Commentaire(int id_commentaire, int publication_id, int id_utilisateur_id, String contenuCommentaire) { 
        utilisateur = new Users(id_utilisateur_id);
        publication = new Publication(publication_id);
        
        this.id_commentaire = id_commentaire;
        this.publication_id = publication_id;
        this.id_utilisateur_id = id_utilisateur_id;
        this.contenuCommentaire = contenuCommentaire;
    }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public int getPublication_id() {
        return publication_id;
    }

    public void setPublication_id(int publication_id) {
        this.publication_id = publication_id;
    }

    public int getId_utilisateur_id() {
        return id_utilisateur_id;
    }

    public void setId_utilisateur_id(int id_utilisateur_id) {
        this.id_utilisateur_id = id_utilisateur_id;
    }

    public String getContenuCommentaire() {
        return contenuCommentaire;
    }

    public void setContenuCommentaire(String contenuCommentaire) {
        this.contenuCommentaire = contenuCommentaire;
    }

    @Override
    public String toString() {
        return "Commentaire{" + " id_utilisateur_id=" + id_utilisateur_id + ", contenuCommentaire=" + contenuCommentaire + '}';
    }
    
    
}
