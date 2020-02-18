/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.UtilisateurSession;
import Services.UtilisateurService;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hamza Mlaouhi
 */
public class LogInController implements Initializable {

    
    @FXML
    private Button btnLogIn;
    @FXML
    private PasswordField txtPassWord;
    @FXML
    private TextField txtLogIn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginAction(ActionEvent event) {
        UtilisateurService us = new UtilisateurService();
        us.Log_in(txtLogIn.getText() ,txtPassWord.getText());
        try{
     Parent Sview = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
            Scene SC = new Scene(Sview);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(SC);
            window.show();
        }catch(IOException ex){
            
        }
    }
    
}
