# 1. 프로젝트 소개

## 프로젝트 주제

학생 정보 관리 프로젝트

데이터
- id
- studentId
- name
- semester
- major
- rc

## GitHub Repository

https://github.com/2026-2-WebService/assign06-c01-22300253.git
https://github.com/Suho-22300253/2026Web_assign06.git


## Render 배포 URL

> https://two026web-assign06.onrender.com
> https://two026web-assign06.onrender.com/students

## 프로젝트 구조
```text
src
└── main
    ├── java
    │   └── org.example.webui
    │       ├── controller
    │       │   └── StudentController.java
    │       │
    │       ├── service
    │       │   └── StudentService.java
    │       │
    │       ├── repository
    │       │   ├── StudentRepository.java
    │       │   └── MemoryStudentRepository.java
    │       │
    │       ├── domain
    │       │   └── Student.java
    │       │
    │       └── dto
    │           ├── RequestStudent.java
    │           └── ResponseStudent.java
    │
    └── resources
        ├── templates
        │   └── students
        │       ├── list.html
        │       └── form.html
        │
        └── static
            └── css
                └── style.css
```

## 실행방법
1. Repository Clone

2. Spring Boot 실행

3. Browser 접속

### 주요 화면
![base browser.png](captures/base%20browser.png)

![add Browser.png](captures/add%20Browser.png)

![input data.png](captures/input%20data.png)

![after submit.png](captures/after%20submit.png)
### Web Endpoint

| HTTP Method | URL | 기능 |
|---|---|---|
| GET | `/students` | 전체 학생 목록 조회 |
| GET | `/students/new` | 학생 등록 Form 화면 |
| POST | `/students` | 새로운 학생 데이터 등록 |

# 2 이번 과제 키워드

- @Controller
> Spring MVC에서 Browser의 HTTP 요청을 처리하고 View를 반환하기 위해 사용하는 Annotation
- Thymeleaf
> Server Side Template Engine
> Model에 담아 전달한 데이터를 HTML과 결합하여 Browser에 전달할 최종 HTML을 생성
> HTML 파일의 구조를 깨지 않고 그대로 유지하면서 서버 데이터를 자연스럽게 결합
- Model
> Controller와 서버에서 사용한 데이터 객체를 view 화면에서 볼 수 있도록 담는 객체
- @ModelAttribute
> form에서 전달받은 데이터와 Java 객체의 필드를 연결 시켜 객체에 정보를 담음
- HTML Form
> Web 화면에서 데이터를 입력받고 서버에 전달하기 위해 사용하는 HTML 요소
- redirect:
> Browser에게 새로운 URL로 다시 요청하도록 지시할 때 사용
> students/list.html을 반환하는 것이 아닌 GET /students 를 요청 보냄
- PRG Pattern
> post -> redirect -> get 흐름 : post가 유지되는 것이 아닌 get으로 바꿔지도록
- Bootstrap
> CSS를 이용하 ui 디자인에 사용하는 framework
- Layered Architecture
> 역할에 따라 계층 구분하는 구조

# 3 핵심 내용 정리
## @Controller와 @RestController의 차이
RestController - HTTP 요쳥에 맞게 json 데이터 타입으로 반환시키는 역할

Controller - View 화면을 반환하여 HTML 화면 반환

## Controller에서 Model을 이용하여 데이터를 View로 전달하는 방법
HTML은 Controller 내부의 java 변수에 접근 불가이므로 Controller에서 View로 전달하고 싶은 데이터를 Model 객체로 등록

html 파일 내부 중 ${students}와 같이 model 에 등록한 이름을 통하여 html 데이터 접근

## Thymeleaf를 이용하여 Collection을 HTML에 출력하는 방법
th:each 와 th:text를 이용하여 List에 있는 모든 데이터 객체를 출력가능하다.

## HTML Form 데이터를 @ModelAttribute로 전달받는 방법
1. form 이 post 방식으로 데이터를 보냄
2. form의 action이 th:action="@{/students}" 와 같이 어디로 보내는지를 보냄
3. 해당 url의 @PostMapping 이 해당 요청에 응답한다.
4. @ModelAttribute 을 통해 서버내의 dto의 필드와 form의 name 파트의 데이터를 연결하여 저장한다.
5. requst 객체를 만들어 저장한다.

