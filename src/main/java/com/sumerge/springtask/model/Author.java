package com.sumerge.springtask.model;

import jakarta.persistence.*;
import lombok.*;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;
    private String authorName;
    private String email;
    private Date authorBirthdate;

    @ManyToMany(mappedBy = "authors")
    private Set<Course> courses = new HashSet<>();

    public Author(String authorName, String email, Date authorBirthdate) {
        this.authorName = authorName;
        this.email = email;
        this.authorBirthdate = authorBirthdate;
    }
}
