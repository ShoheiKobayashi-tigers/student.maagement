//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//import shohei.student.management.service.StudentService;
//
//@Component
//public class StartupAgeUpdater implements ApplicationRunner {
//
//  private final StudentService studentService;
//
//  public StartupAgeUpdater(StudentService studentService) {
//    this.studentService = studentService;
//  }
//
//  @Override
//  public void run(org.springframework.boot.ApplicationArguments args) throws Exception {
//    studentService.updateAllStudentAgesOnStartup();
//    System.out.println("Application startup: Student ages updated.");
//  }
//}