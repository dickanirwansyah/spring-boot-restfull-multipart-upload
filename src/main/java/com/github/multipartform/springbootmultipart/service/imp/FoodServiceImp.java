package com.github.multipartform.springbootmultipart.service.imp;

import com.github.multipartform.springbootmultipart.model.Food;
import com.github.multipartform.springbootmultipart.repo.FoodRepo;
import com.github.multipartform.springbootmultipart.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class FoodServiceImp implements FoodService{

    @Autowired
    private FoodRepo foodRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Food createFood(Food food) {
        try{
            return foodRepo.save(food);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Food updateFood(Food food) {
        try{
            if(!entityManager.contains(food))
                food = entityManager.merge(food);
            return food;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteFood(Food food) {
        try{
            foodRepo.delete(food);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Food> findAllFood() {
        try{
            return foodRepo.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Food findOneByIdfood(Long id) {
        try{
            return foodRepo.findOne(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
