/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DataBase.MyDB;
import Entities.Transactions;
import Services.UtilisateurService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author yassine bayoudh
 */
public class TransactionsController implements Initializable {
    @FXML
    private TableView<Transactions> tabTransactions;
    @FXML
    private TableColumn<Transactions, String> cID;
    @FXML
    private TableColumn<Transactions, String> CidCostumer;
    @FXML
    private TableColumn<Transactions, String> CpackageName;
    @FXML
    private TableColumn<Transactions, String> cAmount;
    @FXML
    private TableColumn<Transactions, String> cCurrency;
    @FXML
    private TableColumn<Transactions, String> cStatus;
    @FXML
    private TableColumn<Transactions, String> cPurchaseTime;

    ObservableList<Transactions> oblist = FXCollections.observableArrayList();
    @FXML
    private Button btnPrintBill;
    
    private ResultSet res;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            Connection con = MyDB.getInstance().getConnection();
            res = con.createStatement().executeQuery("SELECT * FROM transactions");
            while(res.next()){
                Transactions t = new Transactions();
                t.setId(res.getInt(1));
                t.setIdCustomer(res.getString(2));
                t.setProduct(res.getString(3));
                t.setAmount(res.getString(4));
                t.setCurrecny(res.getString(5));
                t.setStatus(res.getString(6));
                t.setCreated_time(res.getString(7));
                oblist.add(t);
                tabTransactions.setItems(oblist);
                
            }
            
           /* cID.setCellValueFactory(new PropertyValueFactory<>("id"));
            CidCostumer.setCellValueFactory(new PropertyValueFactory<>("idCustomer"));*/
            CpackageName.setCellValueFactory(new PropertyValueFactory<>("product"));
            cAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
            cCurrency.setCellValueFactory(new PropertyValueFactory<>("currecny"));
            cStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            cPurchaseTime.setCellValueFactory(new PropertyValueFactory<>("created_time"));
        } catch (SQLException ex) {
            Logger.getLogger(TransactionsController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }    

    @FXML
    private void PrintBill(ActionEvent event) {
        try {
            String fileName ="C:\\Users\\asus\\Desktop\\My Projects Content\\test.pdf";
            Document doc = new Document();
            
            PdfWriter.getInstance(doc, new FileOutputStream(fileName));
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(fileName));;
            doc.open();
            Transactions t = tabTransactions.getSelectionModel().getSelectedItem();
            
            Font titleFont = new Font();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
            Date datenow = new Date();  
            
          /*  Paragraph email = new Paragraph(UtilisateurService.currentUser.getEmail());
            Paragraph name = new Paragraph(UtilisateurService.currentUser.getNom()+" "+UtilisateurService.currentUser.getPrenom());
            Paragraph date = new Paragraph(formatter.format(datenow)+"");*/
            Paragraph title = new Paragraph("Payment Bill");// NEW
            title.setFont(new Font(Font.FontFamily.SYMBOL, 55.0f));// NEW
            title.setAlignment(Paragraph.ALIGN_CENTER);// NEW
            Paragraph email = new Paragraph("email");// NEW
            Paragraph name = new Paragraph("name");// NEW
            Paragraph date = new Paragraph("Date : "+formatter.format(datenow));// NEW
            Paragraph footer = new Paragraph("This email is automatic please do not response , thank you");// NEW
            footer.setAlignment(Paragraph.ALIGN_CENTER);// NEW

            
            doc.add(title);// NEW
            
            Paragraph productref = new Paragraph("Product Ref :");// NEW
            productref.setAlignment(Paragraph.ALIGN_RIGHT); // NEW
            doc.add(productref);// NEW
            date.setSpacingAfter(50);// NEW

            doc.add(email);// NEW
            doc.add(name);// NEW
            doc.add(date);// NEW


            
            //Creating Table and Cells
            
            PdfPTable table = new PdfPTable(7);
            
            PdfPCell c1 = new PdfPCell(new Phrase("ID"));
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase("Customer ID"));
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase("Purshased Product"));
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase("Product Amount"));
            table.addCell(c1);
            
            c1 = new PdfPCell(new Phrase("Currency "));
            table.addCell(c1);  
            
            c1 = new PdfPCell(new Phrase("Status"));
            table.addCell(c1);
            
             c1 = new PdfPCell(new Phrase("Purchase Time"));
            table.addCell(c1);
            
            table.setHeaderRows(1);
            
            table.addCell(""+t.getId());
            table.addCell(t.getIdCustomer());
            table.addCell(t.getProduct());
            table.addCell(t.getAmount());
            table.addCell(t.getCurrecny());
            table.addCell(t.getStatus());
            table.addCell(t.getCreated_time());
            table.setSpacingAfter(50f); // NEW


            
            doc.add(table);

            doc.add(footer);// NEW
            /*Paragraph id = new Paragraph(t.getId());
            Paragraph idCostumer = new Paragraph(t.getIdCustomer());
            Paragraph packageName = new Paragraph(t.getProduct());
            Paragraph amountPayed = new Paragraph(t.getAmount());
            Paragraph currecny = new Paragraph(t.getCurrecny());
            Paragraph status = new Paragraph(t.getStatus());
            Paragraph purchaseTime = new Paragraph(t.getCreated_time());*/
           


            /*doc.add(id);
            doc.add(idCostumer);
            doc.add(packageName);
            doc.add(amountPayed);
            doc.add(currecny);
            doc.add(status);
            doc.add(purchaseTime);*/

            doc.close();
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(TransactionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
