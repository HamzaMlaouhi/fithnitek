/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Reclamation;
import Services.ReclamationService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ReclamationUIController implements Initializable {

    @FXML
    private TextArea txtMsgRec;
    @FXML
    private ComboBox<String> cbTypeRec;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnListe;

    ObservableList<String> listetype = FXCollections.observableArrayList("message1","message2","message3","message4");
    @FXML
    private Button btnstat;
    @FXML
    private Button btnEnv;
    @FXML
    private Button btnAnnuler;
    @FXML
    private TextField nom;
    @FXML
    private Button btnlistetraitees;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbTypeRec.setItems(listetype);
    }    

    @FXML
    private void envoyerReclamation(ActionEvent event) {
        if(!"".equals(txtMsgRec.getText())){
            ReclamationService sp = new ReclamationService();
            Reclamation r=new Reclamation();
            r.setNom(nom.getText());
            r.setTypereclamation(cbTypeRec.getValue());
            r.setMessage(txtMsgRec.getText());
            r.setIdutilisateur(1);
            r.setEtat(0);
            sp.ajouterReclamation(r);
            
            nom.clear();
            txtMsgRec.clear();
        }else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Please Fill the Fields");
            alert.setContentText("*You Have Missed to fill some Fields");
            alert.showAndWait();

        }
    }

    @FXML
    private void AnnulerReclamation(ActionEvent event) {
        nom.clear();
        txtMsgRec.clear();
    }

    @FXML
    private void AjouterReclamation(ActionEvent event)throws IOException {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
    private void ListerReclamationTratiees(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ListRecTraitees.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Stat Reclamation !");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
}
