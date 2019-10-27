package com.fsd.mod.training.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageDto<T> {
    private List<T> list;
    private long total;
}
