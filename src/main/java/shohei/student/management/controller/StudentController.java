package shohei.student.management.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shohei.student.management.controller.converter.StudentConverter;
import shohei.student.management.data.Courses;
import shohei.student.management.data.Student;
import shohei.student.management.form.UpdateStudentForm;
import shohei.student.management.repository.StudentRepository;
import shohei.student.management.service.StudentService;

@Controller
public class StudentController {

  @Autowired
  private StudentService service;
  @Autowired
  private StudentConverter converter;
  @Autowired
  private StudentRepository repository;


  //受講生情報を表示
  @GetMapping("/studentList")
  public String getStudentList(Model model) {
    service.updateAllStudentAges();
    List<shohei.student.management.data.Student> students = service.searchStudentsList();
    List<Courses> courses = service.searchStudentsCourseList();
    model.addAttribute("studentList", converter.convertStudentDetails(students, courses));
    model.addAttribute("courseList", courses);

    return "studentList";
  }

  //受講生に紐づくコース情報を表示
  @GetMapping("/courseList")
  public String getCourseListByStudentId(@RequestParam("studentId") String studentId, Model model) {
    List<Courses> courses = service.searchCoursesByStudentId(studentId);
    model.addAttribute("courseList", courses);
    model.addAttribute("studentId", studentId);
    return "courseList";
  }

  //全受講コース情報一覧を表示
  @GetMapping("/allCourseList")
  public String getCourseList(Model model) {
    List<Courses> allCourses = service.searchStudentsCourseList();
    model.addAttribute("courseList", allCourses);
    return "courseList";
  }

  //受講生情報を登録したのち、コース情報登録画面に遷移する。
  @GetMapping("/newStudent")
  public String newStudent(Model model) {
    model.addAttribute("student", new Student());
    List<String> prefectures = service.getPrefectureList();
    model.addAttribute("prefectures", prefectures);
    return "registerStudent";
  }

  @PostMapping("/registerStudent")
  public String registerStudent(@ModelAttribute Student student, BindingResult result) {
    if (result.hasErrors()) {
      return "registerStudent";
    }
    service.postStudent(student);
    String registeredStudentId = student.getId();
    return "redirect:/newCourse?studentId=" + registeredStudentId;
  }


  //受講生情報を新規登録したときに、コース情報も登録する。
  //コース情報のみを後から登録することもできる。
  @GetMapping("/newCourse")
  public String newCourse(@RequestParam(value = "studentId", required = false) String studentId,
      Model model) {
    model.addAttribute("courses", new Courses(studentId));
    List<String> courseNameList = service.getFixedCourseNameList();
    model.addAttribute("courseList", courseNameList);
    return "registerCourse";
  }


  @PostMapping("/registerCourse")
  public String registerCourse(@ModelAttribute Courses courses,
      Model model, BindingResult result) {
    if (result.hasErrors()) {
      model.addAttribute("courseList", service.getFixedCourseNameList());
      return "registerCourse";
    }
    service.postCourses(courses);
    return "redirect:/studentList";
  }

  @GetMapping("/formUpdateStudent")
  public String formUpdateStudent(@RequestParam(value = "id", required = false) String id,
      Model model) {
    List<Student> studentInfo = service.searchStudentById(id);
    model.addAttribute("studentInfo", studentInfo);
    model.addAttribute("id", id);
    model.addAttribute("updateStudentForm", service.getUpdateStudentFormById(id));

    return "updateStudent";
  }

  @PatchMapping("/updateStudent")
  public String updateStudent(@ModelAttribute UpdateStudentForm updateStudentForm,
      BindingResult result) {
    if (result.hasErrors()) {
      return "updateStudent";
    }

    service.updateStudentData(updateStudentForm);

    return "redirect:/studentList";
  }

  @GetMapping("/confirmDelete")
  public String confirmDelete(@RequestParam(value = "id", required = false) String id,
      Model model) {
    List<Student> studentInfo = service.searchStudentById(id);
    model.addAttribute("studentInfo", studentInfo);
    model.addAttribute("id", id);
    return "deleteStudent";
  }

  @DeleteMapping("/deleteStudent")
  public String deleteStudent(@ModelAttribute Student student, BindingResult result) {
    if (result.hasErrors()) {
      return "deleteStudent";
    }

    repository.deleteStudent(student);

    return "redirect:/studentList";
  }


}
