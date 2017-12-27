package com.github.multipartform.springbootmultipart.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class WebConfigUpload {

    private static final String LOCATION_PATH="//var//www//html//server-poto//";

    //logger
    private Logger logger = LoggerFactory.getLogger(WebConfigUpload.class);

    //configurer upload
    public static void uploadFile(HttpServletRequest request, MultipartFile file, String code){

        if(!new File(LOCATION_PATH).exists()){
            new File(LOCATION_PATH).mkdirs();
        }

        try{
            file.transferTo(new File(LOCATION_PATH + code + ".jpg"));
        }catch (IOException e){

        }
    }
}
