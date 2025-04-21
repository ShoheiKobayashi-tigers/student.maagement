package shohei.student.management.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shohei.student.management.controller.converter.StudentConverter;
import shohei.student.management.data.Courses;
import shohei.student.management.data.Student;
import shohei.student.management.domain.StudentDetail;
import shohei.student.management.service.StudentService;

@RestController
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/studentList")
  public List<StudentDetail> getstudentList() {
    List<Student> students = service.searchStudentsList();
    List<Courses> courses = service.searchStudentsCourseList();
    return converter.convertStudentDetails(students, courses);
  }


  @GetMapping("/courseList")
  public List<Courses> getCourseList() {
    return service.searchStudentsCourseList();
  }


}
