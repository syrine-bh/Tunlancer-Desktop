/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.postuler;
import java.util.List;

/**
 *
 * @author siwar
 */
public interface Iservicepostuler {
     public void addpostuler(postuler a);
     public List< postuler> Displaypostuler();
     public int update(postuler  a);
      public void delete(int  a);
    
}
