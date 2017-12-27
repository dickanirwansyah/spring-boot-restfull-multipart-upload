package com.github.multipartform.springbootmultipart.dao.imp;

import com.github.multipartform.springbootmultipart.dao.FoodDAO;
import com.github.multipartform.springbootmultipart.model.Food;
import com.github.multipartform.springbootmultipart.model.modelmapper.CreateFood;
import com.github.multipartform.springbootmultipart.service.FoodService;
import com.github.multipartform.springbootmultipart.utilities.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodDAOimp implements FoodDAO{

    @Autowired
    private FoodService foodService;

    @Override
    public Food createFood(@DTO(CreateFood.class)Food food) {
        try{
            return foodService.createFood(food);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Food> findAllFoods() {
        try{
            return foodService.findAllFood();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Food findOneFoodById(Long id) {
        try{
            return foodService.findOneByIdfood(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Food updateFood(Food food) {
        try{
            return foodService.updateFood(food);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteFood(Food food) {
        try{
            foodService.deleteFood(food);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
