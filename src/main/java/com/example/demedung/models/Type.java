package com.example.demedung.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idType")
    private Integer id;
    private String name;

    @ManyToMany(mappedBy = "types")
    private Set<Blog> blogs;
}
