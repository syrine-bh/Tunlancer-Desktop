/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer.forum.gui;
import Models.Topics;

/**
 *
 * @author cyrinaa belguith
 */
final public class TopicsHolder {
    private int id;
     private final static TopicsHolder INSTANCE = new TopicsHolder();
  
  private TopicsHolder() {}
 
  
  public static TopicsHolder getInstance() {
    return INSTANCE;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public int getId() {
    return id;
    
    
}
}