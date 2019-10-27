package com.fsd.mod.user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tbl_role")
public class Role extends BaseEntity {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private Set<Resource> resources;

}
