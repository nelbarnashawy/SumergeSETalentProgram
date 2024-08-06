package com.sumerge.springtask.model;

import jakarta.persistence.*;
import lombok.*;


import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String author_name;
    private String email;
    private Date author_birthdate;

}
