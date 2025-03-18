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
public class OrderView {
    private int orderid;
    private String orderdate;
    private String contactname;
    private String address;
    private int totalproduct;
    private double total;

    public OrderView(int orderid, String orderdate, String contactname, String address, int totalproduct, double total) {
        this.orderid = orderid;
        this.orderdate = orderdate;
        this.contactname = contactname;
        this.address = address;
        this.totalproduct = totalproduct;
        this.total = total;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalproduct() {
        return totalproduct;
    }

    public void setTotalproduct(int totalproduct) {
        this.totalproduct = totalproduct;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderView{" + "orderid=" + orderid + ", orderdate=" + orderdate + ", contactname=" + contactname + ", address=" + address + ", totalproduct=" + totalproduct + ", total=" + total + '}';
    }

    
    
}
