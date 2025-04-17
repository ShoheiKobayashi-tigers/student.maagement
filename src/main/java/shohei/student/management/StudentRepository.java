package shohei.student.management;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM student WHERE name= #{name}")
  Student searchByName(String name);

  @Select("SELECT * FROM student")
  List<Student> allName();

  @Insert("INSERT student values(#{name},#{age})")
  void registorStudent(String name, int age);

  @Update("UPDATE student SET age=#{age} WHERE name=#{name}")
  void updateStudentName(String name, int age);

  @Delete("DELETE FROM student WHERE name=#{name}")
  void deleteStudent(String name);

}
