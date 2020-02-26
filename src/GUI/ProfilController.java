/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Colis;
import Entities.Personne;
import Services.ColisService;
import Services.UtilisateurService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

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
    @FXML
    private Button livBtn;
    @FXML
    private Button btnMyPackages;
    @FXML
    private Button btnMyItems;
    @FXML
    private Button btnMyTrips;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        UtilisateurService us = new UtilisateurService();
        ColisService cs = new ColisService();
        if (us.checkNotification()) {

            Notifications notificationBuilder = Notifications.create()
                    .title("You've got a new notification")
                    .text("Someone Wants your service")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_LEFT)
                    .onAction((event) -> {
                try {
                    
                    Colis c = cs.getColisNotification();
                    FXMLLoader loader = new FXMLLoader(getClass()
                            .getResource("detailsColis.fxml"));
                    
                    Parent root = loader.load();
                    DetailsColisController dcc = loader.getController();
                    dcc.setC(c);
                    livBtn.getScene().setRoot(root);
                   // us.deleteNotification();
                } catch (IOException ex) {
                    Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    });
            notificationBuilder.showConfirm();
        }

        txtFull_name.setText(Personne.user.getNom() + " " + Personne.user.getPrenom());
        txtEmail.setText(Personne.user.getEmail());
        txtTelNum.setText(Integer.toString(Personne.user.getNum_tel()));

        String imageName = us.getImageName();
        try {
            Image image = new Image("/Images/Profil/" + imageName);
            ProfImage.setImage(image);
        } catch (Exception ex) {
            ex.getMessage();
        }

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
    private void LogOutAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("LogIn.fxml"));

        Parent root = loader.load();
        LogInController apc = loader.getController();
        btnLogOut.getScene().setRoot(root);
    }

    private void GoToLivraisonManagment(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("HomePage.fxml"));

            Parent root = loader.load();
            HomePageController apc = loader.getController();
            btnLogOut.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToLivraison(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("HomePage.fxml"));

            Parent root = loader.load();
            HomePageController apc = loader.getController();
            btnLogOut.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void goToMyPackages(ActionEvent event) {            
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("Liste_colis.fxml"));

            Parent root = loader.load();
            Liste_colisController apc = loader.getController();
            btnLogOut.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void goToMyItems(ActionEvent event) {
    /*try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("HomePage.fxml"));

            Parent root = loader.load();
            HomePageController apc = loader.getController();
            btnLogOut.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    @FXML
    private void goToMyTrips(ActionEvent event) {
    try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("FXML.fxml"));

            Parent root = loader.load();
            FXMLController apc = loader.getController();
            btnLogOut.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
