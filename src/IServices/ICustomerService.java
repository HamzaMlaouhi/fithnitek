/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Colis;
import com.stripe.Stripe;
import com.stripe.model.Customer;
/**
 *
 * @author yassine bayoudh
 */
public interface ICustomerService {
    public void createCostumor(String nom,String description);
    public void createCustomerCard(String cardNumber , String exp_month , String exp_year , String cvc);
    public void checkoutPayment(String currency, Colis c);
    //public void createTransfer(int amount , String currecny ,String transferGroup);
}
