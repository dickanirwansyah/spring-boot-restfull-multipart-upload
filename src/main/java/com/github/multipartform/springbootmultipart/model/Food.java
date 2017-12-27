package com.github.multipartform.springbootmultipart.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@ToString
@Data
@Entity
@Table(name = "food",
        catalog = "belajar")
public class Food implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Transient
    private MultipartFile file;

    public Food(){
        this.code = "food-"+ UUID.randomUUID()
                .toString().substring(19)
                .toUpperCase();
    }
}
