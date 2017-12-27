package com.github.multipartform.springbootmultipart.service;

import com.github.multipartform.springbootmultipart.model.Food;

import java.util.List;

public interface FoodService {

    Food createFood(Food food);

    Food updateFood(Food food);

    void deleteFood(Food food);

    List<Food> findAllFood();

    Food findOneByIdfood(Long id);
}
