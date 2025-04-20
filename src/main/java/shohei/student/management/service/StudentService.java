package shohei.student.management.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shohei.student.management.data.Courses;
import shohei.student.management.data.Student;
import shohei.student.management.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;

  }

  public List<Student> searchStudentsList() {
    List<Student> allStudents = repository.search();

    return allStudents.stream().filter(student -> student.getAge() >= 30)
        .collect(Collectors.toList());
  }

  public List<Courses> searchStudentsCourseList() {
    List<Courses> allCoursesData = repository.searchCourses();
    return allCoursesData.stream()
        .filter(courses -> courses.getCourseName().equals("Java")).collect(Collectors.toList());
  }

}
