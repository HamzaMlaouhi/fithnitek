/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.MyDB;
import Entities.Utilisateur;
import IServices.IUtilisateurService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Entities.Personne;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hamza Mlaouhi
 */
public class UtilisateurService implements IUtilisateurService {

    private Statement ste;

    Connection con;
    private ResultSet resultSet;

    public UtilisateurService() {
        con = MyDB.getInstance().getConnection();
    }

    @Override
    public void Sign_up(Utilisateur u, Personne p) {
        int idutilisateur = 0;
        try {
            PreparedStatement pste = con.prepareStatement("INSERT INTO fos_user (username,email,password) VALUES (?,?,?);");
            pste.setString(1, u.getUsername());
            pste.setString(2, u.getEmail());
            pste.setString(3, u.getPassword());

            pste.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }

        try {
            PreparedStatement psteID = con.prepareStatement("SELECT id FROM fos_user WHERE email =?");
            PreparedStatement pste = con.prepareStatement("INSERT INTO personne (idutilisateur,cin,nom,prenom,NumTel,sexe,image) VALUES (?,?,?,?,?,?,?);");

            psteID.setString(1, u.getEmail());
            ResultSet res = psteID.executeQuery();
            while (res.next()) {
                idutilisateur = res.getInt("id");
            }

            pste.setInt(1, idutilisateur);
            pste.setInt(2, p.getCin());
            pste.setString(3, p.getNom());
            pste.setString(4, p.getPrenom());
            pste.setInt(5, p.getNum_tel());
            pste.setString(6, p.getSexe());
            pste.setString(7, p.getImage());


            pste.executeUpdate();

        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    @Override
    public void ModifierUtilisateur(Utilisateur u, Personne p) {
//        
        try {
            String sqlU = "UPDATE fos_user SET username=? ,email=?  WHERE id=?";
            String sqlP = "UPDATE personne SET nom=? ,prenom=?  WHERE idutilisateur =?";
            PreparedStatement steU = con.prepareStatement(sqlU);
            PreparedStatement steP = con.prepareStatement(sqlP);

            steU.setString(1, u.getUsername());
            steU.setString(2, u.getEmail());
            steU.setInt(3, Personne.user.getId());

            steP.setString(1, p.getNom());
            steP.setString(2, p.getPrenom());
            steP.setInt(3, Personne.user.getId());

            steU.executeUpdate();
            steP.executeUpdate();

        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    @Override
    public void SupprimerUtilisateur() {
        try {
            String sqlU = "DELETE FROM fos_user WHERE id=? ";
            String sqlP = "DELETE FROM personne WHERE idutilisateur=? ";

            PreparedStatement steU = con.prepareStatement(sqlU);
            PreparedStatement steP = con.prepareStatement(sqlP);

            steU.setInt(1, Personne.user.getId());
            steP.setInt(1, Personne.user.getId());
            steP.executeUpdate();
            steU.executeUpdate();


        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    @Override
    public boolean Log_in(String username, String password) {

        try {

            ste = con.createStatement();
            ResultSet rs = ste.executeQuery("Select P.*,F.* from fos_user F JOIN personne P ON F.id=P.idutilisateur WHERE  username ='" + username + "' and password='" + password + "'");

            while (rs.next()) {
                System.out.println("111");
                int id = rs.getInt("F.id");
                String nom = rs.getString("P.nom");
                String prenom = rs.getString("P.prenom");
                String image = rs.getString("P.image");
                int cin = rs.getInt("P.cin");
                int num_tel = rs.getInt("P.NumTel");
                String sexe = rs.getString("P.sexe");
                String userraplacement = rs.getString("F.username");
                String passreplacement = rs.getString("F.password");
                String email = rs.getString("F.email");
                Date last_login = rs.getDate("F.last_login");

                System.out.println(id);

                Personne.user = new Personne(sexe, nom, prenom, image, cin, num_tel, id, username, password, email, null);

                System.out.println(Personne.user);
//  }
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
    
    public String getImageName(){
            String imageName="";
        try {
                        ste = con.createStatement();
            ResultSet rs = ste.executeQuery("Select image from  personne where idutilisateur="+Personne.user.getId());
            while(rs.next()){
                imageName=rs.getString("image");
            }   
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
                    return imageName;

    }
    
    
    public boolean checkNotification(){
         boolean verif=false;
        try {
           ste = con.createStatement();
            ResultSet rs = ste.executeQuery("Select * from notifications where idLivreur="+Personne.user.getId());
             verif = rs.next();
           
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return verif;
    }
    
    public void deleteNotification(){
         try {
            String sqlU = "DELETE FROM notifications WHERE idLivreur ="+Personne.user.getId();
            PreparedStatement stee = con.prepareStatement(sqlU);
            stee.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    
    
    public Utilisateur getUserById(int idUser){
           Utilisateur sender = new Utilisateur();
        try {
            PreparedStatement pste = con.prepareStatement("Select * from fos_user where id=?");
            pste.setInt(1, Personne.user.getId());
            ResultSet res = pste.executeQuery();
            while (res.next()) {
              sender.setEmail(res.getString("email"));
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return sender;
    }
    
    public Utilisateur getNotificationSenderInfo(){
           Utilisateur sender = new Utilisateur();
        try {
            PreparedStatement pste = con.prepareStatement("Select * from notifications where idLivreur=?");
            pste.setInt(1, Personne.user.getId());
            ResultSet res = pste.executeQuery();
            while (res.next()) {
              sender = getUserById(res.getInt("idPropC"));
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return sender;
    }
    
    
    
    
    
    
    
}
