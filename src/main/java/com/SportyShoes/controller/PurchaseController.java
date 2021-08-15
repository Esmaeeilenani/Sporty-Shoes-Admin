package com.SportyShoes.controller;

import com.SportyShoes.model.Admin;
import com.SportyShoes.model.Buyer;
import com.SportyShoes.model.Product;
import com.SportyShoes.model.Purchase;
import com.SportyShoes.model.PurchaseProduct;
import com.SportyShoes.service.ProductService;
import com.SportyShoes.service.PurchaseService;
import com.SportyShoes.service.UserService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/Purchases")
@SessionAttributes(names = "admin", types = Admin.class)
public class PurchaseController {

    @Autowired
    UserService userService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ProductService productService;

    @Transactional
    @GetMapping("")
    public String PurchasesList(Model model, @SessionAttribute Admin admin) {
        setCounts(model);
        List<Purchase> purchases = purchaseService.allPurchases();
        model.addAttribute("purchases", purchases);
        return "admin/purchases/PurchaseList";
    }

    @Transactional
    @GetMapping("/view")
    public String ViewPurchase(Model model, @RequestParam(defaultValue = "-1", name = "id") long id, @SessionAttribute Admin admin) {
        setCounts(model);

        if (id == -1) {
            model.addAttribute("errormsg", "Purchase Id can not be empty");
            return "forward:/Purchases";
        }

        if (!purchaseService.existsById(id)) {
            model.addAttribute("errormsg", "Purchase with Id " + id + " dose not exists");
            return "forward:/Purchases";
        }

        Purchase purchase = purchaseService.getPurchaseId(id);

        model.addAttribute("Purchase", purchase);

        Set<PurchaseProduct> purchaseProducts = purchase.getPurchaseProducts();

        model.addAttribute("purchaseProducts", purchaseProducts);
        return "admin/purchases/Purchase";
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
