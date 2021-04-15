/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.IServiceCommentaire;
import Models.Commentaire;
import Models.Publication;
import Models.Users;
import utils.MaConnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cyrina
 */
public class ServiceCommentaire implements IServiceCommentaire{
      Connection cnx;
    public ServiceCommentaire() {
        cnx = MaConnexion.getInstance().getCnx();
    }
    
     @Override
    public void AjouterCommentaire(Users utilisateur,Publication p,String contenuCommentaire) throws SQLException {
       java.sql.Statement stm = cnx.createStatement();        	
        String query = "INSERT INTO `commentaire`(`id_commentaire`, `publication_id`, `id_utilisateur_id`, `contenuCommentaire`) VALUES (NULL, '"+p.getId()+"', '"+utilisateur.getId()+"', '"+contenuCommentaire+"');";
        stm.executeUpdate(query);
    }
    
     @Override
    public void  supprimerCommentaire(Users utilisateur,Publication p) throws SQLException {
        java.sql.Statement stm = cnx.createStatement();
        String query = "DELETE FROM `commentaire` where publication_id= '"+p.getId()+"' AND id_utilisateur_id= '"+utilisateur.getId()+"'";
        stm.executeUpdate(query);    
    }
      @Override
    public void modifierCommentaire(Users utilisateur,Publication p,String contenuCommentaire) throws SQLException {
        java.sql.Statement stm = cnx.createStatement();
        String query = "UPDATE commentaire SET contenuCommentaire= '"+contenuCommentaire+"' where publication_id= '"+p.getId()+"' AND id_utilisateur_id= '"+utilisateur.getId()+"'";
        stm.executeUpdate(query);    
    }
   
    @Override
    public List<Commentaire> afficherCommentaires(Publication p) throws SQLException {
        java.sql.Statement stm = cnx.createStatement();
        String query = "SELECT  *  FROM `commentaire` where publication_id= '"+p.getId()+"'";
        
        ResultSet rst = stm.executeQuery(query);
        List<Commentaire> commentaires = new ArrayList<>();
        while (rst.next()) {
            Commentaire commentaire = new Commentaire ();
            commentaire.setId_commentaire(rst.getInt("id_commentaire"));
            commentaire.setId_utilisateur_id(rst.getInt("id_utilisateur_id"));
            commentaire.setPublication_id(rst.getInt("publication_id"));
            commentaire.setContenuCommentaire(rst.getString("contenuCommentaire"));
            
            commentaires.add(commentaire);
        }
        return commentaires;
    }

    
}