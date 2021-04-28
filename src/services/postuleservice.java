/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Utils.MyConnection;
import entities.postuler;
import interfaces.Iservicepostuler;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author siwar
 */
public class postuleservice  implements Iservicepostuler{

    @Override
    public void addpostuler(postuler a) {

  try {
         String requete = "INSERT INTO `postuler` (`telephone`, `cv`, `email`, `nom`, `prenom`, `message`, `annonce_id`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = new MyConnection ().getConnection().prepareStatement(requete);
            ps.setString(1, a.getTelephone());
             ps.setString(2, a.getCv());
              ps.setString(3, a.getEmail());
              ps.setString(4, a.getNom());
              
                ps.setString(5, a.getPrenom());
                ps.setString(6, a.getMessage());

               //  ps.setInt(7,a.getAnnonce_id().getId());
                ps.setInt(7,a.getId());


            ps.executeUpdate();
            System.out.println("postulation effectuée avec succées");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public List<postuler> Displaypostuler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(postuler a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
