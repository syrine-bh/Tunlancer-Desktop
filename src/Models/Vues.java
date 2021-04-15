/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;


/**
 *
 * @author Cyrina
 */
public class Vues {
    									
    private int id;
    private int utilisateur_id;
    private int publication_id;
    private String adresse;
    private String pays_code;
    private Date date;
    private String operateur;
    private String pays;
    private String region;
    private String ville;

    private Users utilisateur;
    private Publication publication;
    
    public Vues() {
        utilisateur = new Users(utilisateur_id);
        publication = new Publication(publication_id);
    }

    public Vues(int id, int utilisateur_id, int publication_id, String adresse, String pays_code, Date date, String operateur, String pays, String region, String ville) {
        utilisateur = new Users(utilisateur_id);
        publication = new Publication(publication_id);
        
        this.id = id;
        this.utilisateur_id = utilisateur_id;
        this.publication_id = publication_id;
        this.adresse = adresse;
        this.pays_code = pays_code;
        this.date = date;
        this.operateur = operateur;
        this.pays = pays;
        this.region = region;
        this.ville = ville;
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

    public int getPublication_id() {
        return publication_id;
    }

    public void setPublication_id(int publication_id) {
        this.publication_id = publication_id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPays_code() {
        return pays_code;
    }

    public void setPays_code(String pays_code) {
        this.pays_code = pays_code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOperateur() {
        return operateur;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "vues{" + "utilisateur_id=" + utilisateur_id + ", adresse=" + adresse + ", pays_code=" + pays_code + ", date=" + date + ", operateur=" + operateur + ", pays=" + pays + ", region=" + region + ", ville=" + ville + '}';
    }
    
    
    
}
