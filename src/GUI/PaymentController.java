/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Colis;
import Services.CustomerService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author yassine bayoudh
 */
public class PaymentController implements Initializable {

    private TextField txtEmail;
    @FXML
    private TextField txtCardNumber;
    @FXML
    private TextField txtMonth;
    @FXML
    private TextField txtYear;
    @FXML
    private TextField txtVcV;
    @FXML
    private Button btnPay;
    @FXML
    private Button btnCancle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void payAction(ActionEvent event) {
        CustomerService cs = new CustomerService();
        cs.createCostumor("Yassine Bayoudh", "App Style Bolt Payment"); // Name will be retrieved with sessions
        cs.createCustomerCard(txtCardNumber.getText(), txtMonth.getText(), txtYear.getText(), txtVcV.getText());
        Colis c = new Colis(); // This will be retrieved from the "Colis" Page
        c.setLabel("Colis 1");
        //c.setPrix(300);
        cs.checkoutPayment("usd", c);
        
    }
    
}
