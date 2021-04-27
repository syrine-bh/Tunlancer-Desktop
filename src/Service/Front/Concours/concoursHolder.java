/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Front.Concours;

/**
 *
 * @author Hiba
 */
public class concoursHolder {
     private int id;
    private final static concoursHolder INSTANCE = new concoursHolder();

    public static concoursHolder getINSTANCE() {
        return INSTANCE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
