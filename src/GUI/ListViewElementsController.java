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
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

/**
 * FXML Controller class
 *
 * @author Wassim
 */
public class ListViewElementsController implements Initializable {

    @FXML
    private ListView<Element> listViewElements;
    @FXML
    private Button btnSetSelection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ElementService es = new ElementService();
        List<Element> listElements = es.AfficherElement();
        for(Element e : listElements){
            listViewElements.getItems().add(e);
        }
        listViewElements.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }    

    @FXML
    private void populateListElements(ActionEvent event) {
        
        ObservableList listofItems = listViewElements.getSelectionModel().getSelectedItems();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("int_ajout_colis.fxml"));
                Parent root = loader.load();
                Int_ajout_colisController lcc = loader.getController();
                lcc.setElemets(listofItems);
            } catch (IOException ex) {
                Logger.getLogger(ListViewElementsController.class.getName()).log(Level.SEVERE, null, ex);
            }

            
        
    }
    
}
