package shohei.student.management.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import shohei.student.management.data.Courses;

@Getter
@Setter
public class StudentDetail {

  private shohei.student.management.data.Student student;
  private List<Courses> studentCourses;
  private Courses courses;

  public StudentDetail(shohei.student.management.data.Student student, Courses courses) {
    this.student = student;
    this.courses = courses;
  }

  public StudentDetail() {

  }


}