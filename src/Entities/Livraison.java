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
    private int idPersonne;
    private String depart;
    private String destination;
    private double poid_disponible;
    private String temp_estime;
    private Colis[] colis;
    private int note_livraison;

    public Livraison() {
    }

    public Livraison(int id, String depart, String destination, double poid_disponible, String temp_estime) {
        this.id = id;
        this.depart = depart;
        this.destination = destination;
        this.poid_disponible = poid_disponible;
        this.temp_estime = temp_estime;
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

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public double getPoid_disponible() {
        return poid_disponible;
    }

    public String getTemp_estime() {
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

    public void setPoid_disponible(double poid_disponible) {
        this.poid_disponible = poid_disponible;
    }

    public void setTemp_estime(String temp_estime) {
        this.temp_estime = temp_estime;
    }

    public void setColis(Colis[] colis) {
        this.colis = colis;
    }

    public void setNote_livraison(int note_livraison) {
        this.note_livraison = note_livraison;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id=" + id + ", depart=" + depart + ", destination=" + destination + ", poid_disponible=" + poid_disponible + ", temp_estime=" + temp_estime + ", colis=" + colis + ", note_livraison=" + note_livraison + '}';
    }

}
