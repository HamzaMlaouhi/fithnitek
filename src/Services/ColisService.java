/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.MyDB;
import Entities.Colis;
import Entities.Element;
import Entities.Livraison;
import Entities.Personne;
import IServices.IColisService;
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
 * @author Hamza Mlaouhi
 */
public class ColisService implements IColisService {

    private Connection con;

    public ColisService() {
        con = MyDB.getInstance().getConnection();
    }

    @Override
    public void AjouterColis(Colis c, List<Element> elements) {
        int idColis = 0;

        // Adding Package Request
        try {
            PreparedStatement pste = con.prepareStatement("INSERT INTO colis( depart , destination,date_limit , label , description,image,idUtilisateur)  VALUES (?,?,?,?,?,?,?);");
//            pste.setInt(1,c.getId());
            pste.setString(1, c.getDepart());
            pste.setString(2, c.getDestination());
            pste.setString(3, c.getDate_limit());
            pste.setString(4, c.getLabel());
            pste.setString(5, c.getDescription());
            pste.setString(6, c.getImage());
            pste.setInt(7, c.getIdUtilisateur());
            pste.executeUpdate();
            
                              
            
        } catch (SQLException ex) {
            ex.getMessage();
        }
        try {
            // Retrieving PAckage ID
            PreparedStatement pste = con.prepareStatement("Select id from colis where label=?");
            pste.setString(1, c.getLabel());
            ResultSet res = pste.executeQuery();
            while (res.next()) {
                idColis = res.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColisService.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Element e : elements) {
            try {
                PreparedStatement pste = con.prepareStatement("Insert Into coliselements Values (?,?)");
                pste.setInt(1, idColis);
                pste.setInt(2, e.getId());
                pste.executeUpdate();
                saveReward(calculateColisReward(idColis),idColis);

            } catch (SQLException ex) {
                Logger.getLogger(ColisService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void ModifierColis(Colis c) {
        try {
            String sql = "UPDATE colis SET depart=? ,destination=? ,date_limit=? ,label=? ,description=?  WHERE id=?";
            PreparedStatement pste = con.prepareStatement(sql);
            pste.setString(1, c.getDepart());
            pste.setString(2, c.getDestination());
            pste.setString(3, c.getDate_limit());
            pste.setString(4, c.getLabel());
            pste.setString(5, c.getDescription());
            pste.setInt(6, c.getId());
            pste.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    @Override
    public void SupprimerColis(Colis c) {
        try {
            PreparedStatement ste = con.prepareStatement("DELETE FROM colis WHERE id=? ;");
            ste.setInt(1, c.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    @Override
    public List<Colis> AfficherColis() {
        List<Colis> listColis = new ArrayList<Colis>();
        try {
            String sql = "Select * From colis ";
            Statement ste = con.createStatement();
            ResultSet res = ste.executeQuery(sql);
            while (res.next()) {
                Colis c = new Colis(res.getInt("id"), res.getString("depart"), res.getString("destination"), res.getString("date_limit"), res.getString("label"), res.getString("description"), res.getInt("idUtilisateur"));
                c.setImage(res.getString("image"));
                listColis.add(c);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return listColis;
    }

    public String getColisOwner(int colisID) {
        int idUtilisateur = 0;
        String colisowner = "";
        try {
            PreparedStatement pste = con.prepareStatement("Select idUtilisateur from colis where id=?");
            pste.setInt(1, colisID);
            ResultSet res = pste.executeQuery();
            while (res.next()) {
                idUtilisateur = res.getInt(1);
            }
            PreparedStatement pste1 = con.prepareStatement("Select nom,prenom from Personne where idUtilisateur=?");
            pste1.setInt(1, idUtilisateur);
            ResultSet res2 = pste1.executeQuery();
            while (res2.next()) {
                colisowner = res2.getString(1) + " " + res2.getString(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColisService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return colisowner;
    }

    @Override
    public void SendNotification(String content, int idColis, int idLivraison, int IdFrom, int IdTo) {

        try {
            PreparedStatement psteNotif = con.prepareStatement("INSERT INTO notifications(content,idColis,idLivraison,idPropC,idLivreur) VALUES (?,?,?,?,?);");
            psteNotif.setString(1, content);
            psteNotif.setInt(2, idColis);
            psteNotif.setInt(3, idLivraison);
            psteNotif.setInt(4, IdFrom);
            psteNotif.setInt(5, IdTo);
            psteNotif.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }

    }

    @Override
    public Colis getDetailColis(int idColis) {
        Colis c = new Colis();
        try {
            PreparedStatement pste = con.prepareStatement("Select * from colis where id=?");
            pste.setInt(1, idColis);
            ResultSet res = pste.executeQuery();
            while (res.next()) {
                c.setId(res.getInt("id"));
                c.setDepart(res.getString("depart"));
                c.setDestination(res.getString("destination"));
                c.setLabel(res.getString("label"));
                c.setDescription(res.getString("description"));
                c.setImage(res.getString("image"));
                c.setIdUtilisateur(res.getInt("idUtilisateur"));
                c.setReward(res.getDouble("reward"));
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return c;
    }
    
    public Colis getColisNotification(){
        Colis c = new Colis();
        try {
            PreparedStatement pste = con.prepareStatement("Select * from notifications where idLivreur=?");
            pste.setInt(1, Personne.user.getId());
            ResultSet res = pste.executeQuery();
            while (res.next()) {
               c =getDetailColis(res.getInt("idColis"));
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return c;
    }
    
    public double calculateColisReward(int idColis){
        double TotalPrix =0;
        int idElement =0 ;
        List<Element> elemets = new ArrayList<>();
        List<Integer> IdsElements = new ArrayList<>();
            try {
            PreparedStatement pste = con.prepareStatement("Select * from coliselements where idColis=?");
            pste.setInt(1, idColis);
            ResultSet ress = pste.executeQuery();
            while(ress.next()){
                IdsElements.add(ress.getInt(2));
            }
            for(Integer i : IdsElements){
            PreparedStatement pstee = con.prepareStatement("Select * from element where id=?");
            pstee.setInt(1, i);
            ResultSet resElement = pstee.executeQuery();
            while(resElement.next()){
                Element e = new Element();
                e.setPoid(resElement.getFloat("prix"));
                e.setPrix(resElement.getDouble("poid"));
                elemets.add(e);
            }
            }
            for(Element e : elemets){
                TotalPrix += e.getPrix()*e.getPoid();
            }
           
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return TotalPrix;
    }
    
    public void saveReward(double prixTotal,int idColis){
        try {
            PreparedStatement pste = con.prepareStatement("UPDATE colis SET reward=? where id=?");
            pste.setDouble(1, prixTotal);
            pste.setInt(2, idColis);
            pste.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

}
