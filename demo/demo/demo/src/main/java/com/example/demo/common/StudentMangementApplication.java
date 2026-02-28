package com.example.demo.common;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "student")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentMangementApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false,length = 20)
    private String name;
    @Column(name = "gender",length = 10)
    private String gender;
    @Column(name = "age")
    private Integer age;
    @Column(name = "email",length = 100)
    private String email;
    @Column(name = "phone",length = 20)
    private String phone;
    @Column(name = "address",length = 200)
    private String address;

    @Column(name = "major",length = 50)
    private String major;
    @Enumerated(EnumType.STRING)
    @Column(name = "status",length = 20)
    private StudentStatus status = StudentStatus.ACTIVE;
    @Column(name = "enrollment_date")
    private LocalDate enrollmentDate;
    @Column(name = "create_at",updatable = false)
    private LocalDateTime createAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    //预持久化回调
    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    //学生状态枚举
    public enum StudentStatus {
        ACTIVE("在读"),
        GRADUATED("毕业"),
        DROPPED_OUT("退学"),
        SUSPENDED("休学");

        private final String description;
        StudentStatus(String description) {
            this.description = description;
        }
        public String getDescription() {
            return description;
        }
    }

}
