package shohei.student.management.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shohei.student.management.controller.converter.StudentConverter;
import shohei.student.management.data.Courses;
import shohei.student.management.data.Student;
import shohei.student.management.domain.StudentDetail;
import shohei.student.management.repository.StudentRepository;
import shohei.student.management.service.StudentService;

@Controller
public class StudentController {

  @Autowired
  private StudentService service;
  @Autowired
  private StudentConverter converter;
  @Autowired
  private StudentRepository repository;


  //受講生情報を表示
  @GetMapping("/studentList")
  public String getStudentList(Model model) {
    List<Student> students = service.searchStudentsList();
    List<Courses> courses = service.searchStudentsCourseList();
    model.addAttribute("studentList", converter.convertStudentDetails(students, courses));
    model.addAttribute("courseList", courses);

    return "studentList";
  }

  //コース情報を表示
  @GetMapping("/courseList")
  public String getCourseListByStudentId(@RequestParam("studentId") String studentId, Model model) {
    List<Courses> courses = service.searchCoursesByStudentId(studentId);
    model.addAttribute("courseList", courses);
    model.addAttribute("studentId", studentId);
    return "courseList";
  }

  //受講生情報を登録したのち、コース情報を登録する。
  @GetMapping("/newStudent")
  public String newStudent(Model model) {
    model.addAttribute("studentDetail", new StudentDetail());
    return "registerStudent";
  }

  @PostMapping("/registerStudent")
  public String registerStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result) {
    if (result.hasErrors()) {
      return "registerStudent";
    }

    repository.registerStudent(studentDetail.getStudent());
    String registeredStudentId = studentDetail.getStudent().getId();
    return "redirect:/newCourse?studentId=" + registeredStudentId;
  }


  @GetMapping("/newCourse")
  public String newCourse(@RequestParam(value = "studentId", required = false) String studentId,
      Model model) {
    Courses courses = new Courses(studentId);
    model.addAttribute("courses", courses);
    return "registerCourse";
  }


  @PostMapping("/registerCourse")
  public String registerCourse(@ModelAttribute Courses courses, BindingResult result) {
    if (result.hasErrors()) {
      return "registerCourse";
    }

    repository.registerCourse(courses.getCourseId(), courses.getStudentId(),
        courses.getCourseName(), courses.getWhenStart(), courses.getWhenComplete());
    return "redirect:/studentList";

  }


}
