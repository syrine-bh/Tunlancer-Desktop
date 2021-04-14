/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Concour;
import interfaces.Iconcours;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;

/**
 *
 * @author Hiba
 */
public class ConcoursCrud implements Iconcours<Concour>{

    @Override
    public void ajouter(Concour t) {


    }

   

     @Override
    public ObservableList<Concour> display() {
        
         ObservableList<Concour> concours = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM `concour`";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            //contenaire
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
              Concour c = new Concour();
                c.setSujet(rs.getString("sujet"));
                
                Date d1 = rs.getDate("date_Debut");
                Date d2 = rs.getDate("date_Fin");

                c.setDateDebut(new Date(d1.getYear(),(d1.getMonth()),d1.getDate()));
                c.setDateFin(new Date(d2.getYear(),(d2.getMonth()),d2.getDate()));

                c.setCategorie(rs.getString("categorie"));
                c.setCouleur(rs.getString("couleur"));
                c.setNom(rs.getString("nom"));
                           c.setDescription(rs.getString("Description"));
           c.setImageName(rs.getString("image_name"));
           c.setIsVideo(rs.getBoolean("is_Video"));

                concours.add(c);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return concours;       }

   

    @Override
    public void supprimer(Concour t) {
try {
            String requete = "DELETE FROM concour where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, t.getId());
            //
            pst.executeUpdate();
            System.out.println("Concours supprimé ! ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public void update(Concour t) {
        try {
            String requete = "UPDATE `concour` SET `sujet`=?,`nom`=?,`description`=?,`date_debut`=?,`date_fin`=?,`image_name`=?,`categorie`=?,`is_video`=?,`couleur`=? WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            
         pst.setString(1, t.getSujet());
            pst.setString(2, t.getNom());
            pst.setString(3, t.getDescription());
            pst.setDate(4, (Date) t.getDateDebut());
            pst.setDate(5, (Date) t.getDateFin());
            pst.setString(6, t.getImageName());
            pst.setString(7, t.getCategorie());
            pst.setBoolean(8, t.getIsVideo());
            pst.setString(9, t.getCouleur());
           
            pst.executeUpdate();
            System.out.println("Concours modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
