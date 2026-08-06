package com.example.demo.webservice;

import com.example.demo.common.DTO.StudentRequest;
import com.example.demo.common.DTO.StudentResponse;
import com.example.demo.common.Repository.StudentRepository;
import com.example.demo.common.Repository.StudentService;
import com.example.demo.common.exception.BusinssException;
import com.example.demo.common.exception.StudentNoAlreadyExistsException;
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
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentResponse updateStudentsInfo(Long id, StudentRequest request) {
        Student student  = studentRepository.findById(id)
                .orElseThrow(() -> new BusinssException("学生不存在, ID = " + id));

        student.setName(request.getName());
        student.setStudentNo(request.getNumber());
        student.setAge(request.getAge());

        Student updatedStudent = studentRepository.save(student);

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(updatedStudent.getId());
        studentResponse.setName(updatedStudent.getName());
        studentResponse.setAge(updatedStudent.getAge());
        studentResponse.setNumber(updatedStudent.getStudentNo());

        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new BusinssException("学生姓名不能为空");
        }
        return studentResponse;
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

    public StudentResponse saveStudent(StudentRequest request) {
        Student student = new Student();

        student.setName(request.getName());
        student.setAge(request.getAge());
        student.setStudentNo(request.getNumber());

        Student saved = studentRepository.save(student);

        StudentResponse response = new StudentResponse();
        response.setId(saved.getId());
        response.setNumber(saved.getStudentNo());
        response.setName(saved.getName());
        response.setAge(saved.getAge());

        return response;
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

    @Override
    public StudentResponse addStudent(StudentRequest request) {
        if (studentRepository.existsByStudentNo(request.getNumber())) {
            throw new StudentNoAlreadyExistsException(request.getNumber());
        }
        return saveStudent(request);
    }

    /**
     * 分页查询的方法
     */
    public Page<StudentResponse> listStudents(Pageable pageable) {
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage.map(this::convertToResponse);
    }

    @Override
    public StudentResponse getStudentById(Long id) {
        return null;
    }

    @Override
    public StudentResponse updateStudentInfo(Long id, StudentRequest request) {
        return null;
    }

    private StudentResponse convertToResponse(Student student) {
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setName(student.getName());
        response.setAge(student.getAge());
        response.setNumber(student.getStudentNo());
        return response;
    }

}
