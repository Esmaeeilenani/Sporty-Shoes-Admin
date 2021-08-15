package com.SportyShoes.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class PurchaseProduct implements Serializable {

    @EmbeddedId
    private PurchaseProductKey id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @MapsId("purchaseId")
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    private double subTotal;

    public PurchaseProduct() {
        id = new PurchaseProductKey();
    }

    public PurchaseProduct(Purchase purchase, Product product, int quantity) {
        this();
        id.setProductId(product.getId());
        this.purchase = purchase;
        this.product = product;
        this.quantity = quantity;
        this.subTotal = quantity * product.getPrice();
    }

    public PurchaseProductKey getId() {
        return id;
    }

    public void setId(PurchaseProductKey id) {
        this.id = id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubTotal() {
        this.subTotal = quantity * product.getPrice();
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.purchase);
        hash = 47 * hash + Objects.hashCode(this.product);
        hash = 47 * hash + this.quantity;
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.subTotal) ^ (Double.doubleToLongBits(this.subTotal) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PurchaseProduct other = (PurchaseProduct) obj;
        if (this.quantity != other.quantity) {
            return false;
        }
        if (Double.doubleToLongBits(this.subTotal) != Double.doubleToLongBits(other.subTotal)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.purchase, other.purchase)) {
            return false;
        }
        return Objects.equals(this.product, other.product);
    }

    @Override
    public String toString() {
        return "PurchaseProduct{" + "purchase Number = " + purchase.getId() + ", product Name = " + product.getName() + ", quantity = " + quantity + ", subTotal = " + subTotal + '}';
    }

}
