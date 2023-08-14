package com.reforco.escolar.controllers;

import com.reforco.escolar.dtos.RegisterStudentDto;
import com.reforco.escolar.models.Student;
import com.reforco.escolar.repositories.StudentRepository;
import com.reforco.escolar.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    //Lista todos os alunos
    @GetMapping("/alunos")
    public String findStudents(Model model){
        model.addAttribute("students", studentService.findAllStudents());
        return "students_list";
    }

    //Busca os alunos na aba de inserção
    @GetMapping("/alunos/novo")
    public String newStudentForm(Model model){
        model.addAttribute("student", new Student());
        return "new_student";
    }

    //Insere novos alunos
    @PostMapping("/alunos/novo")
    public String saveNewStudent(@Valid RegisterStudentDto registerStudentDto, BindingResult result, RedirectAttributes attributes, Model model){
        if(result.hasErrors()){
            model.addAttribute("registerStudentDto", new RegisterStudentDto());
            attributes.addFlashAttribute("message", "Verifique os campos");
            return "new_student";
        }

        studentService.createStudent(registerStudentDto);

        model.addAttribute("success_register", registerStudentDto);
        return "redirect:/alunos";
    }

    //Busca alunos para atualização
    @GetMapping("/alunos/editar")
    public String editStudent(@RequestParam("id") Long id, Model model){
        Student student = studentService.findStudentById(id);
        model.addAttribute("student", student);
        return "edit_student";
    }

    //Atualiza aluno
    @PostMapping("/alunos/editar")
    public String saveEditStudent(RegisterStudentDto registerStudentDto){
        studentService.createStudent(registerStudentDto);
        return "redirect:/alunos";
    }

    //Deleta aluno
    @DeleteMapping("/alunos/deletar")
    public String deleteStudent(@RequestParam ("id") Long id){
        studentService.deleteStudent(id);
        return "redirect:/alunos";
    }
}
