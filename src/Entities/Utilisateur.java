/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author Hamza Mlaouhi
 */
public class Utilisateur {
    private int id ;
    private String username;
    private String email;
    private String password;
    private Date last_login ;

    public Utilisateur() {
    }

    public Utilisateur(int id, String username, String email, String password, Date last_login) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.last_login = last_login;
    }
    
    public Utilisateur(int id, String username, String password,String email) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }
    
}
