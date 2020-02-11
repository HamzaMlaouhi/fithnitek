 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.MyDB;
import Entities.Categorie;
import Entities.Element;
import IServices.IElementService;
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
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            ste = con.createStatement();
            String requeteInsert = "INSERT INTO element (id,name,quantite,prix,poid,image) VALUES ('"+e.getId()+ "', '" + e.getName()+ "', '" + e.getQuantite()+ "','"+e.getPrix()+"','"+e.getPoid()+"','"+e.getImage()+"');";
            ste.executeUpdate(requeteInsert);
        } catch (SQLException ex) {
            Logger.getLogger(ElementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ModifierElement(Element e) {
try {
            String sql = "UPDATE element SET name=? ,quantite=? , poid=? ,prix=?,image=? WHERE id=?";
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setInt(6,e.getId());
            ste.setString(1,e.getName());
            ste.setFloat(2,e.getQuantite());
            ste.setDouble(3,e.getPrix());
            ste.setFloat(4,e.getPoid());
            ste.setString(5,e.getImage());


            
            ste.executeUpdate();
            } 
        catch (SQLException ex) {
            ex.getMessage();
        }       }

    @Override
    public void SupprimerElement(Element e) {
try {
            String sql = "DELETE FROM element WHERE id=? ";
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setInt(1,e.getId());
            ste.executeUpdate();
            } 
        catch (SQLException ex) {
            ex.getMessage();
        }     
    }

    @Override
    public List<Element> AfficherElement() {
List<Element> listColis = new ArrayList<Element>();
        try {
            String sql = "Select * From colis ";
            Statement ste = con.createStatement();
            ResultSet res = ste.executeQuery(sql);
            while(res.next())
            {
                Element e = new Element(res.getInt("id"),res.getString("name"),res.getInt("quantite"),res.getDouble("prix"),res.getFloat("poid"),res.getString("image") );
                listColis.add(e);
            }
     }catch(SQLException ex){
         ex.getMessage(); 
     }
        return listColis;
    }
    }    

