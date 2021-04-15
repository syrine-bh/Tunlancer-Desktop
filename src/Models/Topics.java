/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;

/**
 *
 * @author cyrinaa belguith
 */
public class Topics {
    
    private int id;
    private String titre;
    private String Contenu;
    private Date date;
    private int user_id;

    public Topics() {
    }

    public Topics(int id) {
        this.id = id;
    }

    public Topics(int id, String titre, String Contenu, Date date, int user_id) {
        this.id = id;
        this.titre = titre;
        this.Contenu = Contenu;
        this.date = date;
        this.user_id = user_id;
    }

    public Topics(int id, String titre, String Contenu, Date date) {
        this.id = id;
        this.titre = titre;
        this.Contenu = Contenu;
        this.date = date;
    }

    public Topics(String titre, String Contenu, Date date) {
        this.titre = titre;
        this.Contenu = Contenu;
        this.date = date;
    }

    

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return Contenu;
    }

    public void setContenu(String Contenu) {
        this.Contenu = Contenu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Topics{" + "id=" + id + ", titre=" + titre + ", Contenu=" + Contenu + ", date=" + date + ", user_id=" + user_id + '}';
    }
    
    
    
    
}
