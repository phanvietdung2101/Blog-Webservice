package com.yyy.blog.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Length(min = 4, max = 50)
    private String username;

    @NotNull
    @Length(min = 4, max = 50)
    private String password;

    @Email
    @NotEmpty
    @Length(min = 6, max = 50)
    private String email;


}
