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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;

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
            PreparedStatement pste = con.prepareStatement("INSERT INTO element(name,quantite,prix,poid,image)  VALUES (?,?,?,?,?);");

            
            pste.setString(1,e.getName());
            pste.setInt(2,e.getQuantite());
            pste.setDouble(3,e.getPrix()); 
            pste.setFloat(4,e.getPoid());
            pste.setString(5,e.getImage()); 
            pste.executeUpdate();
        } catch (SQLException ex) {
           ex.getMessage();
        } 
//        try {
//            
//            ste = con.createStatement();
//            String requeteInsert = "INSERT INTO element (id,name,quantite,prix,poid,image) VALUES ('"+e.getId()+ "', '" + e.getName()+ "', '" + e.getQuantite()+ "','"+e.getPrix()+"','"+e.getPoid()+"','"+e.getImage()+"');";
//            ste.executeUpdate(requeteInsert);
//        } catch (SQLException ex) {
//            ex.getMessage();
//        }
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void SupprimerElement(Element e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Element> AfficherElement() {

List<Element> listElement = new ArrayList<Element>();
        try {
            String sql = "Select * From element ";
            Statement ste = con.createStatement();
            ResultSet res = ste.executeQuery(sql);
            while(res.next())
            {
                Element e = new Element(res.getInt("id"),res.getString("name"),res.getInt("quantite"),res.getDouble("prix"),res.getFloat("poid"),res.getString("image") );
                listElement.add(e);
            }
     }catch(SQLException ex){
         ex.getMessage(); 
     }
        return listElement;
    }

    @Override
    public List<Element> AfficherListElementColis(int id) {
        List<Integer> elementsId = new ArrayList<>();
        List<Element> listElmentColis = new ArrayList<>();
        try {
            String sql = "Select idElement From coliselements where idColis="+id;
            Statement stee = con.createStatement();
            ResultSet res = stee.executeQuery(sql);
            while(res.next())
            {
             elementsId.add(res.getInt(1));
            }
     }catch(SQLException ex){
         ex.getMessage(); 
     }
        for(Integer i : elementsId){
            try {
            String sql = "Select * From element where id="+i;
            Statement stee = con.createStatement();
            ResultSet res = stee.executeQuery(sql);
            while(res.next())
            {
             Element e = new Element();
             e.setName(res.getString(2));
             e.setPoid(res.getFloat(5));
             e.setPrix(res.getDouble(4));
             e.setQuantite(3);
             listElmentColis.add(e);
            }
     }catch(SQLException ex){
         ex.getMessage(); 
     }
        }
        return listElmentColis;
        

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

}