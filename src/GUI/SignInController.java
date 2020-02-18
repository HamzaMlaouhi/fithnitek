/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Personne;
import Services.UtilisateurService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import Entities.Utilisateur;
import Entities.UtilisateurSession;

/**
 * FXML Controller class
 *
 * @author Hamza Mlaouhi
 */
public class SignInController implements Initializable {

    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtCIN;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private ComboBox<?> ZoneSexe;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtConfPassword;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnSaha;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void signUp(ActionEvent event) {
        UtilisateurService us = new UtilisateurService();
        Personne P = new Personne();
        Utilisateur user = new Utilisateur();
        user.setUsername(txtUsername.getText());
        user.setEmail(txtEmail.getText());
        user.setPassword(txtPassword.getText());
        
       
        P.setPrenom(txtLastName.getText());
        P.setNom(txtName.getText());
        int jml = Integer.parseInt(txtPhoneNumber.getText());
        P.setNum_tel(jml);
        int cci = Integer.parseInt(txtCIN.getText());
        P.setCin(cci);


        us.Sign_up(user, P);
        
        
    }

    @FXML
    private void Test(ActionEvent event) {
        
    }
    
    
}
