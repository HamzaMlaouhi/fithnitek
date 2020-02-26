/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.scene.control.TextField;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author Hamza Mlaouhi
 */
public class EmailInterfaceController implements Initializable {

    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnSendCode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void SendAction(ActionEvent event) throws MessagingException, IOException {
        UtilisateurService US = new UtilisateurService();
        UtilisateurService.sendMail(txtEmail.getText(), "Password", "Your Code Is " + US.codelogin());
       
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("CodeEmailInterface.fxml"));

        Parent root = loader.load();
        CodeEmailInterfaceController apc = loader.getController();
        btnSendCode.getScene().setRoot(root);
    }

}
