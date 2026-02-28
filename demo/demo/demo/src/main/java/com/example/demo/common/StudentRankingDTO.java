package com.example.demo.common;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRankingDTO {
    private Long studentId;
    private String studentName;
    private String studentNo;
    private String className;

    private BigDecimal studentScore;
    private Integer rank;
    private Integer totals;

    private BigDecimal classesAverageScore;
    private BigDecimal different;
    private String grade;
    private String performance;
    /**
     * 计算描述表现
     */
    public String getPerformance() {
        if (studentScore == null) {
            return "缺考";
        }
        double value = studentScore.doubleValue();
        if (value >= 90) {
            return "优秀";
        } if (value >=80) {
            return "良好";
        } if (value >= 70) {
            return "中等";
        } if (value >= 60) {
            return "及格";
        }
        return "不及格";
    }
    public String getGrade() {
        if (studentScore == null) {
            return "未评分";
        }
        double value = studentScore.doubleValue();
        if (value >= 90) {
            return "A";
        } if (value >= 80) {
            return "B";
        } if (value >= 70) {
            return "C";
        } if (value >= 60) {
            return "D";
        }
        return "F";
    }
    /**
     * 从数据库结果查询
     */
    public static StudentRankingDTO fromResult(Object[] result,int rank,int totals) {
        return StudentRankingDTO.builder()
                .studentId(((Number) result[0]).longValue())
                .className((String) result[1])
                .studentNo((String) result[2])
                .studentScore((BigDecimal) result[3])
                .studentName((String) result[4])
                .rank(rank)
                .totals(totals)
                .build();
    }
}
