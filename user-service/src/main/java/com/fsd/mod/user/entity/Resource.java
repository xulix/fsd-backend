package com.fsd.mod.user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tbl_resource")
public class Resource extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "url", nullable = false)
    private String url;

}
