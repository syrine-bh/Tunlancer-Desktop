/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Models.Publication;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Cyrina
 */
public interface IServicePublication {
    public void ajouterPublication(Publication p) throws SQLException;
    public void supprimerPublication(Publication p) throws SQLException;
    public void modifierPublication(Publication p) throws SQLException;
    public List<Publication> afficherPublications() throws SQLException;
    public Publication getPublicationById(int id) throws SQLException; 

}

