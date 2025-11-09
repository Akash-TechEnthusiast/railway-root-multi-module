package com.india.railway.model.mysql;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
// @Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String address;
	private String qualification;
	private String gender;
	private String dateofbirth;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id")
	private Employee manager;

	// Self-referencing relationship: an employee can have many subordinates
	@OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Employee> subordinates = new HashSet<>();

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Set<Employee> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(Set<Employee> subordinates) {
		this.subordinates = subordinates;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", address=" + address +
				", qualification=" + qualification + ", gender=" + gender +
				", dateofbirth=" + dateofbirth +
				", managerId=" + (manager != null ? manager.getId() : null) +
				", subordinateCount=" + (subordinates != null ? subordinates.size() : 0) + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Employee))
			return false;
		Employee employee = (Employee) o;
		return id != null && id.equals(employee.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
