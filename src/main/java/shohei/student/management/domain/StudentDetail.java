package shohei.student.management.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import shohei.student.management.data.Courses;
import shohei.student.management.data.Student;

@Getter
@Setter
public class StudentDetail {

  private Student student;
  private List<Courses> studentCourses;
  private Courses courses;

  public StudentDetail(Student student, Courses courses) {
    this.student = student;
    this.courses = courses;
  }
  

  public StudentDetail() {
    this(new Student(), new Courses());
  }
}