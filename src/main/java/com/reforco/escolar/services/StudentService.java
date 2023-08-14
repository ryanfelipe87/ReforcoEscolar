package com.reforco.escolar.services;

import com.reforco.escolar.dtos.RegisterStudentDto;
import com.reforco.escolar.models.Turma;
import com.reforco.escolar.models.Student;
import com.reforco.escolar.repositories.TurmaRepository;
import com.reforco.escolar.repositories.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private StudentRepository studentRepository;

    //Criar alunos
    public Student createStudent(@Valid RegisterStudentDto registerStudentDto){
        Turma turma = new Turma();
        Student student = new Student();
        BeanUtils.copyProperties(registerStudentDto, student);
        BeanUtils.copyProperties(registerStudentDto, turma);
        student.setDateRegister(LocalDateTime.now());
        Turma classSaved = turmaRepository.save(turma);
        student.setTurma(classSaved);

        return studentRepository.save(student);
    }

    //Listar todos os alunos
    public List<Student> findAllStudents(){
        return (List<Student>) studentRepository.findAll();
    }

    //Buscar aluno po id
    public Student findStudentById(Long id){
        Optional<Student> result = studentRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        return new Student();
    }

    //Atualizar alunos por id
    public Student updateStudent(Student student){
        Optional<Student> result = studentRepository.findById(student.getId());
        Student existing = result.get();
        existing.setName(student.getName());
        existing.setDataNascimento(student.getDataNascimento());
        existing.setTurma(student.getTurma());
        return studentRepository.save(existing);
    }

    //Deletar aluno por id
    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }
}
