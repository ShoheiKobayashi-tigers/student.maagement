package shohei.student.management.form;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStudentForm {

  private String id;
  private String name;
  private String furigana;
  private String nickname;
  private String mail;
  private String remark;

  public UpdateStudentForm() {

  }

  public UpdateStudentForm(Map<String, String> findUpdateStudentData) {
    this.id = findUpdateStudentData.get("id");
    this.name = findUpdateStudentData.get("name");
    this.furigana = findUpdateStudentData.get("furigana");
    this.nickname = findUpdateStudentData.get("nickname");
    this.mail = findUpdateStudentData.get("mail");
    this.remark = findUpdateStudentData.get("remark");


  }

}
