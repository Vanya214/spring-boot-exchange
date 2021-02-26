package com.project.controller;

import com.project.exchange_api.ExchangeDataLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {


    @GetMapping("/")
    public String getMainPage(Model model){

        List<String> exchangeRates = ExchangeDataLoader.getLiveRates();
        System.out.println(" ");

        model.addAttribute("rates" , exchangeRates);

        return "main";
    }
}
