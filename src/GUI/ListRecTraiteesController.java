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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ListRecTraiteesController implements Initializable {

    @FXML
    private TableView<Reclamation> ListRec;
    @FXML
    private TableColumn<Reclamation, String> nom;
    @FXML
    private TableColumn<Reclamation, String> typereclamation;
    @FXML
    private TableColumn<Reclamation, String> message;
    @FXML
    private Button btnsupp;
    @FXML
    private TextField search;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnListe;
    @FXML
    private Button btnstat;
    
    ObservableList<Entities.Reclamation> data;
    ReclamationService rs = new ReclamationService();
    Entities.Reclamation r = new Entities.Reclamation();
    List<Reclamation> ls;
    @FXML
    private Button btnlistetraitees;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        load();
    }    
    
    public void load (){ 
        ListRec.setVisible(true);
        ls = rs.ListerReclamation();
        data = FXCollections.observableArrayList();
        if(!ls.isEmpty())
        ls.stream().forEach((j) -> {
                data.add(new Reclamation(j.getId(),j.getNom(),j.getTypereclamation(),j.getMessage(),j.getIdutilisateur(),j.getEtat()));
                ListRec.setItems(data);
        });
      
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typereclamation.setCellValueFactory(new PropertyValueFactory<>("typereclamation"));
        message.setCellValueFactory(new PropertyValueFactory<>("message"));
       
    }
    
    @FXML
    private void SupprimerReclamation(ActionEvent event) {
        Reclamation r = ListRec.getSelectionModel().getSelectedItem();
        rs.supprimerReclamation(r.getId());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            data.clear();
            ls.clear();
            load();
        } else {
            System.out.println("yessss");
        }
    }

    @FXML
    private void find(ActionEvent event) {
        ObservableList table = ListRec.getItems();
        
        search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
	            if (oldValue != null && (newValue.length() < oldValue.length())) {
	                ListRec.setItems(table);
	            }
	            String value = newValue.toLowerCase();
	            ObservableList<Entities.Reclamation> subentries = FXCollections.observableArrayList();

	            long count = ListRec.getColumns().stream().count();
	            for (int i = 0; i < ListRec.getItems().size(); i++) {
	                for (int j = 0; j < count; j++) {
	                    String entry = "" + ListRec.getColumns().get(j).getCellData(i);
	                    //if (entry.toLowerCase().equals(value)
                                    
                            if (entry.toLowerCase().contains(CharSequence.class.cast(value))) 
                            {
	                        subentries.add(ListRec.getItems().get(i));
	                        break;
                            }
	                }
	            }
	            ListRec.setItems(subentries);
	        });
    load();
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
        primaryStage.setTitle("Afficher Reclamation !");
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
