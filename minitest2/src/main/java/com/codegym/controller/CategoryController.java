package com.codegym.controller;

import com.codegym.model.Category;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CategoryController {
    @ModelAttribute("category")
    public Category setupCategory() {
        return new Category();
    }

    @GetMapping("/category")
    public ModelAndView showCategory(@SessionAttribute("category") Category category) {
        ModelAndView modelAndView = new ModelAndView("/category");
        modelAndView.addObject("category",category);
        return modelAndView;

    }
}
