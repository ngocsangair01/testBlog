package com.example.demedung.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBlog")
    private Integer id;

    @Nationalized
    private String title;

    @Nationalized
    private String content;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToMany
    @JoinTable(name = "typesOfBlogs",
    joinColumns = @JoinColumn(name = "idBlog",referencedColumnName = "idBlog"),
    inverseJoinColumns = @JoinColumn(name = "idType",referencedColumnName = "idType"))
    private Set<Type> types;

    public Blog(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }
}
