package com.SportyShoes.controller;

import com.SportyShoes.model.Admin;
import com.SportyShoes.model.Buyer;
import com.SportyShoes.model.Product;
import com.SportyShoes.service.ProductService;
import com.SportyShoes.service.PurchaseService;
import com.SportyShoes.service.UserService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/Users")
@SessionAttributes(names = "admin", types = Admin.class)
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ProductService productService;

    @Transactional
    @GetMapping("")
    public String UserList(Model model, @SessionAttribute Admin admin) {
        setCounts(model);

        List<Buyer> buyers = userService.allBuyers();
        model.addAttribute("buyers", buyers);

        return "admin/users/UserList";
    }

    @GetMapping("/delete")
    public String DeleteUser(Model model, @RequestParam(defaultValue = "-1", name = "id") long id, @SessionAttribute Admin admin) {
        if (id == -1) {
            model.addAttribute("errormsg", "User Id can not be empty");
            return "forward:/Users";
        }

        if (!userService.existsById(id)) {
            model.addAttribute("errormsg", "User with Id " + id + " dose not exists");
            return "forward:/Users";
        }

        userService.deleteUserId(id);
        return "redirect:/Users";
    }

    @Transactional
    @GetMapping("/view")
    public String ViewUser(Model model, @RequestParam(defaultValue = "-1", name = "id") long id, @SessionAttribute Admin admin) {
        setCounts(model);

        if (id == -1) {
            model.addAttribute("errormsg", "User Id can not be empty");
            return "forward:/Users";
        }

        if (!userService.existsById(id)) {
            model.addAttribute("errormsg", "User with Id " + id + " dose not exists");
            return "forward:/Users";
        }

        Buyer buyer = (Buyer) userService.getUserId(id);
        model.addAttribute("ViewBuyer", buyer);
        model.addAttribute("purchases", buyer.getPurchases());

        return "admin/users/User";
    }

    @PostMapping("/save")
    public String saveUser(Model model, Buyer buyer) {
        userService.saveUser(buyer);
        return "redirect:/Users";
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
