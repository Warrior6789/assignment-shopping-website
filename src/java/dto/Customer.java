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
public class Customer {
    private int id;
    private String contactName;
    private String address;
    private String phone;
    private String password;

    public Customer(int id, String contactName, String address, String phone, String password) {
        this.id = id;
        this.contactName = contactName;
        this.address = address;
        this.phone = phone;
        this.password = password;
    }

    public Customer(int id, String contactName, String address, String phone) {
        this.id = id;
        this.contactName = contactName;
        this.address = address;
        this.phone = phone;
    }
    

    public Customer(String contactName, String address, String phone, String password) {
        this.contactName = contactName;
        this.address = address;
        this.phone = phone;
        this.password = password;
    }

    public Customer(String contactName, String address, String phone) {
        this.contactName = contactName;
        this.address = address;
        this.phone = phone;
    }
    

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", contactName=" + contactName + ", address=" + address + ", phone=" + phone + ", password=" + password + '}';
    }

    
    
}
