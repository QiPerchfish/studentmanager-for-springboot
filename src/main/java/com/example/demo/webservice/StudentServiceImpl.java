package com.example.demo.webservice;

import com.example.demo.common.Repository.StudentRepository;
import com.example.demo.common.Repository.StudentService;
import com.example.demo.common.StudentRankingDTO;
import com.example.demo.common.exception.BusinssException;
import com.example.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
//@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student updateStudentsInfo(Long id, StudentRankingDTO dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new BusinssException("学生不存在, ID = " + id));

        student.setName(dto.getStudentName());
        student.setStudentNo(dto.getStudentNo());

        if (dto.getStudentName() == null || dto.getStudentName().trim().isEmpty()) {
            throw new BusinssException("学生姓名不能为空");
        }
        return studentRepository.save(student);
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
    @Transactional
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                        .orElseThrow(() -> new BusinssException("学生不存在, ID = " + id));
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
    /**
     * 分页查询的方法
     */
    @Override
    public Page<StudentRankingDTO> listStudents(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> studentPage = studentRepository.findAll(pageable);

        return studentPage.map(this::convertToDTO);
    }

    private StudentRankingDTO convertToDTO(Student student) {
        StudentRankingDTO dto = new StudentRankingDTO();
        dto.setStudentName(student.getName());
        dto.setStudentId(student.getId());
        dto.setStudentNo(student.getStudentNo());
        dto.setStudentAge(Integer.valueOf(String.valueOf(student.getAge())));

        // 返回DTO结果
        return dto;
    }

}
