package com.github.multipartform.springbootmultipart.controller;

import com.github.multipartform.springbootmultipart.dao.FoodDAO;
import com.github.multipartform.springbootmultipart.model.Food;
import com.github.multipartform.springbootmultipart.model.modelmapper.CreateFood;
import com.github.multipartform.springbootmultipart.utilities.DTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class ControllerUpload {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FoodDAO foodDAO;

    @PostMapping(value = "/upload")
    public @ResponseBody void uploads(@RequestParam(value = "file")MultipartFile file, Food food){

        //saved food
            insert(food);
        //upload file
        String originalname = "";
        try{
            originalname = food.getCode()+".jpg";
            byte[] bytes = file.getBytes();
            /**
             * NOTE !!!!
             * //var//www//html//server-poto//
             * (penempatan file di server php)
             *
             * */
            BufferedOutputStream stream =
                    new BufferedOutputStream
                            (new FileOutputStream(new File("//var//www//html//server-poto//"+originalname)));
            stream.write(bytes);
            stream.close();
        }catch (IOException e){
            logger.info("failed to upload !"+originalname);
        }
    }

    @DeleteMapping(value = "/deletefood/{id}")
    public ResponseEntity<String> deleteFood(@PathVariable String id){
        try{
            Food food = foodDAO.findOneFoodById(Long.parseLong(id));
            if(food == null){
                return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
            }
            foodDAO.deleteFood(food);
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(value = "/foods/{id}")
    public ResponseEntity<Food> findOneById(@PathVariable String id){
        try{
            Food food = foodDAO.findOneFoodById(Long.parseLong(id));
            if(food == null){
                return new ResponseEntity<Food>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<Food>(food, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(value = "/foods")
    public ResponseEntity<List<Food>> findAllFood(){
        try{
            List<Food> foodList = foodDAO.findAllFoods();
            if(foodList.isEmpty()){
                return new ResponseEntity<List<Food>>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<List<Food>>(foodList, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //DTO MAPPER INSERT FOOD
    private void insert(@DTO(CreateFood.class)Food food){
        foodDAO.createFood(food);
    }

    //DTO MAPPER UPDATE FOOD
}
