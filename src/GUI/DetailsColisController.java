/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Element;
import Services.ElementService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Wassim
 */
public class DetailsColisController implements Initializable {

    @FXML
    private TableColumn<Element, String> name;
    @FXML
    private TableColumn<Element, Integer> quantity;
    @FXML
    private TableColumn<Element, Double> price;
    @FXML
    private TableColumn<Element, Float> weight;
    @FXML
    private TableView<Element> colisDetails;
    
    ObservableList<Element> obserList = FXCollections.observableArrayList();
    List<Element> listElement;
    public int idColis;
 
    
     public void load(int id){
        ElementService es = new ElementService();
                listElement = es.AfficherListElementColis(id);
                for(Element e : listElement){
                    Element element = new Element();
                    element.setName(e.getName());
                    element.setQuantite(e.getQuantite());
                    element.setPrix(e.getPrix());
                    element.setPoid(e.getPoid());
                    obserList.add(e);
    }    
                name.setCellValueFactory(new PropertyValueFactory<>("Name"));
                quantity.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
                price.setCellValueFactory(new PropertyValueFactory<>("Prix"));
                weight.setCellValueFactory(new PropertyValueFactory<>("Poid"));
                
                colisDetails.setItems(obserList);

   
    
}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //load();
    }

    public void setIdColis(int idColis) {
        this.idColis = idColis;
    }

    
    
    
    
}

