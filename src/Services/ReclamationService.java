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
import java.util.logging.Level;
import java.util.logging.Logger;

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

            String requeteInsert = "INSERT INTO reclamation(id, nom, typereclamation, message, idutilisateur, etat ) VALUES ('"+r.getId()+"','"+r.getNom()+"','"+r.getTypereclamation()+"','"+r.getMessage()+"','"+r.getIdutilisateur()+"','"+r.getEtat()+"')";
            ste.executeUpdate(requeteInsert);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerReclamation(int id)  {
         try {
            PreparedStatement pt = con.prepareStatement("delete from reclamation where id=?");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }



//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

       /* try {

            ste = con.createStatement();

            String requeteSupprime = "delete from reclamation where id=?";
            ste.executeUpdate(requeteSupprime);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }*/
        
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

            String query = "SELECT * FROM reclamation WHERE etat=0";
            ResultSet rstl = ste.executeQuery(query);

            while (rstl.next()){
                reclamation = new Reclamation(Integer.parseInt(rstl.getString(1)), rstl.getString(2), rstl.getString(3), rstl.getString(4), Integer.parseInt(rstl.getString(5)), Integer.parseInt(rstl.getString(6)));
                reclamationList.add(reclamation);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return reclamationList ;
    }
        
    @Override
    public int CountService(String Service) {
        int i=0;
        try {
            PreparedStatement pt;
            String query = "select * from reclamation where typereclamation='"+Service+"'";
            pt=con.prepareStatement(query);
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                i+=1;
            }
        }
         catch (SQLException ex) {
            System.out.println("Erreur " + ex.getMessage());
        }  
        return i;
    }

    @Override
    public List<Reclamation> trier() throws SQLException{
    List<Reclamation> arr=new ArrayList<>();
        ste = con.createStatement();
         String sql="select * from Reclamation order by id desc";
          ResultSet rs=ste.executeQuery(sql);
           while (rs.next()) {                
    int id=rs.getInt(1);
    String type_rec =rs.getString(2);
    String msg =rs.getString(3);
    int id_usr=rs.getInt(4);

    Reclamation s= new Reclamation(id, type_rec, msg, id_usr);
    arr.add(s);
     }
    return arr;   
    
    }
    
    @Override
    public void EnableEtat(Reclamation r) {
        try {
            PreparedStatement pt;
            String query = "update reclamation set etat=? where id='"+r.getId()+"'";
            System.out.println("..");
            pt=con.prepareStatement(query);
            pt.setInt(1,1);
            pt.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        }
        catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour del'etat de l'evenement " + ex.getMessage());
        }     
    }
    
    @Override
    public List<Reclamation> ListerReclamation (){

        Reclamation reclamation = null;
        try{
            ste = con.createStatement();

            String query = "SELECT * FROM reclamation WHERE etat=1";
            ResultSet rstl = ste.executeQuery(query);

            while (rstl.next()){
                reclamation = new Reclamation(Integer.parseInt(rstl.getString(1)), rstl.getString(2), rstl.getString(3), rstl.getString(4), Integer.parseInt(rstl.getString(5)), Integer.parseInt(rstl.getString(6)));
                reclamationList.add(reclamation);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return reclamationList ;
    }
    
}
