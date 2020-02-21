/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.MyDB;
import Entities.Colis;
import IServices.ICustomerService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.Balance;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Payout;
import com.stripe.model.Token;
import com.stripe.model.Transfer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yassine bayoudh
 */
public class CustomerService implements ICustomerService{
    Connection con;
    PreparedStatement ste;
    String currentCustomerID =""; // Will be autoLoaded with Session
    
    public CustomerService(){
            con = MyDB.getInstance().getConnection();
            Stripe.apiKey="sk_test_2APKdZmMUOLIX9CiUu3hdB9Z00kd880Rdb";
    }

    @Override
    public void createCostumor(String email,String nom,String description) {
        try {
            Map<String,Object> customerMap = new HashMap<>();
            customerMap.put("email",email); // Will be with Session
            customerMap.put("name",nom);// Will be with Session
            customerMap.put("description",description);// Will be with Session
            Customer customer = Customer.create(customerMap);
            currentCustomerID+=customer.getId();
            String sql = "INSERT INTO customer (id, FullName, email) VALUES (?,?,?)";
            ste = con.prepareStatement(sql);
            ste.setString(1, customer.getId());
            ste.setString(2, customer.getName());
            ste.setString(3, customer.getEmail());
            ste.executeUpdate();    
        } catch (StripeException ex) {
            ex.getMessage();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void createCustomerCard(String cardNumber, String exp_month, String exp_year, String cvc) {
        try {
            String id = currentCustomerID; // With SQL Query
           
            
                        
            
            Customer customer = Customer.retrieve(id);
            Map<String,Object> cardParam = new HashMap<>();
            Map<String,Object> tokenParam = new HashMap<>();
            Map<String,Object> toSource = new HashMap<>();
            
            cardParam.put("number", cardNumber);
            cardParam.put("exp_month", exp_month);
            cardParam.put("exp_year", exp_year);
            cardParam.put("cvc", cvc);
            
            tokenParam.put("card", cardParam);
            
            Token token = Token.create(tokenParam);
            
            toSource.put("source", token.getId());
            customer.getSources().create(toSource);
        } catch (StripeException ex) {
            Logger.getLogger(CustomerService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void checkoutPayment(String currency,Colis c) {
        Customer customer = getCurrentCostumer();
        try {
            Map<String,Object> chargeParam = new HashMap<>();
            chargeParam.put("amount", c.getPrix());
            chargeParam.put("currency", currency);
            chargeParam.put("customer",currentCustomerID ); // will be with session
            Charge.create(chargeParam);
            
            
            String sql = "INSERT INTO transactions (id_costumer, product, amount,currency,status) VALUES (?,?,?,?,?)";
            ste = con.prepareStatement(sql);
            ste.setString(1, customer.getId());
            ste.setString(2, c.getLabel());
            ste.setInt(3, c.getPrix());
            ste.setString(4,currency);
            //ste.setString(5, c.getStatus()); // TO DO in Colis Class
            ste.setString(5,"Payed");
            ste.executeUpdate();
        } catch (StripeException | SQLException ex) {
            Logger.getLogger(CustomerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
       

        public String getCurrentId(String email){
            String customerId=null;
            try {
            String sql = "SELECT id From Customer where email=?";
            ste = con.prepareStatement(sql);
            ste.setString(1,email);
            ResultSet result = ste.executeQuery();
            while(result.next())
                customerId = result.getString("id");
        } catch (SQLException ex) {
            Logger.getLogger(CustomerService.class.getName()).log(Level.SEVERE, null, ex);
        }
           return customerId;
       }

       
        public Customer getCurrentCostumer(){
            Customer customer = null;
            try {
            customer = Customer.retrieve(currentCustomerID); // will be with session
        } catch (StripeException ex) {
            Logger.getLogger(CustomerService.class.getName()).log(Level.SEVERE, null, ex);
        }
            return customer;
        }

    @Override
    public void createTransfer(int amount, String currecny,String transferGroup) {
        try {
            String recipientId="cus_Gk5cAAlXXbRiH9";
            Customer c = getCurrentCostumer();
            Map<String, Object> payoutParams = new HashMap<>();
            payoutParams.put("amount", 100); // amount in cents
            payoutParams.put("currency", "usd");
            payoutParams.put("recipient", recipientId);
            payoutParams.put("bank_account", "ba_1GCmqWE1hwlWf2TGFH7Yndl2");
            payoutParams.put("statement_descriptor", "JULY SALES");
            
            Payout payout = Payout.create(payoutParams);
        } catch (StripeException ex) {
            Logger.getLogger(CustomerService.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    
        
        
    
}

    
    
    
    

    
    

