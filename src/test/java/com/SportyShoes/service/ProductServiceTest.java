package com.SportyShoes.service;

import com.SportyShoes.model.Product;
import java.util.List;
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

@SpringBootTest
@RunWith(SpringRunner.class)
@SqlGroup({
    @Sql(scripts = {"classpath:schemaTest.sql", "classpath:dataTest.sql"})
    ,
    @Sql(scripts = {"classpath:dropTest.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
public class ProductServiceTest {

    @Autowired
    private ProductService productService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void Add_New_Product_Success() {
        Product product = new Product("iphone", "new ", "mobiles", 5, 5);

        Throwable assertThrows = assertThrows(Throwable.class, () -> {
            productService.saveProduct(product);
            throw null;
        });

        assertNull(assertThrows.getMessage());
        logger.info("Product ->{}", product);
        logger.info("Throwable ->{}", assertThrows.getMessage());

    }

}
