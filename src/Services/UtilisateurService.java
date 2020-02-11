/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.MyDB;
import Entities.Categorie;
import Entities.Utilisateur;
import IServices.IUtilisateurService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Hamza Mlaouhi
 */
public class UtilisateurService implements IUtilisateurService{
    Connection con ;
    
    public UtilisateurService(){
            con = MyDB.getInstance().getConnection();
}
    
    @Override
    public void Sign_up(Utilisateur u) {    
            try {
            PreparedStatement pste = con.prepareStatement("INSERT INTO fos_user (id,username,email,password) VALUES (?,?,?,?);");
            pste.setInt(1,u.getId());
            pste.setString(2,u.getUsername());
            pste.setString(3,u.getPassword());
            pste.setString(4,u.getEmail());
            pste.executeUpdate();
        } catch (SQLException ex) {
           ex.getMessage();
        } 
    }
    
     @Override
    public void ModifierUtilisateur(Utilisateur u) {
 try {
            String sql = "UPDATE fos_user SET username=? ,email=? , password=? WHERE id=?";
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setInt(4,u.getId());
            ste.setString(1,u.getUsername());
            ste.setString(2,u.getPassword());
            ste.setString(3,u.getEmail());
            ste.executeUpdate();
            } 
        catch (SQLException ex) {
            ex.getMessage();
        }   
    }

    @Override
    public void SupprimerUtilisateur(Utilisateur u) {
try {
            String sql = "DELETE FROM fos_user WHERE id=? ";
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setInt(1,u.getId());
            ste.executeUpdate();
            } 
        catch (SQLException ex) {
            ex.getMessage();
        }        
    }
    

    @Override
    public void Log_in(String username , String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
