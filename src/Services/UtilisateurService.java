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
}
