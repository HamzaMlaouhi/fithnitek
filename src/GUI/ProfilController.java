/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Personne;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Hamza Mlaouhi
 */
public class ProfilController implements Initializable {

    @FXML
    private Label txtEmail;
    @FXML
    private Label txtFull_name;
    @FXML
    private Label txtTelNum;
    @FXML
    private Button btnSetting;
    @FXML
    private ImageView ProfImage;
    @FXML
    private Button btnLogOut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtFull_name.setText(Personne.user.getNom() + " " + Personne.user.getPrenom());
        txtEmail.setText(Personne.user.getEmail());
        txtTelNum.setText(Integer.toString(Personne.user.getNum_tel()));
        
        Image image = new Image("/Images/avatardefault_92824.png");
        ProfImage.setImage(image);
    }

    @FXML
    private void SettingAction(ActionEvent event) throws IOException {
    
    FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("SettingInterface.fxml"));

        Parent root = loader.load();
        SettingInterfaceController apc = loader.getController();
        btnSetting.getScene().setRoot(root);
    }

    @FXML
    private void LogOutAction(ActionEvent event) throws IOException  {
         FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("LogIn.fxml"));

        Parent root = loader.load();
        LogInController apc = loader.getController();
        btnLogOut.getScene().setRoot(root);
    }
}
