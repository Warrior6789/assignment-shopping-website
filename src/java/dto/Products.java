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
public class Products {

    private int productId;
    private String name;
    private int supplierId;
    private int categoryId;
    private String quantityPerUnit;
    private double unitPrice;
    private String image;

    public Products(int productId, String name, int supplierId, int categoryId, String quantityPerUnit, double unitPrice, String image) {
        this.productId = productId;
        this.name = name;
        this.supplierId = supplierId;
        this.categoryId = categoryId;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.image = image;
    }

    public Products(String name, double unitPrice, int supplierId, int categoryId, String quantityPerUnit, String image) {
        this.productId = productId;
        this.name = name;
        this.supplierId = supplierId;
        this.categoryId = categoryId;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.image = image;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Products{" + "name=" + name + ", supplierId=" + supplierId + ", categoryId=" + categoryId + ", quantityPerUnit=" + quantityPerUnit + ", unitPrice=" + unitPrice + ", image=" + image + '}';
    }
    
}
