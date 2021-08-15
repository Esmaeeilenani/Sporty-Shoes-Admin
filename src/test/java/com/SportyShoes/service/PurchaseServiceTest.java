package com.SportyShoes.service;

import com.SportyShoes.model.Buyer;
import static org.junit.jupiter.api.Assertions.*;
import com.SportyShoes.model.Product;
import com.SportyShoes.model.Purchase;
import com.SportyShoes.model.User;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
@SqlGroup({
    @Sql(scripts = {"classpath:schemaTest.sql", "classpath:dataTest.sql"})
    ,
    @Sql(scripts = {"classpath:dropTest.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
public class PurchaseServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    @Test
    @DirtiesContext
    public void SavePurchase_With_Products_Succsess() {
        try {

            List<Product> products = productService.allProducts();
            Buyer Buyer = (Buyer) userService.getUserId(1);

            Purchase purchase = new Purchase(LocalDate.now());
            purchase.setBuyer(Buyer);

            products.forEach(product -> {
                logger.info("Befor Adding product to Purchase ->{}", product);
                purchase.addProduct(product, product.getInStock());

            });

            products = productService.allProducts();

            products.forEach(product -> {
                logger.info("-----------------------------------------------");
                logger.info("After Adding product to Purchase ->{}", product);
                logger.info("-----------------------------------------------");

            });

            Throwable assertThrows = assertThrows(Throwable.class, () -> {
                purchaseService.savePurchase(purchase);
                throw null;
            });

            logger.info("Purchase ->{}", purchase);
            assertNull(assertThrows.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    @Test
    public void Get_Purches_Success() {
        try {
            Purchase purchase = purchaseService.getPurchaseId(1);

            logger.info("purchase ->{}", purchase);
            assertNotNull(purchase);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Transactional
    @Test
    public void Get_AllPurches_Success() {

        try {
            List<Purchase> purchases = purchaseService.allPurchases();

            logger.info("Get_AllPurches_Success--------------------------------------");
            purchases.forEach(purchase -> {
                logger.info("purchase ->{}", purchase);
            });

            assertEquals(4, purchases.size());
            logger.info("Get_AllPurches_Success--------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
