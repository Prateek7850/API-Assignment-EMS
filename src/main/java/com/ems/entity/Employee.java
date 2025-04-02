package com.ems.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Employee")
public class Employee {
       
	@Id
	@Column(name="id",nullable=false)
	private Long id;
	@Column(name="name",nullable=false)
	private String name;
	@Column(name="designation",nullable=false)
	@Enumerated(EnumType.STRING)
	private Designation designation;
	private double salary;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="department_id")
	@JsonIgnoreProperties({"employee"})
	private Department department;
	
	public Employee() {
		super();
	}

	public Employee(Long id, String name, Designation designation, double salary, Department department) {
		super();
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.salary = salary;
		this.department = department;
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

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary=salary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}		
}
