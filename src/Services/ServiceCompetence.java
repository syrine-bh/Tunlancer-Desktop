/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Models.Competence;
import Interfaces.IServiceCompetence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;  
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import static javax.swing.UIManager.get;
import utils.MyConnection;

  

/**
 *
 * @author asus
 */
public class ServiceCompetence implements IServiceCompetence {
     @FXML
    private TextField tfid;
    @FXML
    private TextField tftitre1;
    @FXML
    private TextField tfdomaine1;
    
    @FXML
    private TextField tfuserid1;
    Connection cnx;
    
    public ServiceCompetence(){
      cnx = MyConnection.getInstance().getConnection();
  
    }
    
     @Override
    public void Addcom(Competence c) {
             try {
        String query = "insert into Competence (titre,domaine)"
                + " values(?,?)";
        PreparedStatement preparedStatement = cnx.prepareStatement(query);
        preparedStatement.setString(1, c.getTitre());
        preparedStatement.setString(2, c.getDomaine());
       
        preparedStatement.executeUpdate();
         System.out.println("competence ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
           
        }
         
            c = (Competence) get(c);
     
    } 
    

    @Override
    public List <Competence> showcomp() {
        List<Competence> Comp = new ArrayList<>();
      try {
        String query = "select * from Competence";
        
	 Statement st = MyConnection.getInstance().getConnection()
                    .createStatement();
            ResultSet rs =  st.executeQuery(query);
	
	while (rs.next()) {
	    Competence ex =new Competence();
	  
	    ex.setTitre(rs.getString("titre"));
            ex.setDomaine(rs.getString("domaine"));
          
	    Comp.add(ex);
        }
       
	 } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	return Comp;
        }

    @Override
    public void updatecom(Competence c) {
 


        try {
            
            
            
            String query = "UPDATE `competence` SET `titre`=?, `domaine`=? WHERE id=?";
            
            Connection cn = MyConnection.getInstance().getConnection();
            PreparedStatement ps;
            ps = cn.prepareStatement(query);
            
            ps.setString(1, tftitre1.getText());
            
            ps.setString(2, tfdomaine1.getText());
              ps.setString(3, tfid.getText());
          
            
             
                ps.executeUpdate();
          
            System.out.println("Modifiée avec succees !");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCompetence.class.getName()).log(Level.SEVERE, null, ex);
        }
    
   
    }
    void text( int id , String Titre, String domaine) {

        tfid.setText(String.valueOf(id));
        tftitre1.setText(Titre);
        tfdomaine1.setText(domaine) ;
        
        
    }

    @Override
    public void deletecomm(Competence c) {
 try {
            String requete = "DELETE FROM Competence where id=?";
            PreparedStatement pst = MyConnection.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.executeUpdate();
            System.out.println("Competence supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    
    
     
            
        
      

   

  
 
       
       
    }    
    
    

    
    

   
    
   
    

