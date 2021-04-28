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

    /**
     *
     * @return
     * @throws SQLException
     */
    public List<Topics> getAllTopics() throws SQLException;
    public List<Topics> getAll() throws SQLException;
    public int getNbrTopic();

    public void supprimerTopic(int id) throws SQLException;

    public int updateTopic(int id, Topics topics) throws SQLException;
    
}
