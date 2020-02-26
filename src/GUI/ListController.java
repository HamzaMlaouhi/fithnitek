/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Livraison;
import Entities.Personne;
import Services.LivraisonService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static sun.font.FontManagerNativeLibrary.load;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class ListController implements Initializable {

    @FXML
    private TableView<Livraison> TabList;
    @FXML
    private TableColumn<Livraison, String> DepartCol;
    @FXML
    private TableColumn<Livraison, String> DestinationCol;
    @FXML
    private TableColumn<Livraison, Double> PoidCol;
    @FXML
    private TableColumn<Livraison, String> TempsCol;

    ObservableList<Livraison> obserList = FXCollections.observableArrayList();
    @FXML
    private Button btnMod;
    @FXML
    private Button ajoutid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        LivraisonService ls = new LivraisonService();
        List<Livraison> listLivraison = ls.AfficherLivraisonPerson(Personne.user.getId());
        for (Livraison l : listLivraison) {
            Livraison liv = new Livraison();
            liv.setId(l.getId());
            liv.setDepart(l.getDepart());
            liv.setDestination(l.getDestination());
            liv.setPoid_disponible(l.getPoid_disponible());
            liv.setTemp_estime(l.getTemp_estime());
            obserList.add(l);
        }
        DepartCol.setCellValueFactory(new PropertyValueFactory<>("depart"));
        DestinationCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
        PoidCol.setCellValueFactory(new PropertyValueFactory<>("poid_disponible"));
        TempsCol.setCellValueFactory(new PropertyValueFactory<>("temp_estime"));
        TabList.setItems(obserList);

    }

    @FXML
    private void DeleteLivraison(ActionEvent event) {
        Livraison liv = TabList.getSelectionModel().getSelectedItem();
        LivraisonService cs = new LivraisonService();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation popup");
        alert.setHeaderText("Confirmation alert");
        alert.setContentText("confirme deleting");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            cs.SupprimerLivraison(liv);
            obserList.clear();
            //TabList.clear();
            load();
        } else {
            System.out.println("Done");
        }
        LivraisonService ls = new LivraisonService();
        List<Livraison> listLivraison = ls.AfficherLivraisonPerson(9);
        for (Livraison l : listLivraison) {
            liv = new Livraison();
            liv.setDepart(l.getDepart());
            liv.setDestination(l.getDestination());
            liv.setPoid_disponible(l.getPoid_disponible());
            liv.setTemp_estime(l.getTemp_estime());
            obserList.add(l);
        }
        DepartCol.setCellValueFactory(new PropertyValueFactory<>("depart"));
        DestinationCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
        PoidCol.setCellValueFactory(new PropertyValueFactory<>("poid_disponible"));
        TempsCol.setCellValueFactory(new PropertyValueFactory<>("temp_estime"));
        TabList.setItems(obserList);
    }

    @FXML
    private void updateLivraison(ActionEvent event) {
        Livraison liv = TabList.getSelectionModel().getSelectedItem();
        LivraisonService cs = new LivraisonService();
        cs.ModifierLivraison(liv);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifLiv.fxml"));
            Parent root = loader.load();

            //Get controller of scene2
            ModifLivController modController = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            modController.transferMessage(liv);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Modification Window");
            stage.show();

        } catch (IOException ex) {
        }
    }

    @FXML
    private void addLivraison(ActionEvent event) {
        Livraison liv = new Livraison();
        liv.setIdPersonne(Personne.user.getId());
        LivraisonService cs = new LivraisonService();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML.fxml"));
            Parent root = loader.load();

            //Get controller of scene2
            FXMLController lisController = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here

            //Show scene 2 in new window            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Mes Voyages");
            stage.show();

        } catch (IOException ex) {
        }
    }

    /*  public void transferMessage(Livraison liv) {
       
        LivraisonService ls = new LivraisonService();
        List<Livraison> listLivraison = ls.AfficherLivraisonPerson(liv.getIdPersonne());
        for (Livraison l : listLivraison) {
            liv = new Livraison();
            liv.setId(l.getId());
            liv.setDepart(l.getDepart());
            liv.setDestination(l.getDestination());
            liv.setPoid_disponible(l.getPoid_disponible());
            liv.setTemp_estime(l.getTemp_estime());
            obserList.add(l);
        }
        DepartCol.setCellValueFactory(new PropertyValueFactory<>("depart"));
        DestinationCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
        PoidCol.setCellValueFactory(new PropertyValueFactory<>("poid_disponible"));
        TempsCol.setCellValueFactory(new PropertyValueFactory<>("temp_estime"));
        TabList.setItems(obserList);
    }*/
}
