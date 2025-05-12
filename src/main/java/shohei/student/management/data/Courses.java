package shohei.student.management.data;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Courses {

  private String courseId;
  private String studentId;
  private String courseName;
  private LocalDate whenStart;
  private LocalDate whenComplete;

  public Courses(String studentId) {
    this.studentId = studentId;
  }

  public Courses() {

  }


}
