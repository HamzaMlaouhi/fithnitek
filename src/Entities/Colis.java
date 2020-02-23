/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

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
    private String Image;
    private List<Element> elements ;
    private int idUtilisateur ;    
     
   
    public Colis(){}

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

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setDepart(String depart) {
        this.depart = new SimpleStringProperty(depart).get() ;
        
    }
    public void setDestination(String destination) {
        this.destination =new SimpleStringProperty(destination).get() ;
    }
    public void setDate_limit(String date_limit) {
        this.date_limit = new SimpleStringProperty(date_limit).get();
    }
    public void setLabel(String label) {
        this.label = new SimpleStringProperty (label).get();
    }
    public void setDescription(String description) {
        this.description =new SimpleStringProperty (description).get();
    }
    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    
    
    @Override
    public String toString() {
        return "Colis{" + "id=" + id + ", depart=" + depart + ", destination=" + destination + ", date_limit=" + date_limit + ", label=" + label + ", description=" + description + ", elements=" + elements + ", idUtilisateur=" + idUtilisateur + '}';
    }
    
    
}
