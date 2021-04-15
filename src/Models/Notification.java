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
public class Notification {
  				
    private int id;
    private int utilisateur_id;
    private int vu;
    private String description;	
    private String lien;
    private String date_creation;

    private Users utilisateur;
    
    public Notification() {
        utilisateur = new Users(utilisateur_id);
    }

    
    public Notification(int id, int utilisateur_id, int vu, String description, String lien, String date_creation) {
        utilisateur = new Users(utilisateur_id);
        this.id = id;
        this.utilisateur_id = utilisateur_id;
        this.vu = vu;
        this.description = description;
        this.lien = lien;
        this.date_creation = date_creation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(int utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    public int getVu() {
        return vu;
    }

    public void setVu(int vu) {
        this.vu = vu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    @Override
    public String toString() {
        return "Notification{" + "id=" + id + ", utilisateur_id=" + utilisateur_id + ", vu=" + vu + ", description=" + description + ", lien=" + lien + ", date_creation=" + date_creation + '}';
    }
    
 
    
    
}
