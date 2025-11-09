package com.india.railway.controller;

import com.india.railway.library.CommonUtil;
import com.india.railway.library.ExampleUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HomeController {
    @GetMapping("/greet")
    public String greet() {
        return ExampleUtil.getWelcomeMessage();
       // return "";
    }

    @GetMapping("/common")
    public String commonentity() {
        return CommonUtil.getWelcomeMessage();
        // return "";
    }



    @GetMapping("/")
    public String home() {
        return "index";

    }

    @GetMapping("/login")
    public String index(Model model) {
        model.addAttribute("name", "User");
        return "login"; // Thymeleaf looks for src/main/resources/templates/index.html
    }

    @GetMapping("/authenticate")
    public String addUsers(Model model) {
        model.addAttribute("name", "Authenticate User");
        return "index";
    }

    @GetMapping("/home")
    public String test(Model model) {
        model.addAttribute("name", "Test User");
        return "index";
    }

    @GetMapping("/loginafter")
    public String loginafter(Model model) {
        model.addAttribute("name", "loginafter");
        return "index";
    }
}
