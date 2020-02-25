/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Livraison;
import Services.LivraisonService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class FXMLController implements Initializable {

    @FXML
    private TextField txtDepart;
    @FXML
    private Button btnAjoutVoyage;
    @FXML
    private TextField txtDest;
    @FXML
    private TextField txtPoids;
    @FXML
    private TextField txtTempsDepart;
    @FXML
    private AnchorPane formVoyage;
    
    LivraisonService ls = new LivraisonService();
    @FXML
    private DatePicker date;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtPoids.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtPoids.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }    

    @FXML
    private void ajouterVoyage(ActionEvent event) {
        
       
        Livraison l = new Livraison();
        l.setDepart(txtDepart.getText());
        l.setDestination(txtDest.getText());
        l.setPoid_disponible(Float.parseFloat(txtPoids.getText()));
        l.setTemp_estime(date.getValue().toString());
        
        ls.AjouterLivraison(l);
        try{ 
            Parent view2 = FXMLLoader.load(getClass().getResource("ListAll.fxml"));
            Scene scene2 =new Scene(view2);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            //Stage window = new Stage();
            window.setTitle("listAll");
            window.setScene(scene2);
            window.show();
            
        }
        catch(IOException ex){    
        }
        
    }

    @FXML
    private void VerifierNumber(KeyEvent event) {
    }
    
}
