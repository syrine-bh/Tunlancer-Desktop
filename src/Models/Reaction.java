/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;



/**
 *
 * @author Cyrina
 */
public class Reaction {
    
 				
    private int id_reaction;
    private int publication_id;
    private int id_utilisateur_id;
    private int typeReaction;	
    
    private Users utilisateur;
    private Publication publication;

    public Reaction() {
        utilisateur = new Users(id_utilisateur_id);
        publication = new Publication(publication_id);
    }

    
    public Reaction(int id_reaction, int publication_id, int id_utilisateur_id, int typeReaction) {
        this.id_reaction = id_reaction;
        this.publication_id = publication_id;
        this.id_utilisateur_id = id_utilisateur_id;
        this.typeReaction = typeReaction;
        
        utilisateur = new Users(id_utilisateur_id);
        publication = new Publication(publication_id);
    }

    public int getId_reaction() {
        return id_reaction;
    }

    public void setId_reaction(int id_reaction) {
        this.id_reaction = id_reaction;
    }

    public int getPublication_id() {
        return publication_id;
    }

    public void setPublication_id(int publication_id) {
        this.publication_id = publication_id;
    }

    public int getId_utilisateur_id() {
        return id_utilisateur_id;
    }

    public void setId_utilisateur_id(int id_utilisateur_id) {
        this.id_utilisateur_id = id_utilisateur_id;
    }

    public int getTypeReaction() {
        return typeReaction;
    }

    public void setTypeReaction(int typeReaction) {
        this.typeReaction = typeReaction;
    }

    @Override
    public String toString() {
        String reaction="";
        switch (typeReaction) {
        case 1:
          reaction="J'adore";
          break;
        case 2:
          reaction="J'adore";
          break;
        case 3:
          reaction="J'adore";
          break;
        case 4:
          reaction="J'adore";
          break;
      }
        return "Reaction{" + ", id_utilisateur_id=" + id_utilisateur_id + ", type Reaction=" + reaction + '}';
    }
    
    
}
