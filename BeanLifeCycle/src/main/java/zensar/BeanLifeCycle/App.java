package zensar.BeanLifeCycle;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	AbstractApplicationContext context = new ClassPathXmlApplicationContext("simple_beans.xml");
		
		LifeCycleBean obj = (LifeCycleBean) context.getBean("lifeCycleBeanId");
		System.out.println(obj);
		
		MusicChatApp musicChatApp = (MusicChatApp) context.getBean("musicChatApp");
		System.out.println(musicChatApp);
		context.registerShutdownHook();
		
		/*
		Order bean = (Order) context.getBean("orderId");
		System.out.println("Employee: " + bean);
		
		Order bean1 = (Order) context.getBean("orderId_2");
		System.out.println("Employee: " + bean1);
		*/
		
		/*Map<String, Order> map = context.getBeansOfType(Order.class);
		map.forEach((key, value)->{
			System.out.println(key+" :: "+value);
		});
		
		String[] beans = context.getBeanNamesForType(Order.class);
		Float price = (float) 0;
		for(String beanData : beans) {
			Order getBean = (Order) context.getBean(beanData);
			price = (float) (price+getBean.getItem().getPrice());
			
		}
		
		System.err.println("Total price : "+price);
		System.out.println("----------");*/    }
}
