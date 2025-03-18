package dto;

import java.sql.Date;

public class Order {
    private int id;
    private int customerid;
    private Date orderdate;
    private Date requiredate;
    private double freight;
    private String address;
    
    // Constructor
    public Order(int id, int customerid, Date orderdate, Date requiredate, double freight, String address) {
        this.id = id;
        this.customerid = customerid;
        this.orderdate = orderdate;
        this.requiredate = requiredate;
        this.freight = freight;
        this.address = address;
    }

    public Order(int customerid, Date orderdate, Date requiredate, double freight, String address) {
        this.customerid = customerid;
        this.orderdate = orderdate;
        this.requiredate = requiredate;
        this.freight = freight;
        this.address = address;
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public Date getRequiredate() {
        return requiredate;
    }

    public void setRequiredate(Date requiredate) {
        this.requiredate = requiredate;
    }

    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
