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
public class Signaler {
     					
    private int id;
    private int id_utilisateur_id;
    private int publication_id;
    private String description;
    private String date_creation;

    private Users utilisateur;
    private Publication publication;
    
    public Signaler() {
        utilisateur = new Users(id_utilisateur_id);
        publication = new Publication(publication_id);
    }

    public Signaler(int id, int id_utilisateur_id, int publication_id, String description, String date_creation) {
        utilisateur = new Users(id_utilisateur_id);
        publication = new Publication(publication_id);
        
        this.id = id;
        this.id_utilisateur_id = id_utilisateur_id;
        this.publication_id = publication_id;
        this.description = description;
        this.date_creation = date_creation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_utilisateur_id() {
        return id_utilisateur_id;
    }

    public void setId_utilisateur_id(int id_utilisateur_id) {
        this.id_utilisateur_id = id_utilisateur_id;
    }

    public int getPublication_id() {
        return publication_id;
    }

    public void setPublication_id(int publication_id) {
        this.publication_id = publication_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    @Override
    public String toString() {
        return "Signaler{" + "id=" + id + ", id_utilisateur_id=" + id_utilisateur_id + ", publication_id=" + publication_id + ", description=" + description + ", date_creation=" + date_creation + '}';
    }
    
}
