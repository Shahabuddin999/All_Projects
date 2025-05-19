package zensar.BeanLifeCycle;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
public class LifeCycleBean implements BeanNameAware, InitializingBean, DisposableBean {
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		System.out.println("Inside setName()");
		this.name = name;
	}
	//@Override
	public void setBeanName(String name) { // Inside BeanNameAware interface
		System.out.println("Inside setBeanName()");
	}
	//@Override
	public void afterPropertiesSet() throws Exception { // Inside InitializingBean interface
		System.out.println("inside afterPropertiesSet()");
	}
	//@Override
	public void destroy() throws Exception { // DisposableBean interface
		System.out.println("inside destroy()");
	}
	@Override
	public String toString() {
		return "LifeCycleBean [name=" + name + "]";
	}
	public LifeCycleBean() {
		System.out.println("Inside constructor LifeCycleBean()");
	}
	public void myDestroy() throws Exception { 
		System.out.println("inside myDestroy()");
	}
	public void myInit() {
		System.out.println("inside myInit()");
	}
}
