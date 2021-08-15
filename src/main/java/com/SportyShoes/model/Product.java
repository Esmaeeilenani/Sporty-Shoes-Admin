package com.SportyShoes.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Product_seq")
    @SequenceGenerator(name = "Product_seq", sequenceName = "Product_seq", initialValue = 1000, allocationSize = 1)
    private long id;
    private String name;
    private String description;
    private String category;
    private double price;
    private int inStock;

    @OneToMany(mappedBy = "product")
    private Set<PurchaseProduct> purchaseProducts;

    public Product() {
    }

    public Product(String name, String description, String category, double price, int inStock) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.inStock = inStock;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public void DecreaseStock(int quantity) {
        inStock -= quantity;
    }

    public void IncreaseStock(int quantity) {
        inStock += quantity;
    }

    public boolean isAvailable() {
        return this.inStock != 0;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", description=" + description + ", category=" + category + ", price=" + price + ", inStock=" + inStock + '}';
    }

}
