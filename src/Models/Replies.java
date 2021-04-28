/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author cyrinaa belguith
 */
public class Replies {
    
    private int id;
    private String contenu;
    private Date created_at;
    private int topic_id;
    private int user_id;
    
    
    
   //private Topics topics;

    public Replies() {
        //topics = new Topics(topic_id);
    }
    
    

    public Replies(int id, String contenu, Date created_at, int topic_id, int user_id) {
        
        //topics = new Topics(topic_id);

        this.id = id;
        this.contenu = contenu;
        this.created_at = created_at;
        this.topic_id = topic_id;
        this.user_id = user_id;
        
        
        

    }

    public Replies(String contenu, int topic_id, int user_id) {
        //topics = new Topics(topic_id);

        this.contenu = contenu;
        this.topic_id = topic_id;
        this.user_id = user_id;
    }
    
    

    public Replies(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Replies(int id, String contenu, int user_id, Date created_at) {
        this.id=id;
        this.contenu=contenu;
        this.user_id=user_id;
        this.created_at=created_at;
                
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
   

    @Override
    public String toString() {
        return "Replies{" + "contenu=" + contenu + ", user_id=" + user_id + '}';
    }
    
    
    
    
    
}
