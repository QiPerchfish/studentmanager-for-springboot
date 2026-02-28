package com.example.demo.webservice;

import com.example.demo.common.Repository.StudentRepository;
import com.example.demo.common.Repository.StudentService;
import com.example.demo.entity.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
//@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAllStudents() {
        return List.of();
    }

    @Override
    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }
    @Override
    public Optional<Student> findStudentById(Long id) {
        return Optional.ofNullable(studentRepository.findById(id).orElse(null));
    }
    @Override
    public Student saveStudent(Student student) {
        if (student.getId() == null && studentRepository.existsByStudentNo(student.getStudentNo())) {
            throw new RuntimeException("学号已存在"+student.getStudentNo());
        }
        return studentRepository.save(student);
    }
    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
    @Override
    public List<Student> findStudentByClass(String className) {
        return studentRepository.findByClassName(className);
    }
    @Override
    public List<Student> searchStudent(String name) {
        return studentRepository.findByName(name);
    }

    @Override
    public List<Student> findTopStudents(Integer minScore) {
        return List.of();
    }

    @Override
    public boolean isStudentNoExtists(String studentNo) {
        return false;
    }

    @Override
    public long countStudentsByClass(String className) {
        return 0;
    }

    @Override
    public Double getAverageScoreByClass(String className) {
        return 0.0;
    }

    @Override
    public List<Student> searchStudentByName(String name) {
        return List.of();
    }

}
