package ru.geekbrains.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping
    public String indexPage(){
        return "index";
    }
}
