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
public class ProductViewDTO {
    private int productId;
    private String productName;
    private double unitPrice;
    private String productImage;
    private String description;
    private String categoryName;
    private String supplierName;
    private String QuantityPerUnit;

    public ProductViewDTO(int productId, String productName, double unitPrice, String productImage, String description, String categoryName, String supplierName, String QuantityPerUnit) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.productImage = productImage;
        this.description = description;
        this.categoryName = categoryName;
        this.supplierName = supplierName;
        this.QuantityPerUnit = QuantityPerUnit;
    }

    public ProductViewDTO(int productId, String productName, double unitPrice, String productImage) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.productImage = productImage;
    }
    

    public String getQuantityPerUnit() {
        return QuantityPerUnit;
    }

    public void setQuantityPerUnit(String QuantityPerUnit) {
        this.QuantityPerUnit = QuantityPerUnit;
    }

    

    

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Override
    public String toString() {
        return "ProductViewDTO{" + "productId=" + productId + ", productName=" + productName + ", unitPrice=" + unitPrice + ", productImage=" + productImage + ", description=" + description + ", categoryName=" + categoryName + ", supplierName=" + supplierName + '}';
    }

    
    
}
