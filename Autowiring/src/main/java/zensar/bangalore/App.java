package zensar.bangalore;

/**
 * Hello world!
 *
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App 
{
    public static void main( String[] args )
    {
    	AbstractApplicationContext context = new AnnotationConfigApplicationContext("zensar.bangalore");
    	EmployeeBean employeeBean =  (EmployeeBean) context.getBean("empBeen");
    	System.out.println(employeeBean);
    	
    	/*
    	 * bangalore
    	AbstractApplicationContext context = new ClassPathXmlApplicationContext("simple_beans.xml");
		
    	EmployeeBean obj = (EmployeeBean) context.getBean("employee");
		System.out.println(obj);
		
		context.registerShutdownHook();*/    
    	}
}
