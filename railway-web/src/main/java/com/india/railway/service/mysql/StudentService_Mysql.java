package com.india.railway.service.mysql;

import com.india.railway.yaml.ReadYamlFileProperties;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.india.railway.model.mysql.Student_Mysql;
import com.india.railway.repository.mysql.Student_Repo_Mysql;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService_Mysql {

    @Autowired
    private Student_Repo_Mysql studentRepository;


    @Autowired
    private ReadYamlFileProperties readYamlFileProperties;

    @PostConstruct
    public void getYamlFileValues() {
        List<ReadYamlFileProperties.Passenger> kk= readYamlFileProperties.getList();
        for (ReadYamlFileProperties.Passenger p : kk) {
            System.out.println("hello ->"+p.getAge());
            //log.info("Passenger Name: {}, Age: {}, Email: {}", p.getName(), p.getAge(), p.getTicket().getNumber());
        }
    }

    public List<Student_Mysql> getAllStudent() {
        return studentRepository.findAll();
    }

    public Optional<Student_Mysql> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student_Mysql addStudent(Student_Mysql student) {
        return studentRepository.save(student);
    }

    public Student_Mysql updateStudent(Long id, Student_Mysql updateStuent) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(updateStuent.getName());
                    student.setEmail(updateStuent.getEmail());
                    // customer.setPhone(updatedCustomer.getPhone());
                    return studentRepository.save(student);
                }).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
