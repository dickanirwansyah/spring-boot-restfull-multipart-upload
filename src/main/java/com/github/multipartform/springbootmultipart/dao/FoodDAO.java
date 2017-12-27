package com.github.multipartform.springbootmultipart.dao;

import com.github.multipartform.springbootmultipart.model.Food;

import java.util.List;

public interface FoodDAO {

    Food createFood(Food food);

    List<Food> findAllFoods();

    Food findOneFoodById(Long id);

    Food updateFood(Food food);

    void deleteFood(Food food);
}
