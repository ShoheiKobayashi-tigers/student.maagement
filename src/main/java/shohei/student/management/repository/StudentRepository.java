package shohei.student.management.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import shohei.student.management.data.Courses;
import shohei.student.management.data.Student;

/**
 * 受講生情報を扱うリポジトリ。
 * <p>
 * 全件検索や単一条件での検索、コース情報の検索が行えるクラスです。
 */

@Mapper
public interface StudentRepository {

  /**
   * 全件検索をします。
   *
   * @return 全件検索した受講生情報の一覧。
   */

  @Select("SELECT * FROM students")
  List<Student> search();

  @Select("SELECT * FROM students_courses")
  List<Courses> searchCourses();

  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<Courses> findCourses(@Param("studentId") String studentId);


  @Insert("INSERT INTO students (id, name, furigana, nickname, mail, city, age, gender, remark) VALUES(#{id}, #{name}, #{furigana}, #{nickname}, #{mail}, #{city}, #{age}, #{gender}, #{remark})")
  void registerStudent(Student student);

  @Insert("INSERT INTO  students_courses VALUES(#{courseId}, #{studentId}, #{courseName}, #{whenStart}, #{whenComplete})")
  void registerCourse(String courseId, String studentId, String courseName, LocalDateTime whenStart,
      LocalDateTime whenComplete);


}
