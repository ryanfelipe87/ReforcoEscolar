package com.reforco.escolar.dtos;

import com.reforco.escolar.models.Turma;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RegisterStudentDto extends Turma {
    private String name;
    private LocalDate dataNascimento;
    private LocalDateTime dateRegister;
}
