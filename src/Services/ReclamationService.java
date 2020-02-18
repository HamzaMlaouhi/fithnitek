/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.MyDB;
import Entities.Reclamation;
import IServices.IReclamationService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ghassen
 */
public class ReclamationService implements IReclamationService{

    List<Reclamation> reclamationList = new ArrayList<>();

    private Connection con ;
    private Statement ste;
    //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fithnitek","root", "");

    public ReclamationService(){
        con = MyDB.getInstance().getConnection();
    }


    @Override
    public void ajouterReclamation(Reclamation r)  {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        try{

            ste = con.createStatement();

            String requeteInsert = "INSERT INTO reclamation(id, typereclamation, message, idutilisateur ) VALUES ('"+r.getId()+"','"+r.getTypereclamation()+"','"+r.getMessage()+"','"+r.getIdutilisateur()+"')";
            ste.executeUpdate(requeteInsert);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerReclamation(Reclamation r)  {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        try {

            ste = con.createStatement();

            String requeteSupprime = "DELETE FROM reclamation WHERE id ="+r.getId();
            ste.executeUpdate(requeteSupprime);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Reclamation rechercherReclamation(Reclamation r) {
        Reclamation reclamation = null;

        try {
            ste = con.createStatement();

            String rechReq = "SELECT * FROM reclamation WHERE id = "+r.getId();
            ResultSet rslt = ste.executeQuery(rechReq);
            while (rslt.next()){
                reclamation= new Reclamation(Integer.parseInt(rslt.getString(1)), rslt.getString(2), rslt.getString(3), Integer.parseInt(rslt.getString(4)));
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return reclamation;
    }

        public List<Reclamation> displayReclamation (){

        Reclamation reclamation = null;
        try{
            ste = con.createStatement();

            String query = "SELECT * FROM reclamation";
            ResultSet rstl = ste.executeQuery(query);

            while (rstl.next()){
                reclamation = new Reclamation(Integer.parseInt(rstl.getString(1)), rstl.getString(2), rstl.getString(3), Integer.parseInt(rstl.getString(4)));
                reclamationList.add(reclamation);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return reclamationList ;
    }

}
