package shohei.student.management.data;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

  private String id;
  private String name;
  private String furigana;
  private String nickname;
  private String mail;
  private String city;
  private LocalDateTime birthday;
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
