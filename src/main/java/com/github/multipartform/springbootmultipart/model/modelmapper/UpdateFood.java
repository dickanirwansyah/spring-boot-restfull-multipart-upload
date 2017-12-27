package com.github.multipartform.springbootmultipart.model.modelmapper;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class UpdateFood {

    @NotNull
    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String name;

}
