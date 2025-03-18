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
public class CartItem {
    private ProductViewDTO product;
    private int quantity;
    
    public ProductViewDTO getProduct() {
        return product;
    }

    public void setProduct(ProductViewDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CartItem(ProductViewDTO product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    
    public double getTotal(){
        return this.quantity * product.getUnitPrice();
    }
    @Override
    public String toString() {
        return product.getProductId()+":"+product.getProductImage()+":"+product.getProductName()+":"+this.quantity+":"+product.getUnitPrice();
    }
    
    
    
    
    
}
