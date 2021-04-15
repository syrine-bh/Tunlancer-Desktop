/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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


    private Users utilisateur;
    private List<Reaction> reactions;
    private List<Commentaire> commentaires;
    private List<Signaler> signaux;
    private List<Vues> vues;

    private ImageView image_file;
    
    public Publication() {
        utilisateur = new Users(utilisateur_id);
        reactions = new ArrayList<>();
        commentaires = new ArrayList<>();
        signaux = new ArrayList<>();
        vues = new ArrayList<>();
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
        
        utilisateur = new Users(1, "cyrine", "Ben hassine", 92047480, "syrine.benhassine@esprit.tn", "123456", "canada", "user", "cyrine.png", "bio", true, 1, 23, "femme");
        reactions = new ArrayList<>();
        commentaires = new ArrayList<>();
        signaux = new ArrayList<>();
        vues = new ArrayList<>();
    }



    public Users getUtilisateur() {
        return utilisateur;
    }
    public void setUtilisateur(Users utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }
    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }
    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public List<Signaler> getSignaux() {
        return signaux;
    }
    public void setSignaux(List<Signaler> signaux) {
        this.signaux = signaux;
    }

    public List<Vues> getVues() {
        return vues;
    }
    public void setVues(List<Vues> vues) {
        this.vues = vues;
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
             archivePublication="Archivée";
         }
        return "Publication :" + "id=" + id + ", description=" + description + ", type=" + typePublication + ", archive=" + archivePublication + ", image_name=" + image_name + ", localisation=" + localisation ;
    }
    


    
}
