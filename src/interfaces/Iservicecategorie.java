/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Categorie;

import java.util.List;

/**
 *
 * @author siwar
 */
public interface Iservicecategorie {
      public void addcategorie(Categorie c);
     public List< Categorie> Displaycategorie();
     public int update(Categorie  c);
      public void delete(int  c);
    
}
