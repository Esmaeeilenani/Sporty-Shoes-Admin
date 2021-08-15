package com.SportyShoes.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController extends RuntimeException {

    @ExceptionHandler(value = Exception.class)
    public String HandelException(HttpServletResponse response, Exception ex, HttpServletRequest request) throws ServletException, IOException {
        return "redirect:/";
    }

    @ExceptionHandler(value = ServletRequestBindingException.class)
    public String HandelNonAdmin(HttpServletResponse response, Exception ex, HttpServletRequest request) {
        if (ex.getMessage().equals("Missing session attribute 'admin' of type Admin")) {
            return "redirect:/Login";
        }

        return "redirect:/";
    }

}
