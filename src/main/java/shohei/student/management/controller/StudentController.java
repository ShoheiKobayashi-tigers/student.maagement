package shohei.student.management.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shohei.student.management.controller.converter.StudentConverter;
import shohei.student.management.data.Courses;
import shohei.student.management.data.Student;
import shohei.student.management.service.StudentService;

@Controller
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/studentList")
  public String getstudentList(Model model) {
    List<Student> students = service.searchStudentsList();
    List<Courses> courses = service.searchStudentsCourseList();
    model.addAttribute("studentList", converter.convertStudentDetails(students, courses));
    return "studentList";
  }


  @GetMapping("/courseList")
  public List<Courses> getCourseList() {
    return service.searchStudentsCourseList();
  }


}
