/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Element;
import Services.ElementService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.scene.control.TextField;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Wassim
 */
public class Int_ajout_elmtController implements Initializable {
    
   public  List<Element> listIntermediaire = new ArrayList<>();
    ElementService es = new ElementService() ;

    @FXML
    private Button btnfilechooser;
    @FXML
    private TextField nomelmt;
    @FXML
    private TextField quantite;
    @FXML
    private TextField prix;
    @FXML
    private TextField poid;
    @FXML
    private Label namelab;
    @FXML
    private Label quantlab;
    @FXML
    private Label pricelab;
    @FXML
    private Label poidlab;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnadd1;
    @FXML
    private Label titlab;

    ElementService elmts = new ElementService();
    @FXML
   public void elmt(ActionEvent e){
       
       
//       try {
//           String elementNam="";
//           FXMLLoader elmtV = new FXMLLoader(getClass().getResource("int_ajout_colis.fxml"));
//           Parent root = elmtV.load();
//           Int_ajout_colisController colisC = elmtV.getController();
//           Element elmt = new Element() ;
//           elmt.setName(nomelmt.getText());
//           elmt.setQuantite(Integer.parseInt(quantite.getText()));
//           elmt.setPrix(Double.parseDouble(prix.getText()));
//           elmt.setPoid(Float.parseFloat(poid.getText()) );
//           listIntermediaire.add(elmt);
//          for(Element element : listIntermediaire){
//              elementNam+=","+element.getName();
//             colisC.setItemsLabel(element.getName());
//
//          }
//           namelab.getScene().setRoot(root);
//          
//       } catch (IOException ex) {
//           Logger.getLogger(Int_ajout_elmtController.class.getName()).log(Level.SEVERE, null, ex);
//       }
       
    }
   
    @FXML
   public void backToColis (ActionEvent e){
       try{ 
            Parent addColis = FXMLLoader.load(getClass().getResource("int_ajout_colis.fxml"));
            Scene scene2 =new Scene(addColis);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
           //Stage window = new Stage();
            window.setTitle("Ajout Colis");
            window.setScene(scene2);
            window.show();
            
        }
        catch(IOException ex){    
        }
   }
   public void ajoutElement(ActionEvent e){
       Element elmt = new Element();
       elmt.setName(nomelmt.getText());
       elmt.setQuantite(Integer.parseInt(quantite.getText()));
       elmt.setPrix(Double.parseDouble(prix.getText()));
       elmt.setPoid(Float.parseFloat(poid.getText()));
       elmts.AjouterElemnet(elmt);
       
   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void selectImage(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(null);    
    }

    public List<Element> getListIntermediaire() {
        return listIntermediaire;
   }
    
    
    
}
