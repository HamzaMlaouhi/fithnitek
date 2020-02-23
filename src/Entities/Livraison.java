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
public class Livraison {

    private int id;
    private String depart;
    private String destination;
    private float poid_disponible;
    private Date temp_estime;
    private Colis[] colis;
    private int note_livraison;

    public Livraison() {
    }

    public Livraison(int id, String depart, String destination, float poid_disponible, Date temp_estime, Colis[] colis, int note_livraison) {
        this.id = id;
        this.depart = depart;
        this.destination = destination;
        this.poid_disponible = poid_disponible;
        this.temp_estime = temp_estime;
        this.colis = colis;
        this.note_livraison = note_livraison;
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

    public float getPoid_disponible() {
        return poid_disponible;
    }

    public Date getTemp_estime() {
        return temp_estime;
    }

    public Colis[] getColis() {
        return colis;
    }

    public int getNote_livraison() {
        return note_livraison;
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

    public void setPoid_disponible(float poid_disponible) {
        this.poid_disponible = poid_disponible;
    }

    public void setTemp_estime(Date temp_estime) {
        this.temp_estime = temp_estime;
    }

    public void setColis(Colis[] colis) {
        this.colis = colis;
    }

    public void setNote_livraison(int note_livraison) {
        this.note_livraison = note_livraison;
    }

}
