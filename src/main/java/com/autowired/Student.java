package com.autowired;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import static jakarta.persistence.GenerationType.SEQUENCE;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "student"
)

public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1

    )
    @GeneratedValue(strategy = SEQUENCE,generator = "student_sequence")
    private Long id;
    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;
    @Column(
            columnDefinition = "TEXT"
    )
    private String lastName;
    @Column(
            nullable = false,
            unique = true
    )
    private String email;
    @Column(
            nullable = false
    )
    private Integer age;

}
