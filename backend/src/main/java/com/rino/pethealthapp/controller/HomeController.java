package com.rino.pethealthapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rino.pethealthapp.dto.response.HomeResponse;
import com.rino.pethealthapp.service.HomeService;

@RestController
@RequestMapping("/api")
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/home")
    public HomeResponse getHome() {
        return homeService.getHome();
    }
    
}
