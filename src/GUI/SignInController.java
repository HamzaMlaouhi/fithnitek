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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

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
    private ComboBox<String> ZoneSexe;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtConfPassword;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnBack;
    @FXML
    private Label FailUsername;
    @FXML
    private Label FailEmail;
    @FXML
    private Label FailName;
    @FXML
    private Label FailPassword;
    @FXML
    private Label FailConPassword;
    @FXML
    private Label FailCIN;
    @FXML
    private Label FailPhoneNum;
    @FXML
    private ImageView ImageFi;
    @FXML
    private ImageView imageview;

    String fileName;
    Image image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        image = new Image("/Images/fi-thnitek.png");
        ImageFi.setImage(image);

        imageview.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("Tile pressed ");
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            File file = fileChooser.showOpenDialog(null);
            String path = "file:\\" + file.getPath();
            fileName = file.getName();
            if (file != null) {
                image = new Image(path);
                imageview.setImage(image);
                BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
                File outputfile = new File("C:\\Users\\yassine bayoudh\\Desktop\\Projets PI\\fithnitek\\src\\Images\\Profil\\" + file.getName());
                try {
                    ImageIO.write(bImage, "jpg", outputfile);
                } catch (IOException ex) {
                    Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            event.consume();
        });
        ObservableList<String> options
                = FXCollections.observableArrayList(
                        
                );
        
        ZoneSexe.getItems().addAll(
        "Male",
        "Female"
        
);

    }

    @FXML
    private void signUp(ActionEvent event) {
        UtilisateurService us = new UtilisateurService();
        Personne P = new Personne();

        if ("".equals(txtLastName.getText()) && "".equals(txtName.getText())) {
            FailName.setText("X");
        }
        if ("".equals(txtUsername.getText())) {
            FailUsername.setText("X");
        }
        if (!(Pattern.matches("^[a-zA-Z0-9]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$", txtEmail.getText()))) {
            FailEmail.setText("X");
        }
        if (txtPassword.getText() == null ? txtConfPassword.getText() != null : !txtPassword.getText().equals(txtConfPassword.getText())) {
            FailPassword.setText("X");
            FailConPassword.setText("X");
        }
        if ("".equals(txtCIN.getText()) || (txtCIN.getText().length() != 8)) {
            FailCIN.setText("X");
        }
        if ("".equals(txtPhoneNumber.getText()) || (txtPhoneNumber.getText().length() != 8)) {
            FailPhoneNum.setText("X");
        } else {

            Utilisateur user = new Utilisateur();
            user.setUsername(txtUsername.getText());
            user.setEmail(txtEmail.getText());
            user.setPassword(txtPassword.getText());

            P.setImage(fileName);
            P.setPrenom(txtLastName.getText());
            P.setNom(txtName.getText());
            P.setSexe(ZoneSexe.getSelectionModel().getSelectedItem());
            int jml = Integer.parseInt(txtPhoneNumber.getText());
            P.setNum_tel(jml);
            int cci = Integer.parseInt(txtCIN.getText());
            P.setCin(cci);

            us.Sign_up(user, P);
        }
    }

    @FXML
    private void BackToLogIn(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("LogIn.fxml"));

        Parent root = loader.load();
        LogInController apc = loader.getController();
        btnBack.getScene().setRoot(root);
    }

}
