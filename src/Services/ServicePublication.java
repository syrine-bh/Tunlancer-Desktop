/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.IServicePublication;
import Models.Commentaire;
import Models.Publication;
import Models.Reaction;
import Models.Users;
import Models.Vues;
import utils.MaConnexion;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Cyrina
 */
public class ServicePublication implements IServicePublication{
    
    Connection cnx;
    
    ServiceReaction SR;
    ServiceCommentaire SC;
    ServiceVues SV;
    
    public ServicePublication() {
        cnx = MaConnexion.getInstance().getCnx();
        SR = new ServiceReaction();
        SC = new ServiceCommentaire();
        SV = new ServiceVues();
    }

    @Override
    public void ajouterPublication(Publication p) throws SQLException {
        Statement stm = cnx.createStatement();        	
        String query = "INSERT INTO `publication` (`id`, `description`, `type`, `archive`, `utilisateur_id`, `image_name`, `localisation`)"
                + "     VALUES (NULL, '"+p.getDescription()+"', '"+p.getType()+"', '"+p.getArchive()+"', '"+p.getUtilisateur_id()+"', '"+p.getImage_name()+"', '"+p.getLocalisation()+"')";
        stm.executeUpdate(query); 
        System.out.println("Publication ajoutée");
       
    }

    @Override
    public void supprimerPublication(int id) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "DELETE FROM `publication` where id= '"+id+"'";
        stm.executeUpdate(query);          
    }

    @Override
    public void modifierPublication(Publication p ) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "UPDATE publication SET description= '"+p.getDescription()+"', type='"+p.getType()+"', archive='"+p.getArchive()+"', utilisateur_id='"+p.getUtilisateur_id()+"', image_name='"+p.getImage_name()+"', localisation='"+p.getLocalisation()+"'WHERE id='"+p.getId()+"'";
        stm.executeUpdate(query);  
    }

    @Override
    public List<Publication> afficherPublications() throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "SELECT  *  FROM `publication` WHERE image_name LIKE '%.png%' OR image_name LIKE '%.jpg%' ORDER BY id DESC";
        
        ResultSet rst = stm.executeQuery(query);
        List<Publication> publications = new ArrayList<>();
        while (rst.next()) {
            Publication p = new Publication();
            
            List<Reaction> reactions = new ArrayList<>();
            List<Commentaire> commentaires = new ArrayList<>();
            List<Vues> vues = new ArrayList<>();

            
            p.setId(rst.getInt("id"));
            p.setArchive(rst.getInt("archive"));
            p.setType(rst.getInt("type"));
            p.setDescription(rst.getString("description"));
            p.setUtilisateur_id(rst.getInt("utilisateur_id"));
            p.setImage_name(rst.getString("Image_name"));
            p.setLocalisation(rst.getString("localisation"));

            
            reactions = SR.afficherReactions(p);
            commentaires = SC.afficherCommentaires(p);
            vues = SV.afficherVues(p);
            
            p.setReactions(reactions);
            p.setCommentaires(commentaires);
            p.setVues(vues);
            publications.add(p);
        }
        return publications;
    }

    @Override
    public Publication getPublicationById(int id) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "select * from `publication` where id= '"+id+"'";
        ResultSet rst = stm.executeQuery(query);
        
        Publication result = new Publication();
        
        while (rst.next()) {
            List<Reaction> reactions = new ArrayList<>();
            List<Commentaire> commentaires = new ArrayList<>();
            List<Vues> vues = new ArrayList<>();
            
            result.setId(rst.getInt("id"));
            result.setArchive(rst.getInt("archive"));
            result.setType(rst.getInt("type"));
            result.setDescription(rst.getString("description"));
            result.setUtilisateur_id(rst.getInt("utilisateur_id"));
            result.setImage_name(rst.getString("Image_name"));
            result.setLocalisation(rst.getString("localisation"));
            

            reactions = SR.afficherReactions(result); 
            commentaires = SC.afficherCommentaires(result);
            vues = SV.afficherVues(result);
            
            result.setReactions(reactions);
            result.setCommentaires(commentaires);
            result.setVues(vues);
        }
     return result;   
    }
    
    public List<Publication> searchPublication(String search) throws SQLException{
        Statement stm = cnx.createStatement();
        String query = "SELECT  *  FROM `publication` WHERE image_name LIKE '%.png%' OR image_name LIKE '%.jpg%' OR description LIKE '%"+search+"%' ORDER BY id DESC";
        
        ResultSet rst = stm.executeQuery(query);
        List<Publication> publications = new ArrayList<>();
        while (rst.next()) {
            Publication p = new Publication();
            
            List<Reaction> reactions = new ArrayList<>();
            List<Commentaire> commentaires = new ArrayList<>();
            List<Vues> vues = new ArrayList<>();

            
            p.setId(rst.getInt("id"));
            p.setArchive(rst.getInt("archive"));
            p.setType(rst.getInt("type"));
            p.setDescription(rst.getString("description"));
            p.setUtilisateur_id(rst.getInt("utilisateur_id"));
            p.setImage_name(rst.getString("Image_name"));
            p.setLocalisation(rst.getString("localisation"));

            
            reactions = SR.afficherReactions(p);
            commentaires = SC.afficherCommentaires(p);
            vues = SV.afficherVues(p);
            
            p.setReactions(reactions);
            p.setCommentaires(commentaires);
            p.setVues(vues);
            publications.add(p);
        }
        return publications;
    }
    
}
