package com.codegym.model;

import com.codegym.controller.CategoryController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;

public class Category {
    private Long id;

    private String name;
    private String description;

    public Category(){}

    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private Map<Task,Integer> tasks = new HashMap<>();
    public Category(Map<Task,Integer> tasks) {
        this.tasks =tasks;
    }

    public Map<Task,Integer> getTasks() {
        return tasks;
    }

    private boolean checkTaskInCategory(Task task) {
        for (Map.Entry<Task, Integer> entry : tasks.entrySet()){
            if (entry.getKey().getId().equals(task.getId())){
                return true;
            }
        }
        return false;
    }

    private Map.Entry<Task, Integer> selectTaskInCategory(Task task) {
        for (Map.Entry<Task, Integer> entry : tasks.entrySet()){
            if (entry.getKey().getId().equals(task.getId())){
                return entry;
            }
        }
        return null;
    }

    public void addTask(Task task) {
        if (!checkTaskInCategory(task)){
            tasks.put(task,1);
        } else {
            Map.Entry<Task, Integer> itemEntry = selectTaskInCategory(task);
            Integer newQuantity = itemEntry.getValue() + 1;
            tasks.replace(itemEntry.getKey(),newQuantity);
        }
    }

    public Integer countTaskQuantity() {
        Integer taskQuantity = 0;
        for (Map.Entry<Task, Integer> entry : tasks.entrySet()){
            taskQuantity += entry.getValue();
        }
        return taskQuantity;
    }

    public Integer countItemQuantity() {
        return tasks.size();
    }

    public Float countTotalPayment(){
        float payment = 0;
        for (Map.Entry<Task, Integer> entry : tasks.entrySet()){
            payment += entry.getKey().getAmount() * (float) entry.getValue();
        }
        return payment;
    }
}