## redirect:와 PRG Pattern의 동작 방식
redirect 없이  student/list를 진행할 경우 마지막 요청이 post로 유지되어있음 / post 가 진행되었다고 GET이 자동으로 실행되는 것이 아니기 떄문

이때 새로 고침하게 될경우 post가 다시한번 요청되어 중복적으로 데이터가 보내지는 상황발생

그렇기에 redirect를 통해 Browser에게 /students로 다시 요청을 지시

browser는 get /students을 요청하여 controller 내부 getMapping을 통해 해당 요청받아 실행

= Post -> redirect -> Get 순으로 진행

# 4 Weekly Report
## Key Learning

### 1. `@Controller`와 Thymeleaf를 이용한 Web UI 구성

기존 REST API에서는 @RestController를 이용하여 JSON 데이터를 반환

Web UI 에서는 @Controller를 이용하여 HTML View를 반환

Controller에서 반환한 View 이름은 Thymeleaf를 통해 실제 HTML 파일과 연결하여 templates/students/list.html을 렌더링

이때 랜더링의 의미는 HTML 템플릿과 데이터를 합쳐서 브라우저에 보여줄 최종 HTML을 만드는 것이다.

#### 2. Model을 이용한 Controller와 View의 데이터 전달

Controller 내부의 Java 데이터를 HTML이 직접 사용할 수 없기 때문에 Model을 spring에서 제공하는 데이터 객체를 이용하여 데이터를 View에 전달

위 코드에서 key 값으로 Model에 등록하면, Thymeleaf에서는 다음과 같이 사용할 수 있습니다.

${key 값} 을 통해 html 파일에서 사용가능하다.

Model은 Controller와 View 사이에서 데이터를 연결하는 역할

#### 3. HTML Form과 `@ModelAttribute`, Redirect의 동작

HTML Form의 name과 DTO의 필드 이름이 같으면 Spring MVC가 Form 데이터를 Java 객체로 자동으로 연결하여 객체에 데이터를 담아준다.

### Web UI Flow

1. Browser
2. GET /students
3. StudentController
4. StudentService
5. StudentRepository
6. MemoryStudentRepository
7. List<ResponseStudent>
8. Model
9. Thymeleaf
10. list.html
11. Browser

### Form Flow

1. HTML Form
2. 사용자 데이터 입력
3. POST /students
4. StudentController의 PostMapping
5. @ModelAttribute을 통해 RequestStudent에 데이터 담음
7. StudentService
8. StudentRepository
9. MemoryStudentRepository
10. redirect:/students
11. GET /students 
12. 학생 목록 화면

### Problem & Solution

#### 문제
Post 에서 중복 저장을 확인해보기 위해 다음과 같이 시도

```java
@PostMapping
public String create(@ModelAttribute RequestStudent request) {
    studentService.save(request);
    return "students/list";
}
```

하지만 목록 화면에서 새로 등록한 데이터가 정상적으로 출력되지 않는 상황 발생

#### 원인

`return "students/list"`는 `GET /students` 요청을 다시 실행하는 것이 아니라  
현재 POST 요청에서 바로 `list.html`을 렌더링합니다.

따라서 `GET /students`에서 사용하는 addAttribute가 실행되지 않는 상황 발생


그 결과 `list.html`에서 사용하는 `${students}`가 Model에 존재하지 않는 문제가 발생했습니다.

또한 POST 요청 후 View를 직접 반환하면 Browser의 마지막 요청이 POST 상태로 남아 있기 때문에  
새로고침 시 동일한 데이터가 다시 등록될 가능성이 있다는 점도 확인

#### 해결

중복을 확인하기 위해서 `return "students/list"` 와 addAttribute를 추가해줌

정상 작동을 위해 return `"redirect:/students";` 진행


### Code Review


@PostMapping
public String create(@ModelAttribute RequestStudent request) {
    
studentService.save(request);

return "redirect:/students";
}

ModelAttribute 와 redirect를 통해 web ui 의 흐름 파악 가능하기 떄문이다.

### AI Usage

- list.html 과 form.html 화면 제작
- External CSS 작성
- Model 의 사용 이유와 작동 원리 파악

> 직접 확인한 부분
- th:href의 URL
- th:action의 URL
- HTTP Method
- Controller Mapping

### Reflection - 궁금한점
왜 PostMapping("/new") 가 안들어가는 지 그리고 이를 붙이는 기준이 궁금합니다.

### 건의사항
없습니다.