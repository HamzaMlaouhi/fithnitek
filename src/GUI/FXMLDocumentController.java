/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.CustomerService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author asus
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        CustomerService cs = new CustomerService();
        /*cs.createCostumor("test11@gmail.com","test test","test Description");
        cs.createCustomerCard("4242424242424242","11","2020","223");
        Colis c = new Colis();
        c.setPrix(300);
        c.setLabel("Bakou 7lib");
        cs.checkoutPayment("usd",c);*/
        //cs.createTransfer(400, "usd", "ORDER_45");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
        }
    
        
    


