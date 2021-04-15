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
public class Users {
    private int id;
    private String nom;
    private String prenom;
    private int tel;	
    private String email;
    private String password;	
    private String pays;
    private String role;
    private String photo;
    private String bibliography;
    private boolean is_enabled;
    private int super_admin;
    private int age;
    private String sexe;

    public Users() {
    }

    public Users(int id) {
        this.id = id;
    }
    
    

    public Users(int id, String nom, String prenom, int tel, String email, String password, String pays, String role, String photo, String bibliography, boolean is_enabled, int super_admin, int age, String sexe) {
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
        this.is_enabled = is_enabled;
        this.super_admin = super_admin;
        this.age = age;
        this.sexe = sexe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public boolean isIs_enabled() {
        return is_enabled;
    }

    public void setIs_enabled(boolean is_enabled) {
        this.is_enabled = is_enabled;
    }

    public int getSuper_admin() {
        return super_admin;
    }

    public void setSuper_admin(int super_admin) {
        this.super_admin = super_admin;
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

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + ", email=" + email + ", password=" + password + ", pays=" + pays + ", role=" + role + ", photo=" + photo + ", bibliography=" + bibliography + ", is_enabled=" + is_enabled + ", super_admin=" + super_admin + ", age=" + age + ", sexe=" + sexe + '}';
    }
	   	
        	
                	
                	
                        	
                        	
                                	
                                					
}
