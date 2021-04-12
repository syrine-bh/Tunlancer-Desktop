/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cyrina
 */
public class Publication {
    private int id;
    private int utilisateur_id;
    private String description;
    private int type;	
    private int archive;
    private String image_name;	
    private String localisation;


    
    public Publication() {

    }

    public Publication(int id) {
        this.id = id;
    }
    
    

    public Publication(int id, int utilisateur_id, String description, int type, int archive, String image_name, String localisation) {
        this.id = id;
        this.utilisateur_id = utilisateur_id;
        this.description = description;
        this.type = type;
        this.archive = archive;
        this.image_name = image_name;
        this.localisation = localisation;
        
    }
  
   
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(int utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getArchive() {
        return archive;
    }

    public void setArchive(int archive) {
        this.archive = archive;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    @Override
    public String toString() {
        String typePublication="Publication";
        String archivePublication="Non archivée"; 
        if (type==1) {
            typePublication="Story";
        }
        if (archive==1) {
             typePublication="Archivée";
         }
        return "Publication :" + "id=" + id + ", description=" + description + ", type=" + typePublication + ", archive=" + typePublication + ", image_name=" + image_name + ", localisation=" + localisation ;
    }

    
}
