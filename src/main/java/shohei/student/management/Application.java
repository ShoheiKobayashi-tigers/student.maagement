package shohei.student.management;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class Application {

  private String name = "Shohei Kobayashi";
  private String age = "25";
  private Map<String, String> student = new HashMap<>() {{
    put("Wakabayashi Masayasu", "46");
  }};


  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @GetMapping("/studentInfo")
  public String getstudentInfo() {
    return name + " " + age + "歳";
  }

  @GetMapping("/Map")
  public Map<String, String> getStudent() {
    return student;
  }

  @PostMapping("/studentInfo")
  public void setstudentInfo(String name, String age) {
    this.name = name;
    this.age = age;
  }

  @PostMapping("/studentName")
  public void updateStudentName(String name) {
    this.name = name;
  }

  @PostMapping("/Map")
  public void setStudent(String name, String age) {
    this.name = name;
    this.age = age;
    this.student.put(name, age);
  }


}
