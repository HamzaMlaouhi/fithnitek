/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hamza Mlaouhi
 */
public class MyDB {
    
    String url = "jdbc:mysql://localhost/fithnitek";
    String login = "root";
    String pwd = "";
    public static MyDB instance;
    public Connection connection;
    
    private MyDB(){
    try {
        Class.forName("com.mysql.jdbc.Driver");
             connection=DriverManager.getConnection(url, login, pwd);
             System.out.println("connexion etablie");
         } catch (SQLException ex) {
             System.out.println(ex);
         } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyDB.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    public static MyDB getInstance(){
        if (instance==null)
        {
            return new MyDB();
        }
        return instance;
    }
    
    public Connection getConnection()
    {
     return connection;
    }
    
}
