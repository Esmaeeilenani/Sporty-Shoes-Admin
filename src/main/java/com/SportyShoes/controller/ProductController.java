package com.SportyShoes.controller;

import com.SportyShoes.model.Admin;
import com.SportyShoes.model.Buyer;
import com.SportyShoes.model.Product;
import com.SportyShoes.service.ProductService;
import com.SportyShoes.service.PurchaseService;
import com.SportyShoes.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/Products")
@SessionAttributes(names = "admin", types = Admin.class)
public class ProductController {

    @Autowired
    UserService userService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String ProductList(Model model,@SessionAttribute Admin admin) {
        setCounts(model);

        List<Product> products = productService.allProducts();

        model.addAttribute("products", products);

        return "admin/products/ProductList";
    }

    @PostMapping("/save")
    public String saveProduct(Model model, Product product ,@SessionAttribute Admin admin) {

        productService.saveProduct(product);

        return "redirect:/Products";
    }

    @GetMapping("/edit")
    public String editProduct(Model model, @RequestParam(defaultValue = "-1") long id ,@SessionAttribute Admin admin) {
        setCounts(model);
        if (id <= -1) {
            model.addAttribute("errormsg", "Product Id can not be empty");
            return "forward:/Products";
        }
        Product product = productService.getProductId(id);

        if (product == null) {
            model.addAttribute("errormsg", "Product with Id " + id + " dose not exists");
            return "forward:/Products";
        }

        model.addAttribute("EditShoes", product);
        return "admin/products/EditProduct";
    }

    @GetMapping("/delete")
    public String deleteProduct(Model model, @RequestParam(defaultValue = "-1") long id ,@SessionAttribute Admin admin) {

        if (id == -1) {
            model.addAttribute("errormsg", "Product Id can not be empty");
            return "forward:/Products";
        }

        if (!productService.existsById(id)) {
            model.addAttribute("errormsg", "Product with Id " + id + " dose not exists");
            return "forward:/Products";
        }

        productService.deleteProductId(id);
        return "redirect:/Products";
    }

    //to set the count of each Entity
    public void setCounts(Model model) {
        long UserCount = userService.count();
        long ShoesCount = productService.count();
        long PurchaseCount = purchaseService.count();
        model.addAttribute("UserCount", UserCount);
        model.addAttribute("ShoesCount", ShoesCount);
        model.addAttribute("PurchaseCount", PurchaseCount);

        model.addAttribute("Newshoes", new Product());
        model.addAttribute("Newbuyer", new Buyer());

    }

}
