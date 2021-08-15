package com.SportyShoes.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;

@Entity
public class Purchase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Purchase_seq")
    @SequenceGenerator(name = "Purchase_seq", sequenceName = "Purchase_seq", initialValue = 1, allocationSize = 1)
    private long id;

    private double total;

    private LocalDate purchaseDate;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    private Set<PurchaseProduct> purchaseProducts;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private Buyer buyer;

    public Purchase() {
        buyer = new Buyer();
        this.purchaseProducts = new HashSet<>();
        this.total = 0;
    }

    public Purchase(LocalDate purchaseDate) {
        this();
        this.purchaseDate = purchaseDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotal() {
        total = 0;
        purchaseProducts
                .forEach(purchaseProduct -> total += purchaseProduct.getSubTotal());
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public String getBuyerName() {
        return buyer.getName();
    }

    public int getNumOfItems() {
        return this.purchaseProducts.size();
    }

    public boolean isEmpty() {
        return purchaseProducts.isEmpty();
    }

    public void addProduct(Product product, int quantity) {

        PurchaseProduct purchaseProduct = new PurchaseProduct(this, product, quantity);

        //Decrease product in Stock by quantity
        product.DecreaseStock(quantity);

        purchaseProducts.add(purchaseProduct);
    }

    public void removeProductId(long id) {

        //getting PurchaseProduct by the product ID
        PurchaseProduct purchaseProduct = getProductId(id);

        Product product = purchaseProduct.getProduct();

        //when removing product from purchase we should add the quantity back
        product.IncreaseStock(purchaseProduct.getQuantity());

        //removing purchaseProduct
        purchaseProducts.remove(purchaseProduct);

    }

    public void removeAllProducts() {
//        purchaseProducts.forEach(purchaseProduct -> {
//            //remove all the product from the purchase
//            removeProductId(purchaseProduct.getProduct().getId());
//        });
        purchaseProducts.clear();
    }

    public PurchaseProduct getProductId(long id) {
        return purchaseProducts
                .stream()
                .filter(purchaseProduct -> purchaseProduct.getId().getProductId() == id)
                .findFirst()
                .orElse(null);
    }

    public Set<PurchaseProduct> getPurchaseProducts() {
        return purchaseProducts;
    }

    public List<Product> getAllProducts() {

        return purchaseProducts.stream()
                .map(purchaseProduct -> purchaseProduct.getProduct())
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Purchase{" + "id=" + id
                + ", total=" + getTotal()
                + ", purchaseDate=" + purchaseDate
                + ", Number of Products="
                + getNumOfItems()
                + ", Buyer name=" + (buyer != null ? buyer.getName() : "") + '}';
    }

}
