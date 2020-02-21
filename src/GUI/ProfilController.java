/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Personne;
import Entities.Utilisateur;
import Services.UtilisateurService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Hamza Mlaouhi
 */
public class ProfilController implements Initializable {

    @FXML
    private Label txtlast_name;
    @FXML
    private Label txtEmail;
    @FXML
    private Label txtbirthday;
    @FXML
    private Button btnModify;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     txtlast_name.setText(Personne.user.getEmail());
       
    }    

    @FXML
    private void ModifyAction(ActionEvent event) {
    }
    
}
