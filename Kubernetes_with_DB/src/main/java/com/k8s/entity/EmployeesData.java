package com.k8s.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employedata")
public class EmployeesData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "empId")
	private int id;
	
	@Column(name = "empName")
	private String empName;
	
	@Column(name = "empAddress")
	private String address;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ucompanyid" /* , referencedColumnName = "companyId" */)
	private CompanyData company;

	public CompanyData getCompany() {
		return company;
	}

	public void setCompany(CompanyData company) {
		this.company = company;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employees [id=" + id + ", empName=" + empName + ", address=" + address + ", company=" + company + "]";
	}
	
}
