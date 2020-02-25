/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Colis;
import Services.ColisService;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.management.Notification;
import org.controlsfx.control.Notifications;

  

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
            HBox lkbir = new HBox();
            VBox img = new VBox();
            VBox fromto = new VBox();
            VBox felsa = new VBox();
            pane.setPrefSize(600,300);
            fromto.setSpacing(10);
            felsa.setAlignment(Pos.CENTER);
            fromto.setAlignment(Pos.CENTER);
            int buttonSize = 70; 
            Button btn = new Button("Send Request");
            btn.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
            
            //Button Event
            btn.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
                
                
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ListAll.fxml"));
                    Parent root = loader.load();
                    ListAllController lac = loader.getController();
                    lac.setColis(c);
                    Scene scene2 = new Scene(root);
                    Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            //Stage window = new Stage();
                        window.setTitle("Ajout Colis");
                        window.setScene(scene2);
                        window.show();

                    
                    
//               Notifications notificationBuilder = Notifications.create()
//                        .title("You've got a new notification")
//                        .text("Someone Wants your service")
//                        .graphic(null)
//                        .hideAfter(Duration.seconds(5))
//                        .position(Pos.TOP_LEFT)
//                        .onAction((event)->{
//                            System.out.println("qsd");
//                        });
//                notificationBuilder.showConfirm();
                } catch (IOException ex) {
                    Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
                }
                        });
            fromto.setLayoutX(50);
            if("".equals(c.getImage())){
               Image image = new Image("/Images/sana.jpg");
            }else{
            Image image = new Image("/Images/"+c.getImage());
            ImageView imgV = new ImageView(image);
            imgV.setFitHeight(100);
            imgV.setFitWidth(100); 
            Label nameLabel = new Label("Package :"+c.getLabel());
            nameLabel.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
            Label fromtoLabel = new Label ("From : "+c.getDepart()+" -> "+"To : "+c.getDestination());
            fromtoLabel.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
            Label dateLabel = new Label("Befor :"+c.getDate_limit());
            dateLabel.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
            Label wazn = new Label(cs.getColisOwner(c.getId()));
            wazn.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
            lkbir.setSpacing(30);
            pane.setMaxHeight(30);
            img.getChildren().addAll(imgV,wazn);
            fromto.getChildren().addAll(nameLabel,fromtoLabel,dateLabel);
            felsa.getChildren().add(btn);
            lkbir.getChildren().addAll(img,fromto,felsa);
            pane.getChildren().add(lkbir);
            PaneList.add(pane);       
            }
        } 
         ScrollLyout.getChildren().addAll(PaneList);  
    }
}
