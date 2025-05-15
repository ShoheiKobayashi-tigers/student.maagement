package shohei.student.management.repository;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import shohei.student.management.data.Courses;
import shohei.student.management.data.Student;
import shohei.student.management.form.UpdateStudentForm;

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
  List<Student> findAll();

  @Select("SELECT * FROM students WHERE is_deleted=false")
  List<Student> search();

  @Select("SELECT * FROM students_courses WHERE student_id IN (SELECT id FROM students WHERE is_deleted=0)")
  List<Courses> searchCourses();

  @Select("SELECT * FROM students WHERE id = #{id}")
  List<Student> findStudent(@Param("id") String id);


  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<Courses> findCourses(@Param("studentId") String studentId);

  @Select("SELECT id, name, furigana, nickname, mail, remark FROM students WHERE id = #{id}")
  Map<String, String> findUpdateStudentData(@Param("id") String id);

  @Select("SELECT EXISTS(SELECT * FROM students_courses WHERE course_id=#{newCourseId})")
  boolean checkCourseId(@Param("newCourseId") String newCourseId);

  @Insert("INSERT INTO students (id, name, furigana, nickname, mail, city, birthday,age,gender, remark) VALUES(#{id}, #{name}, #{furigana}, #{nickname}, #{mail}, #{city},#{birthday},#{age} ,#{gender}, #{remark})")
  void registerStudent(Student student);

  @Insert("INSERT INTO  students_courses VALUES(#{courseId}, #{studentId}, #{courseName}, #{whenStart}, #{whenComplete})")
  void registerCourse(Courses courses);

  @Update("UPDATE students SET name = #{name}, furigana = #{furigana}, nickname = #{nickname}, mail = #{mail}, remark = #{remark} WHERE id = #{id}")
  void updateStudent(UpdateStudentForm updateStudentForm);

  @Update("UPDATE students SET age = #{age} WHERE id = #{id}")
  void save(Student student);


  @Delete("UPDATE students SET is_deleted=1 WHERE id=#{id}")
  void deleteStudent(Student student);


}
