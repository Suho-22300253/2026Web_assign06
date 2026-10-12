package org.example.webui.controller;

import org.example.webui.dto.RequestStudent;
import org.example.webui.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

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

    // list.html의 <a>의 href URL과 Controller의 @GetMapping URL이 같아서 연결이되는 거다
    /*
    list.html에서 @{/students/new} 은 thymeleaf 문법 -> href를 thymeleaf가 처리 -> url을 만듬

    이를 form 메서드와 연결하는 이유
    기본적으로 a tag는 냥 URL로 이동하는 기능 -> controller 없이도 이동 가능 : static 폴더 내에서는 가은
    하지만 template 폴더에서는 브라우저가 직접 요청해서 가져가는 파일이 아니라, Controller가 Thymeleaf에게 렌더링을 시켜서 보여주는 View

    정리
    기존 : <a href="form.html">Add</a> 의 경우 브라우저는 form.html 파일로 직접 이동
    springboot: templates 폴더의 HTML 파일을 직접 열지 않고, 먼저 Controller에 URL 요청을 보냄
    받은 url 을 처리하는 controller 가 필요

    브라우저는 GET /students/new를 보낸다. = 서버야, /students/new 주소의 내용을 보여달라는 내용

     */
    @GetMapping("/new")
    public String form() {
        return "students/form";
    }

    @PostMapping
    /*
    form의 method - post 형식으로 보낸다 -> submit 시 form의 데이터 전송
    @ModelAttribute Spring MVC가 Form의 name과 RequestStudent의 필드를 비교해서 연결해준다
    하나하나 값을 꺼내서 객체를 만들 필요가 없음

    new 를 안해주는 이유 : new -> 만드는 화면
    post : 실제로 만드는 것
    return students/list 할 경우 데이터 추가 화면이 발생하지 않음
    정상: create -> 학생 저장 -> redirect: 브라우저에게 /students로 다시 요청 -> 브라우저가 get으로 url 받음
 String list(Model model) 실행 -> 화면에 보여줌
    현재: 학생 저장 -> list.html 바로 렌더링(<tr th:each="student : ${students}">가 있는데
                    post 에서는 model.addAttribute("students", ...) 을 진행하지 않았다.)
     */
    public String create( @ModelAttribute RequestStudent request) {
        studentService.save(request);
        // /students로 다시 요청 - > @GetMapping public String list(Model model)
        return "redirect:/students";
    }
    /* 아래와 같이 짤 경우 -> 새로고침 할때 마다 추가된다.
    @PostMapping
    public String create(
            @ModelAttribute RequestStudent request,
            Model model
    ) {
        studentService.save(request);

        model.addAttribute(
                "students",
                studentService.findall()
        );

        return "students/list";
    }
     */


}
