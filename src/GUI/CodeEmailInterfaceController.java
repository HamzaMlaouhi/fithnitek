/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Personne;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Hamza Mlaouhi
 */
public class CodeEmailInterfaceController implements Initializable {

    @FXML
    private TextField txtCode;
    @FXML
    private Button btnVerify;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void VerifyAction(ActionEvent event) throws IOException {
    if(Integer.parseInt(txtCode.getText())==Personne.Code){
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("ChangePassInterface.fxml"));

        Parent root = loader.load();
        ChangePassInterfaceController apc = loader.getController();
        btnVerify.getScene().setRoot(root);
    }
    }
    
}
