package com.yyy.blog.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Length(min = 4)
    private String title;

    @Column(columnDefinition="TEXT",length = 50000)
    @NotEmpty
    @Length(min = 100)
    private String content;

    @ManyToOne
    @NotNull
    private User user;

    @OneToMany
    private List<Like> likeList;

    @OneToMany
    private List<Comment> commentList;
}
