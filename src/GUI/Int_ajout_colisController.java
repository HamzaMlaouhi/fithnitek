/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Colis;
import Entities.Element;
import Services.ColisService;
import Services.ElementService;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Wassim
 */
public class Int_ajout_colisController implements Initializable {

    @FXML
    private TextField from;
    @FXML
    private TextField to;
    @FXML
    private TextField befdate;
    @FXML
    private TextField shipname;
    @FXML
    private TextArea note;
    @FXML
    private Button pub;
    @FXML
    private Label finish;
    @FXML
    private Label limitdate;
    @FXML
    private Label start;
    private Label txtItemsList;
    @FXML
    private Label idshipname;
    
    private List<Element> itemsList;
    ColisService cs = new ColisService() ;
    @FXML
    private Label titlab;
    private Label alert;
    @FXML
    private Button mycolis;
    
       public List<Element> elemets ;
    @FXML
    private Label idshipname1;
    @FXML
    private ListView<Element> myItems;
    @FXML
    private ListView<Element> addedItems;
    @FXML
    private Label titlab1;
    @FXML
    private Button btnMove;
    ObservableList<Element> oList;
    ObservableList<Element> oList2 =FXCollections.observableArrayList();
    @FXML
    private Button btnMove2;
    @FXML
    private ImageView imageViewID;
    
    String fileName;
    @FXML
    private Button btnAddItems;


    public void colis(ActionEvent e){ 
        try{ 
            Parent view2 = FXMLLoader.load(getClass().getResource("int_ajout_elmt.fxml"));
            Scene scene2 =new Scene(view2);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
           //Stage window = new Stage();
            window.setTitle("Ajout element");
            window.setScene(scene2);
            window.show();
            
        }
        catch(IOException ex){    
        }
    }
    
    public void closeWindow (){
        
    }
    @FXML
        public void ajoutColis(ActionEvent e){
        if(from.getText().equals("") || to.getText().equals("") || befdate.getText().equals("") || shipname.getText().equals("") ){
            alert.setText("insert all fields ");
        }
        else{
        Colis c = new Colis();
        ColisService cs = new ColisService();
        c.setDepart(from.getText());
        c.setDestination(to.getText());
        c.setDate_limit(befdate.getText());
        c.setLabel(shipname.getText());
        c.setDescription(note.getText());
        c.setIdUtilisateur(1);
        c.setImage(fileName);
        cs.AjouterColis(c,oList2);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("OK!");
        
        try {
            Parent view3 = FXMLLoader.load(getClass().getResource("Liste_colis.fxml"));
            Scene scene2 =new Scene(view3);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setTitle("Afficher colis");
            window.setScene(scene2);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
        }
        }
    @FXML
     public void myColis(ActionEvent e){ 
        try{ 
            Parent Lcolis = FXMLLoader.load(getClass().getResource("Liste_colis.fxml"));
            Scene scene2 =new Scene(Lcolis);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
           //Stage window = new Stage();
            window.setTitle("Afficher colis");
            window.setScene(scene2);
            window.show();
            
        }
        catch(IOException ex){    
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ElementService es = new ElementService();
        List<Element> myCurrentItems = es.AfficherElement();
        oList = FXCollections.observableArrayList(myCurrentItems);
        myItems.setItems(oList);
        myItems.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        addedItems.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);  
        imageViewID.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
         System.out.println("Tile pressed ");
         FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
        File file =fileChooser.showOpenDialog(null);
        String path = "file:\\"+file.getPath();
        fileName = file.getName();
        if(file!=null){
        Image image = new Image(path);
        imageViewID.setImage(image);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        File outputfile = new File("C:\\Users\\yassine bayoudh\\Desktop\\Projets PI\\fithnitek\\src\\Images\\"+file.getName());
             try {
                 ImageIO.write(bImage, "jpg", outputfile);
             } catch (IOException ex) {
                 Logger.getLogger(Int_ajout_colisController.class.getName()).log(Level.SEVERE, null, ex);
             }
  }

        
           
        
         event.consume();
     });
    } 
    
    public void setItemsLabel(String Itemname){
        txtItemsList.setText(Itemname);
    }
    public Label getTxtItemsList() {
        return txtItemsList;
    }

    private void listerElements(ActionEvent e) {
          try {
            Parent view3 = FXMLLoader.load(getClass().getResource("ListViewElements.fxml"));
            Scene scene2 =new Scene(view3);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setTitle("Afficher colis");
            window.setScene(scene2);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    public List<Element> getElemets() {
        return elemets;
    }

    public void setElemets(List<Element> elemets) {
        this.elemets = elemets;
    }

    @FXML
    private void moveItems(ActionEvent event) {
        Node aNode = (Node)event.getSource();
        
        Element selectedItem = myItems.getSelectionModel().getSelectedItem();
        oList2.add(selectedItem);
        addedItems.setItems(oList2);
        oList.remove(selectedItem);

              
    }

    @FXML
    private void moveItemsToLEft(ActionEvent event) {
        Element selectedItem = addedItems.getSelectionModel().getSelectedItem();
        oList.add(selectedItem);
        myItems.setItems(oList);
        oList2.remove(selectedItem);
    }

    @FXML
    private void MoveToItems(ActionEvent event) {
        try {
            Parent view3 = FXMLLoader.load(getClass().getResource("int_ajout_elmt.fxml"));
            Scene scene2 =new Scene(view3);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Ajouter Colis");
            window.setScene(scene2);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
        
    }
    
    
    
}
