/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Hamza Mlaouhi
 */
public class UtilisateurSession {

    private static UtilisateurSession instance;
    private String nom;
    private String prenom;
    private String email;

    private UtilisateurSession(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
       
    }

    public static UtilisateurSession getInstace(String nom, String prenom, String email) {
        if (instance == null) {
            instance = new UtilisateurSession(nom, prenom, email);
        }
        return instance;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "UtilisateurSession{" + "nom=" + nom + ", prenom=" + prenom + ", email=" + email + '}';
    }
    
    
    
}
