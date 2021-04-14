/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.metamodel.SingularAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hiba
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUser", query = "SELECT u FROM Users u WHERE u.user = :user"),
    @NamedQuery(name = "Users.findByCurrentConnections", query = "SELECT u FROM Users u WHERE u.currentConnections = :currentConnections"),
    @NamedQuery(name = "Users.findByTotalConnections", query = "SELECT u FROM Users u WHERE u.totalConnections = :totalConnections"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByNom", query = "SELECT u FROM Users u WHERE u.nom = :nom"),
    @NamedQuery(name = "Users.findByPrenom", query = "SELECT u FROM Users u WHERE u.prenom = :prenom"),
    @NamedQuery(name = "Users.findByTel", query = "SELECT u FROM Users u WHERE u.tel = :tel"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByPays", query = "SELECT u FROM Users u WHERE u.pays = :pays"),
    @NamedQuery(name = "Users.findByRole", query = "SELECT u FROM Users u WHERE u.role = :role"),
    @NamedQuery(name = "Users.findByPhoto", query = "SELECT u FROM Users u WHERE u.photo = :photo"),
    @NamedQuery(name = "Users.findByBibliography", query = "SELECT u FROM Users u WHERE u.bibliography = :bibliography"),
    @NamedQuery(name = "Users.findByIsEnabled", query = "SELECT u FROM Users u WHERE u.isEnabled = :isEnabled"),
    @NamedQuery(name = "Users.findBySuperAdmin", query = "SELECT u FROM Users u WHERE u.superAdmin = :superAdmin"),
    @NamedQuery(name = "Users.findByAge", query = "SELECT u FROM Users u WHERE u.age = :age"),
    @NamedQuery(name = "Users.findBySexe", query = "SELECT u FROM Users u WHERE u.sexe = :sexe")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "USER")
    private String user;
    @Basic(optional = false)
    @Column(name = "CURRENT_CONNECTIONS")
    private long currentConnections;
    @Basic(optional = false)
    @Column(name = "TOTAL_CONNECTIONS")
    private long totalConnections;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "prenom")
    private String prenom;
    @Basic(optional = false)
    @Column(name = "tel")
    private int tel;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "pays")
    private String pays;
    @Basic(optional = false)
    @Column(name = "role")
    private String role;
    @Column(name = "photo")
    private String photo;
    @Column(name = "bibliography")
    private String bibliography;
    @Basic(optional = false)
    @Column(name = "is_enabled")
    private boolean isEnabled;
    @Column(name = "super_admin")
    private Integer superAdmin;
    @Basic(optional = false)
    @Column(name = "age")
    private int age;
    @Basic(optional = false)
    @Column(name = "sexe")
    private String sexe;
    @OneToMany(mappedBy = "userId")
    private Collection<Participation> participationCollection;

    public Users() {
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Users(Integer id, long currentConnections, long totalConnections, String nom, String prenom, int tel, String email, String password, String pays, String role, boolean isEnabled, int age, String sexe) {
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

    public Users(int i, SingularAttribute<Users, Long> currentConnections, SingularAttribute<Users, Long> totalConnections, String hiba, String farhat, String string, String hibaesprittn, String string0, String tunis, String freelancer, int i0, String string1, String femme) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Users[ id=" + id + " ]";
    }

    public Users(String user, long currentConnections, long totalConnections, Integer id, String nom, String prenom, int tel, String email, String password, String pays, String role, String photo, String bibliography, boolean isEnabled, Integer superAdmin, int age, String sexe, Collection<Participation> participationCollection) {
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

    public Users(String user, Integer id, String nom, String prenom, int tel, String email, String password, String pays, String role, String photo, String bibliography, boolean isEnabled, Integer superAdmin, int age, String sexe, Collection<Participation> participationCollection) {
        this.user = user;
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
    
}
