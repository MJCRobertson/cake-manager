package com.waracle.cakemgr.thymeleaf;

import com.waracle.cakemgr.Cake.CakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ThymeleafController {

    @Autowired
    private CakeRepository cakeRepository;

    @RequestMapping("/")
    @ResponseBody
    public ModelAndView listCakes(Model model) {
        model.addAttribute("cakes", cakeRepository.findAll());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}