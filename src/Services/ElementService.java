 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.MyDB;
import Entities.Element;
import IServices.IElementService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abder
 */
public class ElementService implements IElementService{
    private Connection con;
    private Statement ste;
    
    
    public ElementService(){
    con = MyDB.getInstance().getConnection();
    
}

    @Override
    public void AjouterElemnet(Element e){
        try {
            PreparedStatement pste = con.prepareStatement("INSERT INTO element VALUES (?,?,?,?,?,?);");
            pste.setInt(1,e.getId());
            pste.setString(2,e.getName());
            pste.setInt(3,e.getQuantite());
            pste.setDouble(4,e.getPrix());
            pste.setDouble(5,e.getPoid()); 
            pste.setString(6,e.getImage()); 
            pste.executeUpdate();
        } catch (SQLException ex) {
           ex.getMessage();
        } 
    }

    @Override
    public void ModifierElement(Element e) {
        try  {
               String sql = "UPDATE element SET name=?,quantite=?,prix=?,poid=?,image=? WHERE id=?";
                PreparedStatement este = con.prepareStatement(sql);
                este.setString(1,e.getName());
                este.setInt(2,e.getQuantite());
                este.setDouble(3,e.getPrix());
                este.setDouble(4,e.getPoid());
                este.setString(5,e.getImage());
                este.setInt(6,e.getId());
                este.executeUpdate();
        }  
        catch (SQLException ex) {
        ex.getMessage();
        }
        
        }

    @Override
    public void SupprimerElement(Element e) {
            try {
                PreparedStatement ste = con.prepareStatement("DELETE FROM element WHERE id=? ;");
                ste.setInt(1,e.getId());
                 ste.executeUpdate();
        }catch(SQLException ex){
            ex.getMessage();
     
            }
    }

    @Override
    public List<Element> AfficherElement() {
        
     List<Element> listElement = new ArrayList<Element>();
        try {
           String sql = "Select * From element ";
            Statement ste = con.createStatement();
            ResultSet eres = ste.executeQuery(sql);
            while(eres.next()){
                Element e = new Element();
                e.setId(eres.getInt("id"));
                e.setName(eres.getString("Name"));
                e.setPrix(eres.getDouble("prix"));
                e.setPoid(eres.getFloat("poid"));
                e.setImage(eres.getString("image"));
    
                listElement.add(e);
            }  

}       catch (SQLException ex) {
    ex.getMessage(); 
            
    
        }         

        return listElement;
    }
}
