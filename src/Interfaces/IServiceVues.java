/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Models.Publication;
import Models.Reaction;
import Models.Users;
import Models.Vues;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Cyrina
 */
public interface IServiceVues {
    public void voirPublication(Vues vue) throws SQLException;
    public List<Vues> afficherVues(Publication p) throws SQLException;

}
