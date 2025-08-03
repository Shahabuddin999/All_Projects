package com.k8s.controller;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.k8s.entity.CompanyData;
import com.k8s.entity.EmployeesData;
import com.k8s.repository.EmployeeRepo;

@RestController
public class EmployeeCoporate {
    
    @Autowired
    private EmployeeRepo employeeRepo1;

    @GetMapping(value = "/welcome-employee")
    public String welcomePage() {
    	
    	return "Hi everyone you'r welcome on Kubernetes with DB";
    }
    
    
    @GetMapping(value = "/indexing")
    public String getUsersContact() {
    	ModelAndView model = new ModelAndView();
    	
    	model.setViewName("welcome");
        model.addObject("empdetails", "aaaa");
        System.out.println(model);
    	return "welcome docker + Kubernetes with DB";
    }
    
    
    @GetMapping(value = "/get-empployee")
    public List<EmployeesData> getUsersContactDetail() {       		
        List<EmployeesData> list = (List<EmployeesData>) employeeRepo1.findAll();
        Iterator<EmployeesData> itr = list.iterator();
        while(itr.hasNext()) {
        	EmployeesData emp = itr.next();
        	System.out.println("Name: "+emp.getEmpName()+",  Address: "+emp.getAddress()+",  EmpId: "+emp.getId());
        	CompanyData c = emp.getCompany();
        	System.out.println(c);
        }
        return list;
    }
    
   
    @PostMapping("/save-employee")
    public EmployeesData update(@RequestBody EmployeesData employee) {
        System.out.println("Recieved : "+employee);
        return employeeRepo1.save(employee);
    }
}
