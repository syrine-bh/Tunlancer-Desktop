/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Models.Competence;
import java.util.List;

/**
 *
 * @author asus
 */public interface IServiceCompetence {
     public void Addcom(Competence c);
     public List <Competence> showcomp();
     public void updatecom(Competence c);
     public void deletecomm(Competence c);
     
      
}
