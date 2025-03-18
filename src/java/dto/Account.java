/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author philo
 */
public class Account {
    private int id;
    private String UserName;
    private String Password;
    private String FullName;
    private  int type;

    public Account(int id, String UserName, String Password, String FullName, int type) {
        this.id = id;
        this.UserName = UserName;
        this.Password = Password;
        this.FullName = FullName;
        this.type = type;
    }

    public Account(String UserName, String Password, String FullName, int type) {
        this.UserName = UserName;
        this.Password = Password;
        this.FullName = FullName;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", UserName=" + UserName + ", Password=" + Password + ", FullName=" + FullName + ", type=" + type + '}';
    }
    
}
