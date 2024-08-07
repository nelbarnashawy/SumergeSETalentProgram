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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authorName;
    private String email;
    private Date authorBirthdate;

}
