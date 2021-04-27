/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Hiba
 */
public class reponsetab {
    private String reponses;
    private boolean statu;

    public reponsetab(String reponses, boolean statu) {
        this.reponses = reponses;
        this.statu = statu;
    }

    public reponsetab() {
    }

   

    public String getReponses() {
        return reponses;
    }

    public void setReponses(String reponses) {
        this.reponses = reponses;
    }

    public boolean isStatu() {
        return statu;
    }

    public void setStatu(boolean statu) {
        this.statu = statu;
    }

    public String toString(char letter){
        return letter+":"+ reponses;
    }

















}
