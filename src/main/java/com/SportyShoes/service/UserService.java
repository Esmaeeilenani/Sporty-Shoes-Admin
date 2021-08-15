package com.SportyShoes.service;

import com.SportyShoes.model.Admin;
import com.SportyShoes.model.Buyer;
import com.SportyShoes.model.User;
import com.SportyShoes.repository.UserRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public User getUserId(long id) {
        return userRepo.findById(id).orElse(null);
    }

    public List<User> allUsers() {
        return userRepo.findAll();
    }

    public void deleteUserId(long id) {
        userRepo.deleteById(id);
    }

    public long count() {
        return userRepo.count();
    }

    public List<Buyer> allBuyers() {

        return userRepo.findAllBuyers();
    }

    public boolean existsById(long id) {
        return userRepo.existsById(id);
    }

    public Admin getAdminByEmailAndPass(String email, String pass) {
        return userRepo.findAdminByEmailAndPassword(email, pass);
    }

}
