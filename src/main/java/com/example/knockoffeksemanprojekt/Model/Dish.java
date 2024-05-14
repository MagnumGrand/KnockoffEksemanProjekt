package com.example.knockoffeksemanprojekt.Model;

public class Dish {
    private Long dishId;
    private String name;
    private String type;

    public Dish() {
    }

    public Dish(Long dishId, String name, String type) {
        this.dishId = dishId;
        this.name = name;
        this.type = type;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
