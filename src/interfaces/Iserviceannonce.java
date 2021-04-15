/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Annonce;
import java.util.List;

/**
 *
 * @author siwar
 */
public interface Iserviceannonce {
     public void addAnnonce(Annonce  a);
     public List< Annonce> DisplayAnnonce();
     public int updateAnnonce(Annonce  a);
      public void deleteAnnonce(int  a);
     
}
