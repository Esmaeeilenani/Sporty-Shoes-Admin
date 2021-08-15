package com.SportyShoes.controller;

import com.SportyShoes.model.Admin;
import com.SportyShoes.model.Buyer;
import com.SportyShoes.model.Product;
import com.SportyShoes.model.Purchase;
import com.SportyShoes.service.ProductService;
import com.SportyShoes.service.PurchaseService;
import com.SportyShoes.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes(names = "admin", types = Admin.class)
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ProductService productService;

    @GetMapping({"", "/index"})
    public String home(Model model, @SessionAttribute Admin admin) {

        setCounts(model);
        List<Purchase> purchases = purchaseService.allPurchases();
        model.addAttribute("purchases", purchases);

        return "admin/index";
    }

    @GetMapping("/Login")
    public String Login(Model model) {
        return "admin/Login";
    }

    @PostMapping("/LoginProcess")
    public String LoginProcess(Model model, @RequestParam(defaultValue = "") String email, @RequestParam(defaultValue = "") String Pass) {
        if (email.isEmpty() || Pass.isEmpty()) {
            model.addAttribute("errormsg", "Please fill all the field");
            return "admin/Login";
        }

        Admin admin = userService.getAdminByEmailAndPass(email, Pass);

        if (admin == null) {
            model.addAttribute("errormsg", "Password or email is worng");
            return "admin/Login";
        }
        
        model.addAttribute("admin", admin);
        return "redirect:/";
    }

    @GetMapping("/Logout")
    public String Logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "forward:/";
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
