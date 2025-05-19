package com.zensar.entity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="EMPLOYEE")
public class EmployeeEntity {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long employeeId;

	@Column(name="name")
	private String name;
	
	@Column(name="sal")
	private double sal;

	@OneToOne(cascade={CascadeType.ALL},
			fetch=FetchType.LAZY)
	@JoinColumn(name="profile_id")
	private ProfileEntity profileObject;
	
	public ProfileEntity getProfileObject() {
		return profileObject;
	}

	public void setProfileObject(ProfileEntity profileObject) {
		this.profileObject = profileObject;
	}

	public EmployeeEntity() {
		
	}
	
	public EmployeeEntity(String name, double sal) {
		this.name = name;
		this.sal = sal;
	}

	

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public ProfileEntity getProfile() {
		return profileObject;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSal() {
		return sal;
	}

	public void setSal(double sal) {
		this.sal = sal;
	}

	public EmployeeEntity(String name, double sal, ProfileEntity profileObject) {
		super();
		this.name = name;
		this.sal = sal;
		this.profileObject = profileObject;
	}

	@Override
	public String toString() {
		return "EmployeeEntity [employeeId=" + employeeId + ", name=" + name + ", sal=" + sal + ", profileObject="
				+ profileObject + "]";
	}
	
	
}
/*
CREATE TABLE EMPLOYEE (ID NUMBER(4) PRIMARY KEY, NAME VARCHAR2(15), SAL NUMBER(7,2), 
	DEPARTMENT_ID NUMBER(4) NOT NULL,
	CONSTRAINT FK_DEPARTMENT FOREIGN KEY(DEPARTMENT_ID) REFERENCES DEPARTMENT(DEPARTMENT_ID))
	 
*/