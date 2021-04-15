/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Models.Commentaire;
import Models.Publication;
import Models.Reaction;
import Models.Users;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Cyrina
 */
public interface IServiceCommentaire {
    public void AjouterCommentaire(Users utilisateur,Publication p,String contenuCommentaire) throws SQLException;
    public void supprimerCommentaire(Users utilisateur,Publication p) throws SQLException;
    public void modifierCommentaire(Users utilisateur,Publication p,String contenuCommentaire) throws SQLException;
    public List<Commentaire> afficherCommentaires(Publication p) throws SQLException;
}
