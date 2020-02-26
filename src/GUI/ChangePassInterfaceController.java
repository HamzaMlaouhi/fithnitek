/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Utilisateur;
import Services.UtilisateurService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

/**
 * FXML Controller class
 *
 * @author Hamza Mlaouhi
 */
public class ChangePassInterfaceController implements Initializable {

    @FXML
    private Button btnChangePassword;
    @FXML
    private PasswordField txtPassV;
    @FXML
    private PasswordField txtPassConfV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ChangeAction(ActionEvent event) throws IOException  {
   
//        if(txtPassConfV.getText().equals(txtPassV.getText())){
        UtilisateurService US = new UtilisateurService();
        Utilisateur u = new Utilisateur();
        u.setPassword(txtPassV.getText());
        US.ModifierPass(u);

        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("LogIn.fxml"));

        Parent root = loader.load();
        LogInController apc = loader.getController();
        btnChangePassword.getScene().setRoot(root);
   }
    }


