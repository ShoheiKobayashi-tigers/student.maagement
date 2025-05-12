package shohei.student.management.repository;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

  @Select("SELECT * FROM students WHERE is_deleted=false")
  List<Student> search();

  @Select("SELECT * FROM students_courses WHERE student_id IN (SELECT id FROM students WHERE is_deleted=0)")
  List<Courses> searchCourses();

  @Select("SELECT * FROM students WHERE id = #{id}")
  List<Student> findStudent(@Param("id") String id);

  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<Courses> findCourses(@Param("studentId") String studentId);

  @Select("SELECT * FROM students")
  List<Student> findAll();

  @Select("SELECT EXISTS(SELECT * FROM students_courses WHERE course_id=#{newCourseId})")
  boolean checkCourseId(@Param("newCourseId") String newCourseId);

  @Insert("INSERT INTO students (id, name, furigana, nickname, mail, city, birthday,age,gender, remark) VALUES(#{id}, #{name}, #{furigana}, #{nickname}, #{mail}, #{city},#{birthday},#{age} ,#{gender}, #{remark})")
  void registerStudent(Student student);

  @Insert("INSERT INTO  students_courses VALUES(#{courseId}, #{studentId}, #{courseName}, #{whenStart}, #{whenComplete})")
  void registerCourse(Courses courses);

  @Update("<script>"
      + "UPDATE students "
      + "<set>"
      + "  <if test='name != null and name != \"\"'>name = #{name},</if>"
      + "  <if test='furigana != null and furigana != \"\"'>furigana = #{furigana},</if>"
      + "  <if test='nickname != null and nickname != \"\"'>nickname = #{nickname},</if>"
      + "  <if test='mail != null and mail != \"\"'>mail = #{mail},</if>"
      + "  <if test='remark != null'>remark = #{remark},</if>"
      + "</set>"
      + "WHERE id = #{id}"
      + "</script>")
  void updateStudent(Student student);

  @Update("UPDATE students SET age = #{age} WHERE id = #{id}")
  void save(Student student);

//  @Update("UPDATE students SET id = #{newId} WHERE id = #{oldId}")
//  void updateId(@Param("newId") String newId, @Param("oldId") String oldId);

  @Delete("UPDATE students SET is_deleted=1 WHERE id=#{id}")
  void deleteStudent(Student student);

//  @Select("SELECT * FROM students_courses")
//  List<Courses> findAllCourses();
//
//  @Update("UPDATE students_courses SET course_id = #{newCourseId} WHERE course_id = #{oldCourseId}")
//  void updateCourse(@Param("newCourseId") String newCourseId,
//      @Param("oldCourseId") String oldCourseId);

}
