package com.SportyShoes.repository;

import com.SportyShoes.model.PurchaseProduct;
import com.SportyShoes.model.PurchaseProductKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseProductRepo extends JpaRepository<PurchaseProduct, PurchaseProductKey> {

}
