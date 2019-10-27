package com.fsd.mod.technology.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tbl_technology")
public class Technology extends BaseEntity {
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "toc", nullable = false)
    private String toc;

    @Column(name = "prerequisites", nullable = false)
    private String prerequisites;
}
