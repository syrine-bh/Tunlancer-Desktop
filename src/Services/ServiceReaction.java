/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.IServiceReaction;
import Models.Publication;
import Models.Reaction;
import Models.Users;
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
public class ServiceReaction implements IServiceReaction{
    Connection cnx;
    public ServiceReaction() {
        cnx = MaConnexion.getInstance().getCnx();
    }
     
    @Override
    public void reagirPublication(Users utilisateur,Publication p, int typeReaction) throws SQLException {
        Statement stm = cnx.createStatement();        	
        String query = "INSERT INTO `reaction` (`id_reaction`, `publication_id`, `id_utilisateur_id`, `typeReaction`) VALUES (NULL, '"+p.getId()+"', '"+utilisateur.getId()+"', '"+typeReaction+"');";
        stm.executeUpdate(query);
    }

    @Override
    public void supprimerReation(Users utilisateur,Publication p) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "DELETE FROM `reaction` where publication_id= '"+p.getId()+"' AND id_utilisateur_id= '"+utilisateur.getId()+"'";
        stm.executeUpdate(query);    
    }

    @Override
    public void modifierReaction(Users utilisateur,Publication p, int typeReaction) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "UPDATE reaction SET typeReaction= '"+typeReaction+"' where publication_id= '"+p.getId()+"' AND id_utilisateur_id= '"+utilisateur.getId()+"'";
        stm.executeUpdate(query);    
    }

    @Override
    public List<Reaction> afficherReactions(Publication p) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "SELECT  *  FROM `reaction` where publication_id= '"+p.getId()+"'";
        
        ResultSet rst = stm.executeQuery(query);
        List<Reaction> reactions = new ArrayList<>();
        while (rst.next()) {
            Reaction reaction = new Reaction();
            reaction.setId_reaction(rst.getInt("id_reaction"));
            reaction.setId_utilisateur_id(rst.getInt("id_utilisateur_id"));
            reaction.setPublication_id(rst.getInt("publication_id"));
            reaction.setTypeReaction(rst.getInt("typeReaction"));
            
            reactions.add(reaction);
        }
        return reactions;
    }

    @Override
    public List<Reaction> afficherReactionsByUtilisateur(Users utilisateur) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "SELECT  *  FROM `reaction` where id_utilisateur_id= '"+utilisateur.getId()+"'";
        
        ResultSet rst = stm.executeQuery(query);
        List<Reaction> reactions = new ArrayList<>();
        while (rst.next()) {
            Reaction reaction = new Reaction();
            reaction.setId_reaction(rst.getInt("id"));
            reaction.setId_utilisateur_id(rst.getInt("id_utilisateur_id"));
            reaction.setPublication_id(rst.getInt("publication_id"));
            reaction.setTypeReaction(rst.getInt("typeReaction"));
            
            reactions.add(reaction);
        }
        return reactions;
    }
    
}
