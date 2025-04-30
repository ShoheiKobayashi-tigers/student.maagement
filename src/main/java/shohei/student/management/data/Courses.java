package shohei.student.management.data;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Courses {

  private String courseId;
  private String studentId;
  private String courseName;
  private LocalDateTime whenStart;
  private LocalDateTime whenComplete;

  public Courses(String studentId) {
    this.studentId = studentId;
  }

  public Courses() {

  }


}
