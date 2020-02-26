/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Element;
import Services.ElementService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Wassim
 */
public class AllItemsController implements Initializable {

    @FXML
    private TableView<Element> taballitems;
    @FXML
    private TableColumn<Element, String> nom;
    @FXML
    private TableColumn<Element, Integer> quantite;
    @FXML
    private TableColumn<Element, Double> prix;
    @FXML
    private TableColumn<Element, Float> poid;

    List<Element> listElement ;
    ObservableList<Element> obserList = FXCollections.observableArrayList();
    @FXML
    private Button backtopack;
    /**
     * Initializes the controller class.
     */
    
   
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
public void affiche(){
    ElementService es = new ElementService();
     listElement = es.AfficherElement();
     for(Element e : listElement){
         Element element = new Element();
         element.setName(e.getName());
         element.setQuantite(e.getQuantite());
         element.setPrix(e.getPrix());
         element.setPoid(e.getPoid());
         obserList.add(e);
     }
        nom.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        poid.setCellValueFactory(new PropertyValueFactory<>("poid"));
        taballitems.setItems(obserList);
}

    @FXML
    private void backToColis(ActionEvent event) {
         try {
            Parent addColis = FXMLLoader.load(getClass().getResource("int_ajout_colis.fxml"));
            Scene scene2 = new Scene(addColis);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            //Stage window = new Stage();
            window.setTitle("Ajout Colis");
            window.setScene(scene2);
            window.show();

        } catch (IOException ex) {
        }
    }
}
