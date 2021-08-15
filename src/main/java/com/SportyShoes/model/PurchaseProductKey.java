package com.SportyShoes.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PurchaseProductKey implements Serializable {

    @Column(name = "purchase_id")
    private long purchaseId;

    @Column(name = "product_id")
    private long productId;

    public PurchaseProductKey() {
    }

    public PurchaseProductKey(long productId, long purchaseId) {
        this.productId = productId;
        this.purchaseId = purchaseId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(long purchaseId) {
        this.purchaseId = purchaseId;
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

}
