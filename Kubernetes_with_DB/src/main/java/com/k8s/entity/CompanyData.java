package com.k8s.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "companydata")
public class CompanyData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "companyId")
	private int id;
	
	@Column(name = "CompanyName")
	private String companyName;
	
	@Column(name = "CompanyOwner")
	private String companyOwner;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyOwner() {
		return companyOwner;
	}
	public void setCompanyOwner(String companyOwner) {
		this.companyOwner = companyOwner;
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", companyName=" + companyName + ", companyOwner=" + companyOwner + "]";
	}
	
}
