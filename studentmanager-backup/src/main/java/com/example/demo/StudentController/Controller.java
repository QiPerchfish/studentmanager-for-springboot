package com.example.demo.StudentController;

import com.example.demo.common.Repository.StudentService;
import com.example.demo.common.Result;
import com.example.demo.common.StudentRankingDTO;
import com.example.demo.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * --关于此版本
 * 此版本为测试版Beta,该系统完全免费，不会出现相关收费功能
 * CopyRight（c）该系统开发团队
 * 采用MIT许可证授权
 * 你可以自由的修改，无需额外费用
 * 但需保留原版权声明和许可证声明
 */
@RestController
@RequestMapping("/api/students")
public class Controller {

    private final StudentService studentService;

    public Controller(StudentService studentService) {
        this.studentService = studentService;
    }
    /*
    查询所有学生
     */
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/test")
    public String test() {
        return "testing";
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentService.findStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    /**
     * 新增学生
     * api/students
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }
    /**
     * 更新信息
     * /api/student/{id}
     */
    @PutMapping("/{id}")
    public Result<Student> updateStudentsInfo(@PathVariable Long id, @RequestBody Student student) {
        return Result.success(studentService.updateStudentsInfo(id, StudentRankingDTO.builder().build()));
    }
    @DeleteMapping("/{id}")
    public Result<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return Result.success(null);
    }
    //===================查询接口====================
    /**
     * 查询学生
     */
    @GetMapping("/class/{className}")
    public List<Student> getStudenyByClass(@PathVariable String className) {
        return studentService.findStudentByClass(className);
    }
    @GetMapping("/search")
    public List<Student> searchStudent(@RequestParam(required = false) String name) {
        if (name == null || name.trim().isEmpty()) {
            return List.of();
        }
        return studentService.searchStudent(name);
    }
    @GetMapping("/top")
    public List<Student> getTopStudent(@RequestParam(required = false) Integer minScore) {
        return studentService.findTopStudents(minScore);
    }
    @GetMapping("/Count")
    public long countStudentByClass(@RequestParam String className) {
        return studentService.countStudentsByClass(className);
    }
    @GetMapping("/average-score")
    public Double getAverageScore(@RequestParam String className) {
        return studentService.getAverageScoreByClass(className);
    }
}
