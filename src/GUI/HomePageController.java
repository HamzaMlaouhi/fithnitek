/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Colis;
import Services.ColisService;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

  

/**
 * FXML Controller class
 *
 * @author Wassim
 */
public class HomePageController implements Initializable {
 ObservableList<Colis> obserList = FXCollections.observableArrayList();
     List<Colis> listColis;

    private VBox mainLayer;
    

    
    List<VBox> PaneList = new ArrayList<>(); //our Collection to hold newly created Buttons
    @FXML
    private VBox ScrollLyout;
    @FXML
    private ScrollPane idScrollLayout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    ColisService cs = new ColisService();
    listColis = cs.AfficherColis();
        for(Colis c : listColis){
            VBox pane =  new VBox();
            pane.setSpacing(10);
            pane.setPadding(new Insets(50,50,50,50));
            Button btn = new Button("Send Request");
            if("".equals(c.getImage())){
               Image image = new Image("/Images/sana.jpg");
            }else{
            Image image = new Image("/Images/"+c.getImage());
            ImageView imgV = new ImageView(image);
            imgV.setFitHeight(100);
            imgV.setFitWidth(100);
            Label nameLabel = new Label("Package :"+c.getLabel());
            Label fromLabel = new Label("From :"+c.getDepart());
            Label toLabel = new Label("To :"+c.getDestination());
            Label dateLabel = new Label("Befor :"+c.getDate_limit());

            pane.getChildren().addAll(imgV,nameLabel,fromLabel,toLabel,dateLabel,btn);
             PaneList.add(pane);
            }
        }
        
               
         ScrollLyout.getChildren().addAll(PaneList);

       // Button btn = new Button("hello");
         //
        // gridLayout.getChildren().add(btn);
       // 
      //  for (Colis c : listColis) {
           // gridLayout.getChildren().add(new Button("test"));
    //}    

    }
}
