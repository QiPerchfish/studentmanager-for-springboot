package com.example.demo.common;
import java.math.BigDecimal;
import jakarta.validation.*;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRankingDTO {
    @NotNull(message = "ID不能为空")
    private Long studentId;
    @NotBlank(message = "学生姓名不能为空")
    @Size(min = 2, max = 20, message = "长度不得超过20，也不能小于2")
    private String studentName;
    @NotBlank(message = "学号不能为0")
    @Size(min = 1, max = 10, message = "学号必须在1-10之间")
    private String studentNo;
    private String className;
    @Min(value = 1, message = "必须大于5")
    @Max(value = 35, message = "不能超过35")
    private Integer studentAge;
    @DecimalMin(value = "0.0", message = "分数必须大于0")
    @DecimalMax(value = "100.0", message = "分数不能超过100")
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
                .studentAge((String) result[5])
                .rank(rank)
                .totals(totals)
                .build();
    }
}
