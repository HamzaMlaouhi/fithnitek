/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author Wassim
 */
public class Personne extends Utilisateur {

    private Enum sexe;
    private String nom;
    private String prenom;
    private Byte image;
    private String cin;
    private int num_tel;

    public Personne() {
    }

    public Personne(Enum sexe, String nom, String prenom, Byte image, String cin, int num_tel, int id, 
            String username, String email, String password, Date last_login) {
        super(id, username, email, password, last_login);
        this.sexe = sexe;
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
        this.cin = cin;
        this.num_tel = num_tel;
    }

   

    public Enum getSexe() {
        return sexe;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Byte getImage() {
        return image;
    }

    public String getCin() {
        return cin;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setSexe(Enum sexe) {
        this.sexe = sexe;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setImage(Byte image) {
        this.image = image;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }
    
}
