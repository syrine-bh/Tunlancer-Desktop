/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Models.Replies;
import Models.Topics;
import Models.Users;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author cyrinaa belguith
 */
public interface IServiceReplies {
    
    public void AjouterRep(Replies rep);
    public void updateRep(Users user, Topics t, String contenu) throws SQLException;
    public List<Replies> afficherRep(Topics t) throws SQLException;
    public void DeleteRep(int rep);
    
}
