package shohei.student.management;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class Application {

  @Autowired
  private StudentRepository repository;

  private String name = "Shohei Kobayashi";
  private String age = "25";
  private Map<String, String> student = new HashMap<>() {{
    put("Wakabayashi Masayasu", "46");
  }};


  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @GetMapping("/student")
  public String getstudentInfo(@RequestParam String name) {
    Student student = repository.searchByName(name);
    return student.getName() + " " + student.getAge() + "歳";
  }

  @GetMapping("/Map")
  public Map<String, String> getStudent() {
    return student;
  }

  @PostMapping("/student")
  public void registorStudent(String name, int age) {
    repository.registorStudent(name, age);
  }

  @PatchMapping("/student")
  public void updateStudentName(String name, int age) {
    repository.updateStudentName(name, age);
  }

  @DeleteMapping("/student")
  public void deleteStudent(String name) {
    repository.deleteStudent(name);
  }

  @PostMapping("/Map")
  public void setStudent(String name, String age) {
    this.name = name;
    this.age = age;
    this.student.put(name, age);
  }

  @PostMapping("/searchMap")
  public Map<String, String> searchStudent(String age) {
    Map<String, String> result = new HashMap<>();
    this.age = age;
    for (Map.Entry<String, String> entry : this.student.entrySet()) {
      if (entry.getValue().equals(age)) {
        result.put(entry.getKey(), entry.getValue());
      }
    }
    return result;
  }


  @PostMapping("/remove")
  public void removeStudent(String name) {
    for (Map.Entry<String, String> entry : this.student.entrySet()) {
      if (entry.getKey().equals(name)) {
        this.student.remove(entry.getKey());
      }
    }
  }


}
