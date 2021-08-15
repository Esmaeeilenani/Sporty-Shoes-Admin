package com.SportyShoes.service;

import com.SportyShoes.model.Purchase;
import com.SportyShoes.repository.PurchaseProductRepo;
import com.SportyShoes.repository.PurchaseRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepo purchaseRepo;

    @Autowired
    private PurchaseProductRepo purchaseProductRepo;

    public void savePurchase(Purchase purchase) {
        //save the purchase to database
        purchaseRepo.save(purchase);

        //save all the product of this purchase
        purchaseProductRepo.saveAll(purchase.getPurchaseProducts());

    }

    public Purchase getPurchaseId(long id) {
        return purchaseRepo.findById(id).orElse(null);
    }

    public List<Purchase> allPurchases() {
        return purchaseRepo.findAll();
    }

    public void deletePurchaseId(long id) {
        purchaseRepo.deleteById(id);
    }

    public long count() {
        return purchaseRepo.count();
    }

    public boolean existsById(long id) {
        return purchaseRepo.existsById(id);
    }

}
