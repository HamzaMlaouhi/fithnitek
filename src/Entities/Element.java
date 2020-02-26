/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Wassim
 */
public class Element {
    private int id ;
    private String name ;
    private int quantite ;
    private Double prix ;
    private Float poid ;
    private Categorie[] categories ;
    private String image ;
    public Element(){
        
    }

    public Element(int id, String name, int quantite, Double prix, Float poid) {
        this.id = id;
        this.name = name;
        this.quantite = quantite;
        this.prix = prix;
        this.poid = poid;
    }
     
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantite() {
        return quantite;
    }

    public Double getPrix() {
        return prix;
    }

    public Float getPoid() {
        return poid;
    }

    public Categorie[] getCategories() {
        return categories;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public void setPoid(Float poid) {
        this.poid = poid;
    }

    public void setCategories(Categorie[] categories) {
        this.categories = categories;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return  name ;
    }
    
    
    
            }
