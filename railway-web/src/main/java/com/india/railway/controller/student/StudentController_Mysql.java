package com.india.railway.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.india.railway.model.mysql.Student_Mysql;
import com.india.railway.service.mysql.StudentService_Mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/student")
// Allow React Frontend
public class StudentController_Mysql {

    @Autowired
    private StudentService_Mysql studentservice_mysql;

    @PostMapping("/create")
    public Student_Mysql addStudent(@RequestBody Student_Mysql student) {
        return studentservice_mysql.addStudent(student);
    }

    @GetMapping("/fetch_all_students")
    public List<Student_Mysql> getAllStudent() {
        return studentservice_mysql.getAllStudent();

    }

    @GetMapping("getStudenById/{id}")
    public Optional<Student_Mysql> getStudentById(@PathVariable Long id) {
        return studentservice_mysql.getStudentById(id);
    }

    @PutMapping("updateStudent/{id}")
    public Student_Mysql updateStudent(@PathVariable Long id, @RequestBody Student_Mysql student) {
        return studentservice_mysql.updateStudent(id, student);
    }

    @DeleteMapping("deteleStudent/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentservice_mysql.deleteStudent(id);
        return "Student deleted successfully!";
    }

    @GetMapping("/fetch_country_for_student")
    public List<Map<String, Object>> getStudentDropdown() {

        // List<Student_Mysql> stulist = studentservice_mysql.getAllStudent();
        return studentservice_mysql.getAllStudent().stream().map(student -> {
            Map<String, Object> map = new HashMap<>();
            // map.put("id", student.getId());
            map.put("name", student.getCountry());
            return map;
        }).collect(Collectors.toList());
    }

}
