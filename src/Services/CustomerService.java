/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.MyDB;
import Entities.Colis;
import Entities.Personne;
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
    
    public CustomerService(){
            con = MyDB.getInstance().getConnection();
            Stripe.apiKey="sk_test_4GlkOPJpGqtu0nRBY9DRUYxq00i9FtqVKa";
    }

    @Override
    public void createCostumor(String nom,String description) {
        try {
            Map<String,Object> customerMap = new HashMap<>();
            customerMap.put("email",Personne.user.getPassword()); // Will be with Session
            customerMap.put("name",nom);
            customerMap.put("description",description);// Will be with Session
            Customer customer = Customer.create(customerMap);
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
            String id = getCurrentId(); // With SQL Query
           
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
            chargeParam.put("amount", c.getReward());
            chargeParam.put("currency", currency);
            chargeParam.put("customer",customer.getId() ); 
            Charge.create(chargeParam);
            
            
            String sql = "INSERT INTO transactions (id_costumer, product, amount,currency,status) VALUES (?,?,?,?,?)";
            ste = con.prepareStatement(sql);
            ste.setString(1, customer.getId());
            ste.setString(2, c.getLabel());
            //ste.setInt(3, c.getPrix());
            ste.setString(4,currency);
            //ste.setString(5, c.getStatus()); // TO DO in Colis Class
            ste.setString(5,"Payed");
            ste.executeUpdate();
        } catch (StripeException | SQLException ex) {
            Logger.getLogger(CustomerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
       

        public String getCurrentId(){
            String customerId=null;
            try {
            String sql = "SELECT id From Customer where email=?";
            ste = con.prepareStatement(sql);
            ste.setString(1,Personne.user.getPassword());
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
            customer = Customer.retrieve(getCurrentId()); // will be with session
        } catch (StripeException ex) {
            Logger.getLogger(CustomerService.class.getName()).log(Level.SEVERE, null, ex);
        }
            return customer;
        }
}


    

        
 

    
    
    
    

    
    

