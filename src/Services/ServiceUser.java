/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Models.Users;
import Interfaces.IServiceUser;
import static Models.Crypt.getMd5;
import com.google.zxing.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import utils.MyConnection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tunlancer.TunlancerDesktop;


/**
 *
 * @author asus
 */
public class ServiceUser implements IServiceUser {
 private static ServiceUser instance;
    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }
      Connection cnx = MyConnection.getInstance().getConnection();
   
      @Override
    public void AddUser (Users u){
         try {
           String query="INSERT INTO users(Nom,Prenom,Tel,Email,Password,Pays,Role,Sexe,Age)"
                    + "VALUES ('"+u.getNom()+"','"+u.getPrenom()+"','"+u.getTel()+"','"+u.getEmail()+"','"+u.getPassword()+"','"+u.getPays()+"','"+u.getRole()+"','"+u.getSexe()+"','"+u.getAge()+"')";
            Statement st = MyConnection.getInstance().getConnection() .createStatement();
            st.executeUpdate(query);
            System.out.println("user ajouté");
            
        } 
         catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }   
      
     public ResultSet getSexe() throws SQLException {
        PreparedStatement stat = MyConnection.prepareStatement("select gender as sex from fosuser");
        return stat.executeQuery();
    }
    @Override
    public List<Users> AfficheUser() {
 List<Users> usersList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM users";
            Statement st = MyConnection.getInstance().getConnection()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Users u = new Users();
                u.setId(rs.getInt("id"));
                u.setNom(rs.getString("Nom"));
                u.setPrenom(rs.getString("Prenom"));
                u.setTel(rs.getInt("Tel"));
                u.setEmail(rs.getString("Email"));
                u.setPays(rs.getString("Pays"));
                u.setSexe(rs.getString("Sexe"));
                u.setAge(rs.getInt("Age"));
              
                usersList.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return usersList;    }

    @Override
    public void deleteUser(Users u) {
   try {
            String requete = "DELETE FROM Users where id=?";
            PreparedStatement pst = MyConnection.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setInt(1, u.getId());
            pst.executeUpdate();
            System.out.println("User supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          
            
    }    

    @Override
    public void updateUser(Users u) {
 try {
            String requete = "UPDATE Users SET Nom=? Prenom=? ,Tel=? , Email=?,Password=?,Pays=?,Sexe=?,Age=?,Role=?  WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getConnection().prepareStatement(requete);
            pst.setString(1, u.getNom());
            pst.setString(2, u.getPrenom());
            pst.setInt(3, u.getTel());
            pst.setString(4,u.getEmail());
            pst.setString(5,u.getPassword());
            pst.setString(6,u.getSexe());
            pst.setString(7,u.getRole());
            pst.setInt(8,u.getAge());
            pst.executeUpdate();
            System.out.println("User modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}    

    /**
     *
     * @param obj
     * @return
     * @throws SQLException
     */
    public Users getAdmin(Users obj) throws SQLException {
        if (obj.getEmail() == null) return null;
        String query = "Select * from users Where role =admin and email = '" + obj.getEmail() + "'";
            PreparedStatement pst = MyConnection.getInstance().getConnection().prepareStatement(query); 
         ResultSet rs = pst.executeQuery(query);
        if (rs.next()) {
            obj.setId(rs.getInt("id"));
            obj.setNom(rs.getString("nom"));
            obj.setPrenom(rs.getString("prenom"));
            obj.setTel(rs.getInt("tel"));
            obj.setEmail(rs.getString("email"));
            obj.setpassword(rs.getString("password"));
            obj.setPays(rs.getString("pays"));
            obj.setAge(rs.getInt("age")); 
            obj.setSexe(rs.getString("sexe")); 
            return obj;
        }
        return null;
    }
      @Override
    public int login(String email, String password) {
          Users user = null;
        PreparedStatement pst;
      
        ResultSet res;

        try {
           pst = MyConnection.getInstance().getConnection().prepareStatement("select * from `Users` where `email`=? and 'password' = ?");
            pst.setString(1, email);
            pst.setString(2, password);
            res = pst.executeQuery();
                 if (res.last())//kan il9a il user
            {
                if (password.equals(res.getString("password"))) {
                 
                     user = new Users (res.getInt("Id"), res.getString("Nom"), res.getString("Role"), res.getString("Prenom"), res.getString("Password"), res.getInt("Tel"),res.getString("Pays"),res.getInt("is_Enabled"),
                 res.getInt("Age"),res.getString("Sexe"),res.getInt("super_admin"));
                 TunlancerDesktop.getInstance().setLoggedUser(user);
                    return 3;//login correct
                } else {
                    return 2;//user s7i7 pass 8alet
                }
            } else {
                return 1;//user mal9ahech
            }
        } catch (Exception ex) {

            return 0;//error
        }
    }

    
     public int sex2(int e) throws SQLException {
        //int cl=getMonClubId(e);
        PreparedStatement pt=MyConnection.prepareStatement("select * from users where sexe=femme");
        //pt.setInt(1,cl);
        ResultSet rs=pt.executeQuery();
        int count=0;
        while(rs.next())
        {
            count++;
        }
        return count;
    }
     public int sex1(int e) throws SQLException {
        //int cl=getMonClubId(e);
        PreparedStatement pt=MyConnection.prepareStatement("select * from users where sexe=homme ");
        //pt.setInt(1,cl);
        ResultSet rs=pt.executeQuery();
        int count=0;
        while(rs.next())
        {
            count++;
        }
        return count;
    }

    public Object get(Users m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean findUtilisateur(String mail) {
        boolean ok = false;
      try {
            
            String requete = "SELECT * FROM users WHERE email=? ";
            PreparedStatement pst = MyConnection.getInstance().getConnection().prepareStatement(requete); 
            pst.setString(1, mail);
            
            ResultSet rs =  pst.executeQuery();            
            
            
            if (rs.next()) {
                ok=true;
            } else {
                System.out.println("not found");
                ok=false;
            }
         
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
      return ok;
    }
    
     @Override
    public void updateMotdepasse(String mail, String mdp) {
        try {
            String requete = "UPDATE users SET password=? WHERE email=?";
            PreparedStatement pst = MyConnection.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setString(1, getMd5(mdp));
            pst.setString(2, mail);
            pst.executeUpdate();
            System.out.println("Mot de passe modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
 /*@Override
     public void findByid(int id) {
      ServiceUser Users = new ServiceUser();
      try {
            
            String requete = "SELECT * FROM Users WHERE id=? ";
            PreparedStatement pst = MyConnection.getInstance().getConnection().prepareStatement(requete); 
            pst.setInt(1, id); 
            ResultSet rs =  pst.executeQuery();            
            
                if(rs.next()){
                   
                    Users.setId(rs.getInt(1));
                    Users.setNom(rs.getString(2));
                    Users.setPrenom(rs.getString(3));
                    Users.setMotDePasse(rs.getString(4));   
                     Users.setMail(rs.getString(5));
                     Users.setPhone(rs.getString(6));
                    Users.setDateC(rs.getDate(10));
                    Users.setPhoto(rs.getString(11));
                }
                    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
      return Users;
    }*/
  public void modifierEtatEnabledFalse(int id){ 
    try { 
    String req = "UPDATE `users` SET `is_enabled`=? WHERE `id`=? ";
PreparedStatement pst = MyConnection.getInstance().getConnection()
                    .prepareStatement(req);
    pst.setInt(1,0);
    pst.setInt(2, id);
        System.out.println("enable:"+1);
    pst.executeUpdate(); 
    } catch (SQLException ex) {
     Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
         }        
      }
    
    @Override
    public void findByid(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getPassword() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    
    
    
    
    



   
   

      

    


   