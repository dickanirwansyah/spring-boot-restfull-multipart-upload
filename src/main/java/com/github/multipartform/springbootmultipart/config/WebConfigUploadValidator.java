package com.github.multipartform.springbootmultipart.config;

import com.github.multipartform.springbootmultipart.model.Food;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class WebConfigUploadValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return Food.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Food food = (Food) object;

        //check file dari entity food
        if(food.getFile() == null || food.getFile().getOriginalFilename().equals("")){
            errors.rejectValue("file", null, "please selected file to upload !");
            return;
        }

        if(!(food.getFile().getContentType().equals("server-poto/jpeg") ||
            food.getFile().getContentType().equals("server-poto/png") ||
            food.getFile().getContentType().equals("server-poto/gif"))){

            errors.rejectValue("file", null, "please selected format file to upload !");
            return;
        }
    }
}
