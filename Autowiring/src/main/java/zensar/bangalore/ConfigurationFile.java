package zensar.bangalore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConfigurationFile {

	@Bean("department1")
	public DepartmentBean getDepartmentBean1() {
		return new DepartmentBean("IT Department","Delhi");
	}
	
	@Bean("department2")
	@Primary
	public DepartmentBean getDepartment2() {
		return new DepartmentBean("HR Department","Mumbai");
	}
	
	@Bean("empBeen")
	public EmployeeBean getEmployeeBean() {
		return new EmployeeBean("Shahabuddin Ansari________");
	}
}
