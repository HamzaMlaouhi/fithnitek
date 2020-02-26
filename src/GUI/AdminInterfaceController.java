/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Hamza Mlaouhi
 */
public class AdminInterfaceController implements Initializable {

    @FXML
    private Button btnRec;
    @FXML
    private Button btnPac;
    @FXML
    private Button btnTra;
    @FXML
    private Button btnGro;
    @FXML
    private Button btnGro1;
    @FXML
    private Button btnAllItems;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void logOutAction(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("LogIn.fxml"));

        Parent root = loader.load();
        LogInController apc = loader.getController();
        btnGro1.getScene().setRoot(root);
    }

    @FXML
    private void GotToReclamation(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("AfficherReclamation.fxml"));

        Parent root = loader.load();
        AfficherReclamationController apc = loader.getController();
        btnRec.getScene().setRoot(root);
    }

    @FXML
    private void GotToPackage(ActionEvent event) throws IOException {
     FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("List_colis.fxml"));

        Parent root = loader.load();
        Liste_colisController apc = loader.getController();
        btnPac.getScene().setRoot(root);
    }

    @FXML
    private void GotToTransaction(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("transactions.fxml"));

        Parent root = loader.load();
        TransactionsController apc = loader.getController();
        btnTra.getScene().setRoot(root);
    }

    @FXML
    private void GotToGroup(ActionEvent event) {
//    FXMLLoader loader = new FXMLLoader(getClass()
//                .getResource("Item.fxml"));
//
//        Parent root = loader.load();
//        Liste_colisController apc = loader.getController();
//        btnPac.getScene().setRoot(root);
    }

    @FXML
    private void GotoItem(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("AllItem.fxml"));

        Parent root = loader.load();
        AllItemsController apc = loader.getController();
        btnTra.getScene().setRoot(root);
    }
    
}
