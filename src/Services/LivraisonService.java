/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.MyDB;
import Entities.Livraison;
import Entities.Personne;
import IServices.ILivraisonService;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Wassim
 */
public class LivraisonService implements ILivraisonService {

    private Connection con;
    private Statement ste;

    public LivraisonService() {
        con = MyDB.getInstance().getConnection();

    }

    @Override
    public void AjouterLivraison(Livraison l) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            ste = con.createStatement();
            String requeteInsert = "INSERT INTO `FiThnitek`.`Livraison` ( `depart`, `destination`, `poid_disponible`,`temp_estime`,idLivreur) VALUES ('" + l.getDepart() + "', '" + l.getDestination() + "' ,'" + l.getPoid_disponible() + "', '" + l.getTemp_estime() + "', '" + Personne.user.getId() + "');";
            ste.executeUpdate(requeteInsert);
        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ModifierLivraison(Livraison l) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            String sql = "UPDATE Livraison SET depart=? ,destination=? ,poid_disponible=? ,temp_estime=? WHERE id=?";
            PreparedStatement pste = con.prepareStatement(sql);
            pste.setString(1, l.getDepart());
            pste.setString(2, l.getDestination());
            pste.setDouble(3, l.getPoid_disponible());
            pste.setString(4, l.getTemp_estime());
            pste.setInt(5, l.getId());
            pste.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    @Override
    public void SupprimerLivraison(Livraison l) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            PreparedStatement ste = con.prepareStatement("DELETE FROM Livraison WHERE id=? ;");
            ste.setInt(1, l.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    @Override
    public List<Livraison> AfficherLivraison() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Livraison> arr = new ArrayList<>();

        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery("select * from livraison");
            while (rs.next()) {
                Livraison l = new Livraison(rs.getInt("id"), rs.getString("depart"), rs.getString("destination"), rs.getDouble("poid_disponible"), rs.getString("temp_estime"));
                l.setIdPersonne(rs.getInt("idLivreur"));
                arr.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;

    }

    @Override
    public List<Livraison> AfficherLivraisonPerson(int idP) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Livraison> arr = new ArrayList<>();

        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery("select * from livraison WHERE idLivreur=" + idP);
            while (rs.next()) {
                Livraison l = new Livraison(rs.getInt("id"), rs.getString("depart"), rs.getString("destination"), rs.getDouble("poid_disponible"), rs.getString("temp_estime"));
                arr.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;

    }

    @Override
    public List<Livraison> RechercherLivraison(String dep, String des) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Livraison> arr = new ArrayList<>();

        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery("select * from livraison WHERE depart like '" + dep + "%' and destination like '" + des + "%' ");
            while (rs.next()) {
                Livraison l = new Livraison(rs.getInt("id"), rs.getString("depart"), rs.getString("destination"), rs.getDouble("poid_disponible"), rs.getString("temp_estime"));
                arr.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;

    }

    @Override
    public List<Livraison> RechercherLivraisonId(int id) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Livraison> arr = new ArrayList<>();

        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery("select * from livraison WHERE id=" + id);
            while (rs.next()) {
                Livraison l = new Livraison(rs.getInt("id"), rs.getString("depart"), rs.getString("destination"), rs.getDouble("poid_disponible"), rs.getString("temp_estime"));
                arr.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;

    }

    public String getDistance(String depart, String Destination) {
        String info = "";
        try {
            String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins=" + depart + "&destinations=" + Destination + "&key=AIzaSyB0rYRKulJT231JzMwF77DBbZ6a9Jkla8M";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");
            //add request header
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //print in String
            System.out.println(response.toString());
            JSONObject jo = new JSONObject(response.toString());
            // System.out.println(jo.getJSONArray("destination_addresses").get(0));
            System.out.println(jo.getJSONArray("destination_addresses").get(0));
            System.out.println(jo.getJSONArray("origin_addresses").get(0));
            System.out.println(jo.getJSONArray("rows").get(0));

            JSONObject jo2;
            jo2 = (JSONObject) jo.getJSONArray("rows").get(0);
            System.out.println(jo2.getJSONArray("elements").get(0));

            JSONObject jo3;
            jo3 = (JSONObject) jo2.getJSONArray("elements").get(0);
            System.out.println(jo3.getJSONObject("duration").getString("text"));
            System.out.println(jo3.getJSONObject("distance").getString("text"));
            info = "Depart : "+jo.getJSONArray("origin_addresses").get(0)+" || Destination : " + jo.getJSONArray("destination_addresses").get(0) + " || Durée : "+jo3.getJSONObject("duration").getString("text")+" || Distance : "+jo3.getJSONObject("distance").getString("text");
                    
            /* JSONObject jo4;
            jo4 = (JSONObject) jo3.getJSONArray("distance").get(0);
            System.out.println(jo3.getJSONArray("distance").get(0));*/
            //   System.out.println(jo.getJSONArray("rows").getJSONArray(0).getJSONArray(1).get(0));
            //   System.out.println(jo.get("status"));
            //Read JSON response and print
            
            JSONObject myResponse = new JSONObject(response.toString());
            JSONArray jArr = (JSONArray) myResponse.getJSONArray("destination_addresses");
            for (int i = 1; i < jArr.length(); i++) {
                JSONObject innerObj = jArr.getJSONObject(i);
                for (Iterator it = innerObj.keys(); it.hasNext();) {
                    String key = (String) it.next();
                    //  System.out.println(key + ":" + innerObj.get(key));
                }
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return info;
    }

}