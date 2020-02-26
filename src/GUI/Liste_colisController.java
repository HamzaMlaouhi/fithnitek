/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Colis;
import Entities.Element;
import Services.ColisService;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Wassim
 */
public class Liste_colisController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<Colis> listcolis;
    @FXML
    private TableColumn<Colis, String> from;
    @FXML
    private TableColumn<Colis, String> to;
    @FXML
    private TableColumn<Colis, String> date;
    @FXML
    private TableColumn<Colis, String> name;
    @FXML
    private TableColumn<Colis, String> note;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;

    List<Colis> listColis;
    @FXML
    private Button btnSeeItems;
    ObservableList<Colis> obserList = FXCollections.observableArrayList();

    @FXML
    public void changeDepart(CellEditEvent editedCell) {
        Colis colisSelected = listcolis.getSelectionModel().getSelectedItem();
        colisSelected.setDepart(editedCell.getNewValue().toString());
    }

    @FXML
    public void changeDestination(CellEditEvent editedCell) {
        Colis colisSelected = listcolis.getSelectionModel().getSelectedItem();
        colisSelected.setDestination(editedCell.getNewValue().toString());
    }

    @FXML
    public void changeDate(CellEditEvent editedCell) {
        Colis colisSelected = listcolis.getSelectionModel().getSelectedItem();
        colisSelected.setDate_limit(editedCell.getNewValue().toString());
    }

    @FXML
    public void changeLabel(CellEditEvent editedCell) {
        Colis colisSelected = listcolis.getSelectionModel().getSelectedItem();
        colisSelected.setLabel(editedCell.getNewValue().toString());
    }

    @FXML
    public void changeNote(CellEditEvent editedCell) {
        Colis colisSelected = listcolis.getSelectionModel().getSelectedItem();
        colisSelected.setDescription(editedCell.getNewValue().toString());
    }

    @FXML
    public void backToColis(ActionEvent e) {
        try {
            Parent addColis = FXMLLoader.load(getClass().getResource("int_ajout_colis.fxml"));
            Scene scene2 = new Scene(addColis);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            //Stage window = new Stage();
            window.setTitle("Ajout Colis");
            window.setScene(scene2);
            window.show();

        } catch (IOException ex) {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();

    }

    public void load() {
        ColisService cs = new ColisService();
        listColis = cs.AfficherColis();
        for (Colis c : listColis) {
            Colis colis = new Colis();
            colis.setDepart(c.getDepart());
            colis.setDestination(c.getDestination());
            colis.setDate_limit(c.getDate_limit());
            colis.setLabel(c.getLabel());
            colis.setDescription(c.getDescription());
            obserList.add(c);
        }
        from.setCellValueFactory(new PropertyValueFactory<>("depart"));
        to.setCellValueFactory(new PropertyValueFactory<>("destination"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_limit"));
        name.setCellValueFactory(new PropertyValueFactory<>("label"));
        note.setCellValueFactory(new PropertyValueFactory<>("description"));

        listcolis.setItems(obserList);

        listcolis.setEditable(true);

        from.setCellFactory(TextFieldTableCell.forTableColumn());
        to.setCellFactory(TextFieldTableCell.forTableColumn());
        date.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setCellFactory(TextFieldTableCell.forTableColumn());

        note.setCellFactory(TextFieldTableCell.forTableColumn());
        System.out.println(obserList);
    }

    @FXML
    private void updateColis(ActionEvent event) {
        Colis colis = listcolis.getSelectionModel().getSelectedItem();
        ColisService cs = new ColisService();
        cs.ModifierColis(colis);
    }

    @FXML
    private void DeleteColis(ActionEvent event) {
        Colis colis = listcolis.getSelectionModel().getSelectedItem();
        ColisService cs = new ColisService();

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation popup");
        alert.setHeaderText("Confirmation alert");
        alert.setContentText("confirme deleting");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            cs.SupprimerColis(colis);
            obserList.clear();
            listColis.clear();
            load();
        } else {
            System.out.println("Done");
        }
    }

    @FXML
    private void seeItems(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("detailsColis.fxml"));
        Parent root = loader.load();
        DetailsColisController dcc = loader.load();
        dcc.load(listcolis.getSelectionModel().getSelectedItem().getId());
        Scene scene2 = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //Stage window = new Stage();
        window.setTitle("Detail Colis");
        window.setScene(scene2);
        window.show();

    }

}
