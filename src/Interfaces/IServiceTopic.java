/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Models.Topics;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author cyrinaa belguith
 */
public interface IServiceTopic {
    
    public void AjouterTopic(Topics t) throws Exception;
    public List<Topics> AfficherTopic() throws SQLException;
    
}
