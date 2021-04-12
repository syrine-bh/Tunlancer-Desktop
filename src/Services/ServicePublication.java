/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.IServicePublication;

import Models.Publication;
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
    

    
    public ServicePublication() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void ajouterPublication(Publication p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerPublication(Publication p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierPublication(Publication p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Publication> afficherPublications() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Publication getPublicationById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }




    
}
