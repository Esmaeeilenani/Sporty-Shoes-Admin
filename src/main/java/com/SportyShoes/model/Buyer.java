package com.SportyShoes.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Buyer extends User {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "buyer", cascade = CascadeType.ALL)
    private List<Purchase> purchases;

    public Buyer() {
        purchases = new ArrayList<>();
    }

    public Buyer(String name, String email, LocalDate dob, String password) {
        super(name, email, dob, password);
        purchases = new ArrayList<>();
    }

    public void addPurchase(Purchase purchase) {
        purchase.setBuyer(this);
        purchases.add(purchase);
    }

    public Purchase getPurchaseId(long id) {

        return purchases
                .stream()
                .filter(purchase -> purchase.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void removePurchaseId(long id) {
        Purchase purchase = getPurchaseId(id);
        purchase.setBuyer(null);
        purchase.removeAllProducts();
        purchases.remove(purchase);
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public int getNumOfPurchaes() {
        return purchases.size();
    }

    @Override
    public String toString() {
        return "Buyer   " + super.toString() + " number of purchases = " + purchases.size();
    }

}
