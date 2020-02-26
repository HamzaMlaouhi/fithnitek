/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;
import javafx.scene.control.ComboBox;

/**
 *
 * @author Wassim
 */
public class Personne extends Utilisateur {

    private String sexe;
    private String nom;
    private String prenom;
    private String image;
    private int cin;
    private int num_tel;
    public static Personne user;
    public static int Code;

    public Personne() {
    }

    public Personne(String sexe, String nom, String prenom, String image, int cin, int num_tel, int id,
            String username, String email, String password, Date last_login) {
        super(id, username, email, password, last_login);
        this.sexe = sexe;
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
        this.cin = cin;
        this.num_tel = num_tel;
    }

    public String getSexe() {
        return sexe;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getImage() {
        return image;
    }

    public int getCin() {
        return cin;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    @Override
    public String toString() {
        return "Personne{" + super.toString() + "sexe=" + sexe + ", nom=" + nom + ", prenom=" + prenom + ", image=" + image + ", cin=" + cin + ", num_tel=" + num_tel + '}';
    }

    public void setSexe(ComboBox<String> ZoneSexe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
