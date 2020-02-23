/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author Hamza Mlaouhi
 */
public class Colis {
    private int id;
    private String depart;
    private String destination ;
    private String date_limit ;       
    private String label ;       
    private String description ;       
    private Element[] elements ;
    private int idUtilisateur ;       
   
    public Colis(){
        
    }

    public Colis(int id, String depart, String destination, String date_limit, String label, String description,  int idUtilisateur) {
        this.id = id;
        this.depart = depart;
        this.destination = destination;
        this.date_limit = date_limit;
        this.label = label;
        this.description = description;
      
        this.idUtilisateur = idUtilisateur;
    }
   
    
    public int getId() {
        return id;
    }

    public String getDepart() {
        return depart;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate_limit() {
        return date_limit;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }


    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDate_limit(String date_limit) {
        this.date_limit = date_limit;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    
    
}
