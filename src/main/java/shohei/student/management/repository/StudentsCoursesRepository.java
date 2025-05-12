//package shohei.student.management.repository;
//
//import java.util.List;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
//import shohei.student.management.data.Courses;
//
//@Mapper
//public interface StudentsCoursesRepository {
//
//  @Select("SELECT * FROM students_courses")
//  List<Courses> findAll();
//
//  @Update("UPDATE students_courses SET student_id = #{studentId} WHERE student_id = #{oldStudentId}")
//  void updateStudentId(@Param("studentId") String studentId,
//      @Param("oldStudentId") String oldStudentId);
//
//}