package com.example.demo.common.Repository;

import com.example.demo.common.StudentRankingDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
public interface DataStatisticsController {
    BigDecimal getClassAverageScore(String className,Long courseId);
    Map<String,Integer> getScoreDistribution(String className,Long courseId);
    List<StudentRankingDTO> getClassDistribution(Long courseId);
    //学生统计接口
    Integer getStudentCountByClass(String className);
    Map<String,Integer> getGenderDistribution(String className);
    Double getAverageAgeByClass(String className);
}
