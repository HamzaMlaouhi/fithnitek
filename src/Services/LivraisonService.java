/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.MyDB;
import Entities.Livraison;
import IServices.ILivraisonService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wassim
 */
public class LivraisonService implements ILivraisonService{
     private Connection con;
     private Statement ste;
    
     public LivraisonService() {
        con = MyDB.getInstance().getConnection();

    }

    @Override
    public void AjouterLivraison(Livraison l) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         try {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            ste = con.createStatement();
            String requeteInsert = "INSERT INTO `FiThnitek`.`Livraison` (`id`, `depart`, `destination`, `poid_disponible`,`temp_estime`,`note_livraison`) VALUES ('" + l.getId() + "', '" + l.getDepart() + "', '" + l.getDestination() + "' ,'" +l.getPoid_disponible()+"', '"+l.getTemp_estime()+"', '"+l.getNote_livraison()+"');";
            ste.executeUpdate(requeteInsert);
        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ModifierLivraison(Livraison l) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       try {
            String sql = "UPDATE Livraison SET depart=? ,destination=? ,poid_disponible=? ,temp_estime=? ,note_livraison=?  WHERE id=?";
            PreparedStatement pste = con.prepareStatement(sql);
            pste.setString(1,l.getDepart());
            pste.setString(2,l.getDestination());
            pste.setDouble(3,l.getPoid_disponible());
            pste.setString(4,l.getTemp_estime());
            pste.setInt(5,l.getNote_livraison());
            pste.setInt(6,l.getId());
            pste.executeUpdate();
            } 
        catch (SQLException ex) {
            ex.getMessage();
        }  
    }

    @Override
    public void SupprimerLivraison(Livraison l) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
      PreparedStatement ste = con.prepareStatement("DELETE FROM Livraison WHERE id=? ;");
      ste.setInt(1,l.getId());
      ste.executeUpdate();
        }catch(SQLException ex){
            ex.getMessage();
        }
    }

    @Override
    public List<Livraison> AfficherLivraison() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Livraison> arr=new ArrayList<>();

        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            ste=con.createStatement();
            ResultSet rs=ste.executeQuery("select * from Livraison");
            while (rs.next()) {
                int id=rs.getInt(1);
                String depart=rs.getString("depart");
                String destination =rs.getString("destination");
                float pDesp =rs.getFloat("poid_disponible");
                String tempEst =rs.getString("temp_estime");
                
                int noteLiv=rs.getInt("note_livraison");
                
                Livraison l=new Livraison(id, depart, destination, pDesp,tempEst,noteLiv);
                arr.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
                    return arr;

    
    }
    
}
