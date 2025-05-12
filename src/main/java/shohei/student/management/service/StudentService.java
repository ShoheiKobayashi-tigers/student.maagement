package shohei.student.management.service;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shohei.student.management.data.Courses;
import shohei.student.management.data.Student;
import shohei.student.management.repository.StudentRepository;


@Service
public class StudentService {

  private StudentRepository repository;
//  private StudentsCoursesRepository studentsCoursesRepository;


  @Autowired
  public StudentService(StudentRepository repository/*,
      StudentsCoursesRepository studentsCoursesRepository*/) {
    this.repository = repository;
//    this.studentsCoursesRepository = studentsCoursesRepository;

  }


  public List<Student> searchStudentsList() {
    return repository.search();
  }

  public List<Courses> searchStudentsCourseList() {
    return repository.searchCourses();
  }

  @Transactional
  public void postStudent(Student student) {
    student.setAge(calculateAge(student.getBirthday()));
    student.setId(UUID.randomUUID().toString());
    repository.registerStudent(student);
  }


  public List<String> getFixedCourseNameList() {
    return Arrays.asList(
        "Java",
        "AWS",
        "Webデザイン",
        "Webマーケティング",
        "フロントエンド",
        "WordPress副業",
        "AI"
    );
  }


  @Transactional
  public List<Student> searchStudentById(String id) {
    return repository.findStudent(id);
  }

  @Transactional
  public void postCourses(Courses courses) {
    courses.setCourseId(generateCourseCode(courses.getCourseName()));
    repository.registerCourse(courses);
  }

  public String generateCourseCode(String courseName) {
    String code = switch (courseName) {
      case "Java" -> "J";
      case "AWS" -> "AW";
      case "Webデザイン" -> "D";
      case "Webマーケティング" -> "M";
      case "フロントエンド" -> "F";
      case "WordPress副業" -> "WP";
      case "AI" -> "AI";
      default -> courseName.substring(0, 1).toUpperCase();
    };

    String currentYear = String.valueOf(Year.now().getValue());
    String categoryName = code + "-" + currentYear;
    int sequence = 1;
    String newCourseId = categoryName + "-" + String.format("%03d", sequence);

    while (repository.checkCourseId(newCourseId)) {
      sequence++;
      newCourseId = categoryName + "-" + String.format("%03d", sequence);
    }

    return newCourseId;
  }

  @Transactional
  public List<Courses> searchCoursesByStudentId(String studentId) {
    return repository.findCourses(studentId);
  }

  public List<String> getPrefectureList() {
    return Arrays.asList(
        "北海道", "青森", "岩手", "宮城", "秋田", "山形", "福島",
        "茨城", "栃木", "群馬", "埼玉", "千葉", "東京", "神奈川",
        "新潟", "富山", "石川", "福井", "山梨", "長野", "岐阜",
        "静岡", "愛知", "三重", "滋賀", "京都", "大阪", "兵庫",
        "奈良", "和歌山", "鳥取", "島根", "岡山", "広島", "山口",
        "徳島", "香川", "愛媛", "高知", "福岡", "佐賀", "長崎",
        "熊本", "大分", "宮崎", "鹿児島", "沖縄", "その他(海外等)"
    );
  }

  public int calculateAge(LocalDate birthday) {
    if (birthday != null) {
      LocalDate now = LocalDate.now();
      return (int) ChronoUnit.YEARS.between(birthday, now);
    }
    return 0;
  }

  @Transactional
  public void updateAllStudentAges() {
    List<Student> allStudents = repository.findAll();
    for (Student student : allStudents) {
      if (student.getBirthday() != null) {
        student.setAge(calculateAge(student.getBirthday()));
        repository.save(student);
      }
    }
    System.out.println("Application startup: Student ages updated.");
  }

//  @Transactional
//  public void migrateStudentIdsToUuid() {
//    List<Student> allStudents = repository.findAll();
//    Map<String, String> oldIdToNewUuid = new HashMap<>();
//
//    for (Student student : allStudents) {
//      String oldId = student.getId();
//      String newUuid = UUID.randomUUID().toString();
//      student.setId(newUuid);
//      oldIdToNewUuid.put(oldId, newUuid);
//      repository.updateId(newUuid, oldId);
//    }
//
//    List<Courses> allCourses = studentsCoursesRepository.findAll();
//    for (Courses course : allCourses) {
//      String oldStudentId = course.getStudentId();
//      if (oldIdToNewUuid.containsKey(oldStudentId)) {
//        course.setStudentId(oldIdToNewUuid.get(oldStudentId));
//        studentsCoursesRepository.updateStudentId(course.getStudentId(),
//            oldStudentId);
//      }
//    }
//  }

//  @Transactional
//  public void updateAllCourseIds() {
//    for (Courses course : repository.findAllCourses()) {
//      repository.updateCourse(generateCourseCode(course.getCourseName()), course.getCourseId());
//    }
//  }


}
