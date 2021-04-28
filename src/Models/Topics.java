/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javafx.scene.control.Label;
import utils.MaConnexion;

/**
 *
 * @author cyrinaa belguith
 */
public class Topics {

 

    

//    public static Topics getInstance() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
    private int id;
    private String titre;
    private String Contenu;
    private Date date;
    private int user_id;
    private int note;
    
    //private List<Replies> replies;

    public Topics() {
        //replies = new ArrayList<>();
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

    public Topics(String titre, String description) {
        this.titre=titre;
        this.Contenu=Contenu;
    }

    public Topics(int id, int user_id, int note) {
        this.id = id;
        this.user_id = user_id;
        this.note = note;
    }

    public Topics(int id, int note) {
        this.id=id;
        this.note=note;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
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

//    public int getTopic_id() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public LinkedHashMap<Date, Integer> AfficheStatisticTopics(String an) {
 LinkedHashMap<Date,Integer> map = new LinkedHashMap<>();    
        
        try {
            String requete = "SELECT `date` Count(*) FROM `topics`"+ "GROUP BY EXTRACT(MONTH FROM Date)";
            PreparedStatement pst = MaConnexion.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, an);
            ResultSet rs = pst.executeQuery();
   
            while(rs.next()){
                map.put(rs.getDate(1), rs.getInt(2));
                }
                
                map.entrySet().forEach(entry -> {
                    System.out.println("dans le mois :"+entry.getKey() +"  nombre de topic "+ entry.getValue());
               
                });
                
        } catch (SQLException ex) {
            System.out.println("Problem"+ex.getMessage());
        }
        return map;    }
      
    
    
}
