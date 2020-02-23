/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Utilisateur;
import Services.UtilisateurService;
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
        Utilisateur U = new Utilisateur(3, "Mlaouhi", "MLaouhi@gmail.com", "tw aandy");
        UtilisateurService US = new UtilisateurService();
       
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
        }
    
  
