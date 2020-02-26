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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    private Button btnSignIn;
    @FXML
    private ImageView LogoViewID;
    @FXML
    private Hyperlink HypForgetPass;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image("/Images/fi-thnitek.png");
        LogoViewID.setImage(image);
 
        }

    @FXML
    private void loginAction(ActionEvent event) throws IOException {
        if ("SuperuseR".equals(txtLogIn.getText()) && ("SuperuseR".equals(txtPassWord.getText()))) {

            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("AdminInterface.fxml"));

            Parent root = loader.load();
            AdminInterfaceController apc = loader.getController();
            btnLogIn.getScene().setRoot(root);
        } else {

            UtilisateurService us = new UtilisateurService();
            boolean success = us.Log_in(txtLogIn.getText(), txtPassWord.getText());
            
            if (success) {

                FXMLLoader loader = new FXMLLoader(getClass()
                        .getResource("Profil.fxml"));

                Parent root = loader.load();
                ProfilController apc = loader.getController();
                btnLogIn.getScene().setRoot(root);

            }else {
                System.out.println("test error");
            }
        }
    }

    @FXML
    private void SignAction(ActionEvent event) {

        try {
            Parent Sview = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
            Scene SC = new Scene(Sview);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(SC);
            window.show();
        } catch (IOException ex) {

        }
    }

    @FXML
    private void ForgetPassAction(ActionEvent event) {

    }

}
