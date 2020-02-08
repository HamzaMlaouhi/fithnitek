/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Hamza Mlaouhi
 */
public class MyDB {
    
    String url = "jdbc:mysql://localhost/FiThnitek";
    String login = "root";
    String pwd = "root";
    public static MyDB instance;
    public Connection connection;
    
    private MyDB(){
    try {
             connection=DriverManager.getConnection(url, login, pwd);
             System.out.println("connexion etablie");
         } catch (SQLException ex) {
             System.out.println(ex);
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
