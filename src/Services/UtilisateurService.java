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
import javax.swing.JOptionPane;
import Entities.Personne;
import Entities.UtilisateurSession;

/**
 *
 * @author Hamza Mlaouhi
 */
public class UtilisateurService implements IUtilisateurService {

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
            PreparedStatement pste = con.prepareStatement("INSERT INTO personne (idutilisateur,cin,nom,prenom,NumTel) VALUES (?,?,?,?,?);");

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
            ste.setInt(4, u.getId());
            ste.setString(1, u.getUsername());
            ste.setString(2, u.getPassword());
            ste.setString(3, u.getEmail());
            ste.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    @Override
    public void SupprimerUtilisateur(Utilisateur u) {
        try {
            String sql = "DELETE FROM fos_user WHERE id=? ";
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setInt(1, u.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    @Override
    public void Log_in(String username, String password) {

//           Utilisateur  acc=new Utilisateur();
//           username = acc.getUsername().getText(); 
//           password = acc.getPassword().getText();  
        String Pword = null;

        try {
//            String sql = "SELECT username, password FROM fos_user WHERE  username = ? and password=?";
            String sql ="Select * from fos_user F JOIN personne P ON F.id=P.idutilisateur WHERE  username = ? and password=?";
            System.out.println(sql);
            PreparedStatement ste = con.prepareStatement(sql);
            ste.setString(1, username);
            ste.setString(2, password);
            resultSet = ste.executeQuery();
            boolean loginSucc = resultSet.next();
            if (loginSucc) {
                Pword = resultSet.getString(password);
                UtilisateurSession.getInstace(resultSet.getString("email"), resultSet.getString("nom"), resultSet.getString("prenom"));
 
            }
            if (Pword.equals(password)) {
                JOptionPane.showMessageDialog(null, "go a head Login ", "Welcome FiThnitek", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Your password is Wrong ", "Try Again", 1);
            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

}
