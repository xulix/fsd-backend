package com.fsd.mod.technology.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TechnologyVo {

    @NotBlank(message = "technology name must not be blank")
    private String name;

    @NotBlank(message = "toc must not be blank")
    private String toc;

    @NotBlank(message = "prerequisites must not be blank")
    private String prerequisites;

}
