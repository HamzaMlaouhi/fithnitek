/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Livraison;
import Services.LivraisonService;
import com.sun.java.swing.plaf.windows.resources.windows;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class ModifLivController implements Initializable {

    @FXML
    private TextField tDepart;
    @FXML
    private TextField tDest;
    @FXML
    private TextField tPoids;
    @FXML
    private TextField tTemps;

    /**
     * Initializes the controller class.
     */
    Livraison l =new Livraison();
    @FXML
    private Button bMod;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void transferMessage(Livraison liv) {
        //Display the message
        System.out.println(liv.toString());
        l.setId(liv.getId());
        tDepart.setText(liv.getDepart());
        tDest.setText(liv.getDestination());
        tPoids.setText(liv.getPoid_disponible() + "");
        tTemps.setText(liv.getTemp_estime());
    }

    @FXML
    private void updateLivraison(ActionEvent event) {
        LivraisonService cs = new LivraisonService();
        l.setDestination(tDest.getText());
        l.setDepart(tDepart.getText());
        l.setPoid_disponible(Double.parseDouble(tPoids.getText()));
        l.setTemp_estime(tTemps.getText());
        cs.ModifierLivraison(l);
        try {
            Parent view2 = FXMLLoader.load(getClass().getResource("List.fxml"));
            Scene scene2 = new Scene(view2);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            //Stage window = new Stage();
            window.setTitle("list");
            window.setScene(scene2);
            window.show();

        } catch (IOException ex) {
        }

    }

}
