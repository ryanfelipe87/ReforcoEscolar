package com.reforco.escolar.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private LocalDate dataNascimento;

    @Column
    private LocalDateTime dateRegister;

    @OneToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

}
