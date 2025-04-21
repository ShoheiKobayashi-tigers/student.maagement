package shohei.student.management.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shohei.student.management.data.Courses;
import shohei.student.management.data.Student;
import shohei.student.management.service.StudentService;

@RestController
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  @GetMapping("/studentList")
  public List<Student> getstudentList() {
    return service.searchStudentsList();
  }

  @GetMapping("/courseList")
  public List<Courses> getCourseList() {
    return service.searchStudentsCourseList();
  }


}
