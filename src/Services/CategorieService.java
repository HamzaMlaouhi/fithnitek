/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.MyDB;
import Entities.Categorie;
import java.util.List;
import IServices.ICategorieService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wassim
 */
public class CategorieService implements ICategorieService{
    Connection con;
    
    public CategorieService(){
        con = MyDB.getInstance().getConnection();
    }

    @Override
    public void AjouterCategorie(Categorie ca) {
        try {
            String sql = "INSERT INTO categories (id, nom, description) VALUES ('"+ca.getId()+"', '"+ca.getName()+"', '"+ca.getDescription()+"')";
            Statement ste = con.createStatement();
            ste.executeUpdate(sql);} 
        catch (SQLException ex) {
            ex.getMessage();
        }    
    }

    @Override
    public void ModifierCategorie(Categorie ca) {
        try {
            String sql = "UPDATE categories SET nom=? ,description=? WHERE id=?";
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setString(1,ca.getName());
            ste.setString(2,ca.getDescription());
            ste.setInt(3,ca.getId());
            ste.executeUpdate();
            } 
        catch (SQLException ex) {
            ex.getMessage();
        }    
    }

    @Override
    public void SupprimerCategorie(Categorie ca) {
try {
            String sql = "DELETE FROM categories WHERE id=? ";
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setInt(1,ca.getId());
            ste.executeUpdate();
            } 
        catch (SQLException ex) {
            ex.getMessage();
        }        }

    @Override
    public List<Categorie> AfficherCategorie() {
        List<Categorie> listCats = new ArrayList<Categorie>();
        try {
            String sql = "Select * From categories ";
            Statement ste = con.createStatement();
            ResultSet res = ste.executeQuery(sql);
            while(res.next())
            {
                Categorie c = new Categorie(res.getInt("id"),res.getString("nom") , res.getString("description"));
                listCats.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCats;
    }
}
