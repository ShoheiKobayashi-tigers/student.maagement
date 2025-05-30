package shohei.student.management.controller.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import shohei.student.management.data.Courses;
import shohei.student.management.data.Student;
import shohei.student.management.domain.StudentDetail;

@Component
public class StudentConverter {

  public List<StudentDetail> convertStudentDetails(List<Student> students,
      List<Courses> courses) {
    List<StudentDetail> studentDetails = new ArrayList<>();
    for (Student student : students) {
      StudentDetail studentDetail = new StudentDetail();
      studentDetail.setStudent(student);
      List<Courses> list = courses.stream()
          .filter(course -> student.getId().equals(course.getStudentId()))
          .collect(Collectors.toList());
      List<Courses> convertCourses = list;
      studentDetail.setStudentCourses(convertCourses);
      studentDetails.add(studentDetail);
    }
    return studentDetails;
  }

}
