package shohei.student.management;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shohei.student.management.data.Courses;
import shohei.student.management.data.Student;
import shohei.student.management.repository.StudentRepository;

@SpringBootApplication
@RestController

public class Application {

  @Autowired
  private StudentRepository repository;


  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @GetMapping("/studentList")
  public List<Student> getstudentList() {
    return repository.search();
  }

  @GetMapping("/courseList")
  public List<Courses> getCourseList() {
    return repository.searchCourses();
  }


}
