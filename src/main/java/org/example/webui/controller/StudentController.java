package org.example.webui.controller;

import org.example.webui.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students")
public class StudentController {

    public final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping // /students 에서 처리 된다
    public String list(Model model){
        //findall 자체가 List<ResponseStudent>을 반환하는 형식
        // 그 리스트를 Model 안에 "students"라는 이름으로 등록해서 View로 넘김
        // <tr th:each="student : ${students}"> 에서 받음
        // Thymeleaf가 그 Model을 읽는 구조
        model.addAttribute("students",studentService.findall()); // 전체 학생 데이터를 받는 내용
        return "students/list";
    }

}
