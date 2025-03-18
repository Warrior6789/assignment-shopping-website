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
public class OrderDetails {

    private int id;

    private int productid;
    private int orderid;

    public OrderDetails(int productid, int orderid, double unitprice, int quantity) {
        this.productid = productid;
        this.orderid = orderid;
        this.unitprice = unitprice;
        this.quantity = quantity;
    }
    private double unitprice;
    private int quantity;

    public OrderDetails(int id, int productid, int quantity) {
        this.id = id;
        this.productid = productid;
        this.quantity = quantity;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
