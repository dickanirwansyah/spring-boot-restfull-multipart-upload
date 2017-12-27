package com.github.multipartform.springbootmultipart.controller;

import com.github.multipartform.springbootmultipart.config.WebConfigUpload;
import com.github.multipartform.springbootmultipart.config.WebConfigUploadValidator;
import com.github.multipartform.springbootmultipart.dao.FoodDAO;
import com.github.multipartform.springbootmultipart.model.Food;
import com.github.multipartform.springbootmultipart.model.modelmapper.CreateFood;
import com.github.multipartform.springbootmultipart.model.modelmapper.UpdateFood;
import com.github.multipartform.springbootmultipart.utilities.DTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;


@RestController
@RequestMapping(value = "/api")
public class ControllerFood {

    @Autowired
    private FoodDAO foodDAO;

    private static final String PATH_DIST = "//home//java-spring//NetBeansProjects//";

    private Logger logger = LoggerFactory.getLogger(ControllerFood.class);

    /**
    @PostMapping(value = "/create")
    public ResponseEntity<Food> createFoods(@DTO(CreateFood.class)Food food){
        try{
            foodDAO.createFood(food);
            return new ResponseEntity<Food>(HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
     **/

    @PostMapping(value = "/reupload")
    public @ResponseBody void reupload(@RequestParam(value = "file") MultipartFile file, Food food, BindingResult result, HttpServletRequest request){
        new WebConfigUploadValidator().validate(food, result);

        new WebConfigUploadValidator().validate(food, result);

        if(food.getId() == null){
            insert(food);
            String originalName = "";
            try{
                originalName = food.getCode()+".jpg";
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream
                                (new FileOutputStream
                                        (new File("//var//www//html//server-poto//"+originalName)));
                stream.write(bytes);
                stream.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            update(food);
        }

        if(!food.getFile().getOriginalFilename().equals("")){
            WebConfigUpload.uploadFile(request, food.getFile(), food.getCode());
        }
    }

    //handling update and insert
    private void update(@DTO(UpdateFood.class)Food food){
        foodDAO.updateFood(food);
    }

    private void insert(@DTO(CreateFood.class)Food food){
        foodDAO.createFood(food);
    }


    @PutMapping(value = "/update")
    public ResponseEntity<Food> cobaUpdate(@DTO(UpdateFood.class)Food food){
        foodDAO.updateFood(food);
        return new ResponseEntity<Food>(HttpStatus.CREATED);
    }
}
