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
import java.sql.SQLException;
import java.sql.Statement;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SupprimerElement(Element e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Element> AfficherElement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}