package shohei.student.management.data;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class Student {

  private String id;
  private String name;
  private String furigana;
  private String nickname;
  private String mail;
  private String city;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthday;
  private int age;
  private String gender;
  private String remark;
  private boolean isDeleted;


  public Student(String id) {
    this.id = id;
  }

  public Student() {

  }
}
