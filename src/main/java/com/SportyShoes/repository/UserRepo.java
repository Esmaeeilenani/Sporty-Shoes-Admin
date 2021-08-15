package com.SportyShoes.repository;

import com.SportyShoes.model.Admin;
import com.SportyShoes.model.Buyer;
import com.SportyShoes.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query("select b from Buyer b")
    public List<Buyer> findAllBuyers();

    public Admin findAdminByEmailAndPassword(String email, String password); 
    
}
