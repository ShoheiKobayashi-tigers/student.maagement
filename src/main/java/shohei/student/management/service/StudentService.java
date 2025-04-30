package shohei.student.management.service;

import java.util.List;
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
    return repository.search();
  }

  public List<Courses> searchStudentsCourseList() {
    return repository.searchCourses();
  }

  public List<Courses> searchCoursesByStudentId(String studentId) {
    return repository.findCourses(studentId);
  }


}
