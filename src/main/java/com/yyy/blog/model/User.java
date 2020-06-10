package com.yyy.blog.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Length(min = 6, max = 50)
    private String username;

    @NotEmpty
    @Length(min = 6, max = 50)
    @UniqueElements
    private String password;

    @Email
    @NotEmpty
    @Length(min = 6, max = 50)
    private String email;

    @ManyToOne
    private Role role;

}
