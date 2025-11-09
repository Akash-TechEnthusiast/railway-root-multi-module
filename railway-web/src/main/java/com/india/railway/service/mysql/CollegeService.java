package com.india.railway.service.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.india.railway.model.mysql.College;
import com.india.railway.model.mysql.Department;
import com.india.railway.repository.mysql.CollegeRepository;
import com.india.railway.repository.mysql.DepartmentRepository;

import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import jakarta.transaction.Transactional;

@Service
public class CollegeService {
    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    public College saveCollege(College college) {

        Iterator<Department> iterator = college.getDepartment().iterator();
        while (iterator.hasNext()) {
            Department element = iterator.next();
            // departmentRepository.save(element);
        }
        return college;

        // return collegeRepository.save(college);
    }

    // public List<College> getAllColleges() {
    // return (List<College>) collegeRepository.findAll();
    // }

    /*
     * public Page<College> getCollegeByName(String name,int page,int size) {
     * Sort sort = "desc".equalsIgnoreCase("desc") ? Sort.by("amount").descending()
     * : Sort.by("amount").ascending();
     * Pageable pageable = PageRequest.of(page, size,sort);
     * 
     * return collegeRepository.findByName(name, pageable);
     * 
     * // return collegeRepository.findById(id).orElse(null);
     * }
     * 
     * 
     * 
     * public void deleteCollege(Long id) {
     * collegeRepository.deleteById(id);
     * }
     * 
     * public College addDepartmentToCollege(Long collgeId, Long departmentId) {
     * College college = collegeRepository.findById(collgeId).orElse(null);
     * Department department =
     * departmentRepository.findById(departmentId).orElse(null);
     * if (college != null && department != null) {
     * college.addDepartment(department);
     * return collegeRepository.save(college);
     * }
     * return null;
     * }
     * 
     * public College removeDepartmentFromCollege(Long collgeId, Long departmentId)
     * {
     * College college = collegeRepository.findById(collgeId).orElse(null);
     * Department department =
     * departmentRepository.findById(departmentId).orElse(null);
     * if (college != null && department != null) {
     * college.removeDepartment(department);
     * return collegeRepository.save(college);
     * }
     * return null;
     * }
     */
}
