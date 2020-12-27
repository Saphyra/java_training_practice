package com.github.training.controller;

import com.github.training.database.AccountRepository;
import com.github.training.database.LoginSession;
import com.github.training.service.LoginSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PageController {
    private final LoginSessionService loginSessionService;
    private final AccountRepository accountRepository;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public ModelAndView index(@CookieValue(name = "session-id", required = false) UUID sessionId) {
        Optional<LoginSession> loginSessionOptional = loginSessionService.findBySessionId(sessionId);
        ModelAndView mav = new ModelAndView("index");
        if (loginSessionOptional.isPresent()) {
            mav.addObject("userLoggedIn", true);
            mav.addObject("username", accountRepository.findByUserId(loginSessionOptional.get().getUserId()).getUsername());
        } else {
            mav.addObject("userLoggedIn", false);
        }
        return mav;
    }

    @GetMapping("/logout")
    public String logout(@CookieValue(name = "session-id", required = false) UUID sessionId, HttpServletResponse response) {
        loginSessionService.logout(sessionId);

        Cookie cookie = new Cookie("session-id", "");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(-1);

        response.addCookie(cookie);

        return "redirect:/index";
    }

    @GetMapping("/account")
    public ModelAndView account(@CookieValue(name = "session-id") UUID sessionId){
        log.info("Account page called");
        ModelAndView mav = new ModelAndView("account");

        return mav;
    }
}
