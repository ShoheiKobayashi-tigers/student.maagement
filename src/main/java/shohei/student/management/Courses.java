package shohei.student.management;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Courses {

  private String course_id;
  private String student_id;
  private String course_name;
  private LocalDateTime when_start;
  private LocalDateTime when_complete;


}
