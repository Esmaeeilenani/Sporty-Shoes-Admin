package com.SportyShoes.service;

import com.SportyShoes.model.Buyer;
import com.SportyShoes.model.Product;
import com.SportyShoes.model.Purchase;
import com.SportyShoes.model.PurchaseProduct;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
@SqlGroup({
    @Sql(scripts = {"classpath:schemaTest.sql", "classpath:dataTest.sql"} ,executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    ,
    @Sql(scripts = {"classpath:dropTest.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ProductService productService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void Add_New_Buyer_NoError_Success() {
        Buyer buyer = new Buyer("esmaeeil", "esmaeeil@gmail.com", LocalDate.of(1998, 5, 5), "123");

        Throwable assertThrows = assertThrows(Throwable.class, () -> {
            userService.saveUser(buyer);
            throw null;
        });

        assertNull(assertThrows.getMessage());
        logger.info("Buyer added ->{}", buyer);
    }

    @Test
    public void Select_All_Buyers_Success() {

        Throwable assertThrows = assertThrows(Throwable.class, () -> {
            userService.allBuyers();
            throw null;
        });

        assertNull(assertThrows.getMessage());

    }

    @Transactional
    @Test
    public void Select_Buyer_Purchese_NoError_Success() {

        Throwable assertThrows = assertThrows(Throwable.class, () -> {
            Buyer buyer = (Buyer) userService.getUserId(1);
            logger.info("Buyer ->{}", buyer);
            List<Purchase> purchases = buyer.getPurchases();

            logger.info("Printing All Buyer purchases");

            //purchases loop
            purchases.forEach(purchase -> {

                logger.info("purchase Information ->{}", purchase);
                logger.info("------------------------------------------------------");
                logger.info("Pruducts in Purchase");

                Set<PurchaseProduct> purchaseProducts = purchase.getPurchaseProducts();

                purchaseProducts.forEach(productOfpurchase -> {
                    Product product = productOfpurchase.getProduct();
                    logger.info("Product Name: ->{}", product.getName());
                    logger.info("Product Descreption: ->{}", product.getDescription());
                    logger.info("Product Price: ->{}", product.getPrice());                    
                    logger.info("Orderd Quantity: ->{}", productOfpurchase.getQuantity());
                    logger.info("SubTotal of Product: ->{}", productOfpurchase.getSubTotal());
                    logger.info("------------------------------------------------------------------------\n\n");
                });

                logger.info("purchase Total Price ->{}", purchase.getTotal());
                logger.info("\n\n");
            });

            throw null;
        });
        assertNull(assertThrows.getMessage());
    }

    @Transactional
    @Test
    public void Delete_Buyer_Purchase_NoErro_Success() {

        Throwable assertThrows = assertThrows(Throwable.class, () -> {
            Buyer buyer = (Buyer) userService.getUserId(1);
            logger.info("Buyer ->{}", buyer);

            buyer.removePurchaseId(1);
            purchaseService.deletePurchaseId(1);

            userService.saveUser(buyer);

            throw null;
        });

        assertNull(assertThrows.getMessage());

        Purchase purchase = purchaseService.getPurchaseId(1);
        assertNull(purchase);

    }

    @Transactional
    @Test
    public void Add_New_Purchase_To_Buyer_NoError_Success() {
        Buyer buyer = (Buyer) userService.getUserId(1);
        logger.info("Buyer ->{}", buyer);
        Purchase purchase = new Purchase(LocalDate.now());

        Product product1 = productService.getProductId(1001);
        Product product2 = productService.getProductId(1002);

        purchase.addProduct(product1, 3);
        purchase.addProduct(product2, 5);

        buyer.addPurchase(purchase);

        Throwable assertThrows = assertThrows(Throwable.class, () -> {
            userService.saveUser(buyer);
            throw null;
        });

        Purchase newPurchase = purchaseService.getPurchaseId(5);

        assertNotNull(newPurchase);
        logger.info("the new Purchase->{}", newPurchase);

        assertNull(assertThrows.getMessage());

    }

}
