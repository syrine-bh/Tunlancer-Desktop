/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Utils.MyConnection;
import entities.Annonce;
import interfaces.Iserviceannonce;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ListView;

/**
 *
 * @author siwar
 */
public class Annonceservice implements Iserviceannonce{

    @Override
    public void addAnnonce(Annonce a) {
         try {
         String requete = "INSERT INTO annonce(nom , description , date ,lieux ,renumeration) VALUES (?,?,?,?,?)";
            PreparedStatement ps = new MyConnection ().getConnection().prepareStatement(requete);
            ps.setString(1, a.getNom());
             ps.setString(2, a.getDescription());
              ps.setDate(3, (Date) a.getDate());
              ps.setString(4, a.getLieux());
              
                ps.setString(5, a.getRenumeration());

            ps.executeUpdate();
            System.out.println("annonce  ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
        @Override
    public List<Annonce> DisplayAnnonce() {
        List<Annonce> ANNList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM annonce";
            Statement st = new MyConnection ().getConnection()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Annonce a = new Annonce();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString("nom"));
                 a.setDescription(rs.getString("description"));
                 a.setDate(rs.getDate("date"));
                 a.setLieux(rs.getString("lieux"));
                 a.setRenumeration(rs.getString("renumeration"));

                ANNList.add(a);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println( ANNList);
        return  ANNList;
    }

    @Override
    public int updateAnnonce(Annonce a) {
         int nbModif = 0;
   try {
            String requete = "UPDATE annonce SET nom=?,description=? , date=? ,lieux=? ,renumeration=? ,where id=?";
            PreparedStatement ps = new MyConnection().getConnection().prepareStatement(requete);
            ps.setString(1, a.getNom());
             ps.setString(2, a.getDescription());
              ps.setDate(3, (Date) a.getDate());
              ps.setString(4, a.getLieux());
              
                ps.setString(5, a.getRenumeration());
                ps.setInt(6,a.getId());

           nbModif  = ps.executeUpdate();
            System.out.println("annonce modifiée avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
   return nbModif;
   } 
   

    @Override
    public void deleteAnnonce(int a) {
     
        try {
            String requete = "DELETE FROM annonce WHERE id=?";
            PreparedStatement ps = new MyConnection().getConnection().prepareStatement(requete);
            ps.setInt(1, a);
            ps.executeUpdate();
            System.out.println("annonce supprimée avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
     public void findannonce(Annonce a) {
         List<Annonce> ANNList = new ArrayList<>();
        try {
            String requete = "SELECT COUNT(a.nom),a.nom FROM annonce a,postuler p where a.id=p.annonce_id GROUP BY a.nom ";
            Statement st = new MyConnection ().getConnection()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            if(rs.next()) {
               
            
              
             rs.getInt("COUNT(a.nom)");
                rs.getString("nom");
                /* a.setDescription(rs.getString("description"));
                 a.setDate(rs.getDate("date"));
                 a.setLieux(rs.getString("lieux"));
                 a.setRenumeration(rs.getString("renumeration"));*/

                ANNList.add(a);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println( ANNList);
       // return  ANNList;
         
     }
     
     public ListView<Annonce >  getannoncebyid(int ida) {
        
         List <Annonce> ANNList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM annonce where id=" + ida;
        
             Statement st = new MyConnection ().getConnection()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Annonce a = new Annonce();
                a.setId(rs.getInt("id"));
                a.setNom(rs.getString("nom"));
                 a.setDescription(rs.getString("description"));
                 a.setDate(rs.getDate("date"));
                 a.setLieux(rs.getString("lieux"));
                 a.setRenumeration(rs.getString("renumeration"));
                 ANNList.add(a);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  (ListView<Annonce>) ANNList;
         
     }
     }