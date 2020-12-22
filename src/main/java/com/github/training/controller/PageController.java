package com.github.training.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class PageController {
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/")
    public String root(){
        return "redirect:/index";
    }
    @GetMapping("/index")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }
}
