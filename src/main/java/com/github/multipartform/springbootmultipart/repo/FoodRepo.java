package com.github.multipartform.springbootmultipart.repo;

import com.github.multipartform.springbootmultipart.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepo extends JpaRepository<Food, Long>{
}
