package com.india.railway.service.mysql;

import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.india.railway.exception.EmployeeAlreadyExistsException;
import com.india.railway.exception.NoSuchEmployeeExistsException;
import com.india.railway.model.mysql.Employee;
import com.india.railway.repository.mysql.EmployeRepository;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeService {

	@Autowired
	private EmployeRepository empRespository;

	// Method to get customer by Id.Throws
	// NoSuchElementException for invalid Id
	public Employee getEmployee(Long id) {
		return empRespository.findById(id)
				.orElseThrow(() -> new NoSuchEmployeeExistsException("NO EMPLOYEE PRESENT WITH ID = " + id));
	}

	public Employee getEmployeeTreeFromRoot(Long rootId) {
		Employee root = empRespository.findById(rootId)
				.orElseThrow(() -> new RuntimeException("Root not found"));
		populateSubordinates(root);
		return root;
	}

	private void populateSubordinates(Employee manager) {
		List<Employee> subs = empRespository.findByManagerId(manager.getId());
		Set<Employee> set = new HashSet<>(subs);
		manager.setSubordinates(set); // important to set it!

		for (Employee sub : subs) {
			populateSubordinates(sub); // recursive call
		}
	}

	// Method to add new customer details to database.Throws
	// CustomerAlreadyExistsException when customer detail
	// already exist
	public String addEmployee(Employee employee) {
		Employee existingEmployee = empRespository.findById(employee.getId()).orElse(null);
		if (existingEmployee == null) {
			empRespository.save(employee);
			return "Employee added successfully";
		} else
			throw new EmployeeAlreadyExistsException("Employee already exists!!");
	}

	// Method to update customer details to database.Throws
	// NoSuchCustomerExistsException when customer doesn't
	// already exist in database
	public String updateEmployee(Employee employee) {
		Employee existingEmployee = empRespository.findById(employee.getId()).orElse(null);
		if (existingEmployee == null)
			throw new NoSuchEmployeeExistsException("No Such Employee exists!!");
		else {
			existingEmployee.setName(employee.getName());
			existingEmployee.setAddress(employee.getAddress());
			empRespository.save(existingEmployee);
			return "Record updated Successfully";
		}
	}

	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return empRespository.findAll();
	}

	@Override
	public void deleteEmployee(Long id) {

		if (empRespository.existsById(id)) {
			empRespository.deleteById(id);
		} else {
			throw new RuntimeException("Employee not found with ID: " + id);
		}

	}

}