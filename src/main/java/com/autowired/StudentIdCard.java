package com.autowired;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentIdCard {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_id_card_sequence",
            allocationSize = 1

    )
    @GeneratedValue(strategy = SEQUENCE,generator = "student_id_card_sequence")
    private Long id;
    private String cardNumber;
    private ZonedDateTime createdAt;
    @PrePersist
    public  void prePersist(){
        this.createdAt=ZonedDateTime.now();
    }
}
