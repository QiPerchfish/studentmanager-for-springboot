# studentmanager-for-springboot
A concise, fast, and operational online Web backend system, built on Spring Boot and JPA, is now open source, but it is still in version 1.0

#---------------------

# 學生信息管理系統 🎓
#### 基於SpringBoot的WebOS
### 為了您更方便的管理課程，統計數據

## 項目簡介
這是一個轻量级的学生信息管理后端系统，提供 RESTful API 接口，支持学生信息的增删改查、班级筛选、模糊搜索、成绩统计等功能。

✅ 适用场景：
- 学校内部工具
- Spring Boot + JPA 学习项目
- 前端同学练手接口

## 🧱 技术栈
| 分层 | 技术 |
|------|------|
| 后端框架 | Spring Boot |
| ORM | Spring Data JPA |
| 数据库 | MySQL  |
| 构建工具 | Maven |
| 开发语言 | Java 17 |

---

## ✅ 已完成功能

### 基础 CRUD
- ✅ 查询所有学生
- ✅ 根据 ID 查询单个学生
- ✅ 新增学生（学号唯一校验）
- ✅ 修改学生信息
- ✅ 删除学生（逻辑删除）

### 业务查询
- ✅ 按班级查询学生
- ✅ 按姓名模糊搜索
- ✅ 按成绩筛选优秀学生
- ✅ 判断学号是否存在

### 统计分析
- ✅ 统计班级人数
- ✅ 计算班级平均分

## 🌐 API 接口列表

| 请求方式 | 接口路径 | 说明 |
|---------|---------|------|
| GET | `/api/students` | 获取所有学生 |
| GET | `/api/students/{id}` | 根据 ID 查询 |
| POST | `/api/students` | 新增学生 |
| PUT | `/api/students/{id}` | 修改学生信息 |
| DELETE | `/api/students/{id}` | 删除学生 |
| GET | `/api/students/class/{className}` | 按班级查询 |
| GET | `/api/students/search?name=` | 按姓名搜索 |
| GET | `/api/students/top?minScore=` | 优秀学生 |
| GET | `/api/students/count?className=` | 班级人数统计 |
| GET | `/api/students/average-score?className=` | 班级平均分 | 

---
## 🚀 快速启动

### 1. 克隆项目
```bash
git clone https://github.com/你的用户名/student-management.git
cd student-management
