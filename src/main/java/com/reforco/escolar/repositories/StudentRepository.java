package com.reforco.escolar.repositories;

import com.reforco.escolar.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
