package com.SportyShoes.model;

import java.time.LocalDate;
import javax.persistence.Entity;

@Entity
public class Admin extends User {

    public Admin() {
        super();
    }

    public Admin(String name, String email, LocalDate dob, String password) {
        super(name, email, dob, password);
    }

    @Override
    public String toString() {
        return "Admin   " + super.toString();
    }

}
