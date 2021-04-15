/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.IServiceVues;
import Models.Commentaire;
import Models.Publication;
import Models.Users;
import Models.Vues;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MaConnexion;


/**
 *
 * @author Cyrina
 */
public class ServiceVues implements IServiceVues{
    
     Connection cnx;
    ServiceReaction SR;
    public ServiceVues () {
        cnx = MaConnexion.getInstance().getCnx();
    }
    
    @Override
    public void voirPublication(Vues vue) throws SQLException {
        Statement stm = cnx.createStatement();        	
        String query = "INSERT INTO `vues`(`id`, `utilisateur_id`, `publication_id`, `adresse`, `pays_code`, `date`, `operateur`, `pays`, `region`, `ville`)"
                + "VALUES (NULL, '"+vue.getUtilisateur_id()+"', '"+vue.getPublication_id()+"', '"+vue.getAdresse()+"', '"+vue.getPays_code()+"', '"+vue.getDate()+"', '"+vue.getOperateur()+"', '"+vue.getPays()+"', '"+vue.getRegion()+"' , '"+vue.getVille()+"'  )";
        stm.executeUpdate(query); 
    }

    @Override
    public List<Vues> afficherVues(Publication p) throws SQLException {
            java.sql.Statement stm = cnx.createStatement();
        String query = "SELECT  *  FROM `vues` where publication_id= '"+p.getId()+"'";
        
        ResultSet rst = stm.executeQuery(query);
        List<Vues> vues = new ArrayList<>();
        while (rst.next()) {
            Vues vue = new Vues ();
            vue.setId(rst.getInt("id"));
            vue.setUtilisateur_id(rst.getInt("utilisateur_id"));
            vue.setPublication_id(rst.getInt("publication_id"));
            vue.setAdresse(rst.getString("adresse"));
            vue.setPays_code(rst.getString("pays_code"));
            vue.setDate(rst.getDate("date"));
            vue.setOperateur(rst.getString("operateur"));
            vue.setPays(rst.getString("pays"));
            vue.setRegion(rst.getString("region"));
            vue.setVille(rst.getString("ville"));
            
            vues.add(vue);
        }
        return vues;    
    }
    
}
