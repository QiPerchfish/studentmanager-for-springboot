package com.example.demo.common.Repository;

import com.example.demo.common.*;
import com.example.demo.entity.ScoreRepositpry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.math.BigDecimal;

@Repository
public interface ScoreRepository extends JpaRepository<ScoreRepositpry,Long> {
    //==========基础查询==========
    /**
     * 根据学生ID查询
     */
    List<ScoreRepositpry> findByStudentId (Long studentId);
    List<ScoreRepositpry> findByCourseId (Long courseId);
    List<ScoreRepositpry> findByExamType (String examType);
    /**
     * 1.用JOIN链接Score和Student
     * 2.计算平均值
     */
    @Query("SELECT AVG(s.scoreValue) FROM ScoreRepositpry s " +
            "WHERE s.studentId IN (SELECT stu.id FROM Student stu WHERE stu.className = :className) " +
            "AND s.courseId = :courseId " +
            "AND s.scoreValue IS NOT NULL")
    Double getAverageScoreByClassAndCourse(
            @Param("className") String className,
            @Param("courseId") Long courseId
    );

}
