package zensar.bangalore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class EmployeeBean {

	@Autowired
	@Qualifier("department2")
	private DepartmentBean departmentBeanNew;
	private String empName;
	public DepartmentBean getDepartmentBeanNew() {
		return departmentBeanNew;
	}
	public void setDepartmentBeanNew(DepartmentBean departmentBeanNew) {
		this.departmentBeanNew = departmentBeanNew;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public EmployeeBean(DepartmentBean departmentBeanNew, String empName) {
		super();
		this.departmentBeanNew = departmentBeanNew;
		this.empName = empName;
	}
	public EmployeeBean() {
		super();
	}
	public EmployeeBean(String empName) {
		this.empName = empName;
	}
	@Override
	public String toString() {
		return "EmployeeBean [departmentBeanNew=" + departmentBeanNew + ", empName=" + empName + "]";
	}
	
}
