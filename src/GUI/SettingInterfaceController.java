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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.util.Pair;

/**
 * FXML Controller class
 *
 * @author Hamza Mlaouhi
 */
public class SettingInterfaceController implements Initializable {

    @FXML
    private TextField txtLaNa;
    @FXML
    private TextField txtNa;
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtMail;
    @FXML
    private TextField txtNumb;
    @FXML
    private Button btnBackToProfil;
    @FXML
    private Button btnModify;
    @FXML
    private Button btnChangePass;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnGoToPayment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtMail.setText(Personne.user.getEmail());
        txtLaNa.setText(Personne.user.getPrenom());
        txtNa.setText(Personne.user.getNom());
        txtUser.setText(Personne.user.getUsername());
        txtNumb.setText(Integer.toString(Personne.user.getNum_tel()));
    }

    @FXML
    private void BackToProfilAction(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("Profil.fxml"));

        Parent root = loader.load();
        ProfilController apc = loader.getController();
        btnBackToProfil.getScene().setRoot(root);
    }

    @FXML
    private void ModifyAction(ActionEvent event) {

        UtilisateurService Us = new UtilisateurService();
        Personne Per = new Personne();
        Utilisateur User = new Utilisateur();
        Per.setPrenom(txtLaNa.getText());
        Per.setNom(txtNa.getText());
        User.setUsername(txtUser.getText());
        User.setEmail(txtMail.getText());
        int nu = Integer.parseInt(txtNumb.getText());
        Per.setNum_tel(nu);
        Us.ModifierUtilisateur(User, Per);
    }

    @FXML
    private void ChangePassAction(ActionEvent event) throws IOException {
    
    }

    @FXML
    private void DeleteAction(ActionEvent event) throws IOException {
        UtilisateurService Us = new UtilisateurService();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Delete Account");
        a.setContentText("Are you sure you want to delete this account ?");
        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.OK) {
            Us.SupprimerUtilisateur();

            Us.SupprimerUtilisateur();
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("LogIn.fxml"));

            Parent root = loader.load();
            LogInController apc = loader.getController();
            btnDelete.getScene().setRoot(root);

        }
    }

    @FXML
    private void GoToPaymentDetails(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("Payment.fxml"));

            Parent root = loader.load();
            PaymentController apc = loader.getController();
            btnDelete.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(SettingInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
