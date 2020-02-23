/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class RepondreReclamationController implements Initializable {

    @FXML
    private TextField mail;
    @FXML
    private TextArea body;
    @FXML
    private TextField subject;
    @FXML
    private Button Send;
    @FXML
    private Button Clear;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnListe;
    @FXML
    private Button btnstat;
    @FXML
    private Button btnMail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Send(ActionEvent event) throws AddressException, MessagingException {
        final String from = "dihek.missaoui@esprit.tn";
        if (!"".equals(mail.getText()) && !"".equals(body.getText()) && !"".equals(subject.getText()) ) {
                    Properties properties = new Properties();
                    properties.put("mail.smtp.starttls.enable", "true");
                    properties.put("mail.smtp.auth", "true");
                    properties.put("mail.smtp.host", "smtp.gmail.com");
                    properties.put("mail.smtp.port", "587");

                    Session session;
                    session = Session.getInstance(properties,
                            new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(from, "12684448");
                        }
                    });

                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(from));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getText()));
                    message.setSubject(subject.getText());
                    message.setText(body.getText());

                    Transport.send(message);
                    mail.clear();
                    subject.clear();
                    body.clear();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Please Fill the Fields");
                    alert.setContentText("*You Have Missed to fill some Fields");
                    alert.showAndWait();

                }
            }
        
    

    @FXML
    private void Clear(ActionEvent event) {
        mail.clear();
        subject.clear();
        body.clear();
    }

    @FXML
    private void AjouterReclamation(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ReclamationUI.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Ajout Reclamation !");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void ListerReclamation(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AfficherReclamation.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Ajout Reclamation !");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void AfficherStatReclamation(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("StatReclamation.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Stat Reclamation !");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void RependreParMail(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("RepondreReclamation.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Stat Reclamation !");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
