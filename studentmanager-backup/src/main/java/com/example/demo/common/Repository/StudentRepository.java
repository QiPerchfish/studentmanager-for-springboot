package com.example.demo.common.Repository;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


/**
 * Student_repository
 * ------
 * 学生数据访问接口
 * END
 */
@org.springframework.stereotype.Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    //_________________________

    List<Student> findByName(String name);

    List<Student> findByClassName(String className);

    List<Student> findByAge(Integer age);

    Boolean existsByStudentNo(String studentNo);

    Boolean existsByName(String name);

    Boolean existsByClassName(String className);

    void deleteAllByName(String name);

    List<Student> findByDeletedFalse();
}
