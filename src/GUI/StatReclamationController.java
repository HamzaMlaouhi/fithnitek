/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ReclamationService;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class StatReclamationController implements Initializable {

    @FXML
    private PieChart pie;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnListe;
    @FXML
    private Button btnstat;
    @FXML
    private Button btnlistetraitees;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        int i,j,k,f,r;
       ReclamationService a = new ReclamationService();
       i=a.CountService("message1");
       k=a.CountService("message2");
       f=a.CountService("message3");
       r=a.CountService("message4");
        ObservableList<PieChart.Data> pieChartData =
               FXCollections.observableArrayList(
                       new PieChart.Data("message1",i),
                       new PieChart.Data("message2",k),
                       new PieChart.Data("message3",f),
                       new PieChart.Data("message4",r)
               );
        pie.setData(pieChartData);
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
