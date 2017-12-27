package com.github.multipartform.springbootmultipart.model.modelmapper;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@ToString
public class CreateFood {

    @NotNull
    private String name;

}
