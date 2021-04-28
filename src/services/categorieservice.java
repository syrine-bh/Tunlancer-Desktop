/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Utils.MyConnection;
import entities.Annonce;
import entities.Categorie;
import interfaces.Iservicecategorie;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author siwar
 */
public class categorieservice  implements Iservicecategorie{

   
    @Override
    public int update(Categorie c) {
  int nbModif = 0;
   try {
            String requete = "UPDATE categorie SET type=? where id=?";
            PreparedStatement ps = new MyConnection().getConnection().prepareStatement(requete);
            ps.setString(1, c.getType());
             
                ps.setInt(2,c.getId());

           nbModif  = ps.executeUpdate();
            System.out.println("categorie modifiée avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
   return nbModif;
    }    

    @Override
    public void delete(int c) {
        
          try {
            String requete = "DELETE FROM categorie  WHERE id=?";
            PreparedStatement ps = new MyConnection().getConnection().prepareStatement(requete);
            ps.setInt(1, c);
            ps.executeUpdate();
            System.out.println("categorie  supprimée avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
}

     

    @Override
    public void addcategorie(Categorie c) {
 try {
         String requete = "INSERT INTO categorie(type) VALUES (?)";
            PreparedStatement ps = new MyConnection ().getConnection().prepareStatement(requete);
            ps.setString(1, c.getType());
             

            ps.executeUpdate();
            System.out.println("categorie  ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public List<Categorie> Displaycategorie() {
          List<Categorie> catList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM categorie";
            Statement st = new MyConnection ().getConnection()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Categorie c = new Categorie();
                c.setId(rs.getInt("id"));
                c.setType(rs.getString("Type"));
                
                catList.add(c);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println( catList);
        return  catList;
         
    }
    
}
