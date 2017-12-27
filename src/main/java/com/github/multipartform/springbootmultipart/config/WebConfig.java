package com.github.multipartform.springbootmultipart.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.multipartform.springbootmultipart.utilities.DTOModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.persistence.EntityManager;
import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    private final ApplicationContext applicationContext;
    private final EntityManager entityManager;

    @Autowired
    public WebConfig(ApplicationContext applicationContext, EntityManager entityManager){
        this.applicationContext = applicationContext;
        this.entityManager = entityManager;
    }

    //mapper config
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
                .applicationContext(this.applicationContext).build();
        argumentResolvers.add(new DTOModelMapper(objectMapper, entityManager));
    }

}
