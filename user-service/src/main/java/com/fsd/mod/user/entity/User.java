package com.fsd.mod.user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tbl_user")
public class User extends BaseEntity {

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "linkedin_url")
    private String linkedinUrl;

    @Column(name = "years_of_experience")
    private Float yearsOfExperience;

    @Column(name = "active")
    private Boolean active = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private Set<Role> roles;

}
