package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.model.Task;
import com.codegym.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@SessionAttributes("category")
public class TaskController {
    @Autowired
    private ITaskService taskService;

    @ModelAttribute("category")
    public Category setupCategory() {
        return new Category();
    }

    @GetMapping("/task")
    public ModelAndView showTask() {
        ModelAndView modelAndView = new ModelAndView("/task");
        modelAndView.addObject("tasks",taskService.findAll());
        return modelAndView;
    }

    @GetMapping("/add/{id}")
    public String addToCategory(@PathVariable Long id, @ModelAttribute Category category, @RequestParam("action") String action) {
        Optional<Task> taskOptional = taskService.findById(id);
        if (!taskOptional.isPresent()){
            return "error_404";
        }
        if (action.equals("show")){
            category.addTask(taskOptional.get());
            return "redirect:/category";
        }
        category.addTask(taskOptional.get());
        return "redirect:/task";
    }
}
