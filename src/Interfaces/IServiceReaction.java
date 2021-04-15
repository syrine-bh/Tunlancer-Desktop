/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Models.Publication;
import Models.Reaction;
import Models.Users;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Cyrina
 */
public interface IServiceReaction {
    public void reagirPublication(Users utilisateur,Publication p,int typeReaction) throws SQLException;
    public void supprimerReation(Users utilisateur,Publication p) throws SQLException;
    public void modifierReaction(Users utilisateur,Publication p,int typeReaction) throws SQLException;
    public List<Reaction> afficherReactions(Publication p) throws SQLException;
    public List<Reaction> afficherReactionsByUtilisateur(Users utilisateur) throws SQLException;
}  