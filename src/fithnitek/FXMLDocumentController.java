/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek;

import Entities.Livraison;
import Services.LivraisonService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author asus
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");

        label.setText("Hello World!");

        Livraison l = new Livraison(1, "tunis", "saklabet", 10.3, "2020-2-14", 0);
        LivraisonService LS = new LivraisonService();
        LS.SupprimerLivraison(l);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
        }
    
        
    


