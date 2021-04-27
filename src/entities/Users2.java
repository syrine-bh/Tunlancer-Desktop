/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

//import java.io.Serializable;
//import java.util.Collection;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.NamedQueries;
//
//import javax.persistence.NamedQuery;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Hiba
 */

public class Users2 {

   
    private String user;
    private long currentConnections;

    private long totalConnections;
   
    private Integer id;
    
    private String nom;
   
    private String prenom;
    
    private int tel;
  
    private String email;
    
    private String password;
    
    private String pays;
   
    private String role;
   
    private String photo;
 
    private String bibliography;
    
    private boolean isEnabled;

    private Integer superAdmin;
   
    private int age;
    
    private String sexe;
   
    private Collection<Participation> participationCollection;

    public Users2() {
    }

    public Users2(Integer id) {
        this.id = id;
    }

    public Users2(Integer id, long currentConnections, long totalConnections, String nom, String prenom, int tel, String email, String password, String pays, String role, boolean isEnabled, int age, String sexe) {
        this.id = id;
        this.currentConnections = currentConnections;
        this.totalConnections = totalConnections;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.email = email;
        this.password = password;
        this.pays = pays;
        this.role = role;
        this.isEnabled = isEnabled;
        this.age = age;
        this.sexe = sexe;
    }

  
    
    

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getCurrentConnections() {
        return currentConnections;
    }

    public void setCurrentConnections(long currentConnections) {
        this.currentConnections = currentConnections;
    }

    public long getTotalConnections() {
        return totalConnections;
    }

    public void setTotalConnections(long totalConnections) {
        this.totalConnections = totalConnections;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBibliography() {
        return bibliography;
    }

    public void setBibliography(String bibliography) {
        this.bibliography = bibliography;
    }

    public boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Integer getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(Integer superAdmin) {
        this.superAdmin = superAdmin;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    @XmlTransient
    public Collection<Participation> getParticipationCollection() {
        return participationCollection;
    }

    public void setParticipationCollection(Collection<Participation> participationCollection) {
        this.participationCollection = participationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users2)) {
            return false;
        }
        Users2 other = (Users2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Users[ id=" + id + " ]";
    }

    public Users2(String user, long currentConnections, long totalConnections, Integer id, String nom, String prenom, int tel, String email, String password, String pays, String role, String photo, String bibliography, boolean isEnabled, Integer superAdmin, int age, String sexe, Collection<Participation> participationCollection) {
        this.user = user;
        this.currentConnections = currentConnections;
        this.totalConnections = totalConnections;
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.email = email;
        this.password = password;
        this.pays = pays;
        this.role = role;
        this.photo = photo;
        this.bibliography = bibliography;
        this.isEnabled = isEnabled;
        this.superAdmin = superAdmin;
        this.age = age;
        this.sexe = sexe;
        this.participationCollection = participationCollection;
    }

    public Users2(Integer id, String nom, String prenom, int tel, String email, String password, String pays, String role, boolean isEnabled, int age, String sexe) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.email = email;
        this.password = password;
        this.pays = pays;
        this.role = role;
        this.isEnabled = isEnabled;
        this.age = age;
        this.sexe = sexe;
    }
    
}
