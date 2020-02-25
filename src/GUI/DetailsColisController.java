/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Colis;
import Entities.Element;
import Services.ColisService;
import Services.CustomerService;
import Services.ElementService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Wassim
 */
public class DetailsColisController implements Initializable {

    private TableColumn<Element, String> name;
    private TableColumn<Element, Integer> quantity;
    private TableColumn<Element, Double> price;
    private TableColumn<Element, Float> weight;
    private TableView<Element> colisDetails;
    
    ObservableList<Element> obserList = FXCollections.observableArrayList();
    List<Element> listElement;
    public int idColis;
    @FXML
    private Label colisIDLAbel;
    @FXML
    private ImageView imageColis;
    @FXML
    private Label txtnomcolis;
    @FXML
    private Label txtDescription;
    @FXML
    private Label txtReward;
    @FXML
    private Button btnAccept;
    @FXML
    private Button btnRefuse;
    
    Colis c;
 
    
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
        ColisService cs = new ColisService();
        c = cs.getColisNotification();
        Image image = new Image("Images/"+c.getImage());
        imageColis.setImage(image);
        txtnomcolis.setText(c.getLabel());
        txtDescription.setText(c.getDescription());
        txtReward.setText(""+c.getReward());
        
        
        
    }

    public void setC(Colis c) {
        this.c = c;
    }

  

    @FXML
    private void acceptDemand(ActionEvent event) {
        CustomerService cs = new CustomerService();
        cs.checkoutPayment("usd", c);
        System.out.println("Payed");
    }

    @FXML
    private void refuseAction(ActionEvent event) {
    }

    
    
    
    
}

