package zensar.BeanLifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class ZenBeanPostProcessor implements BeanPostProcessor{
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("Inside postProcessBeforeInitialization()");
		if(bean instanceof LifeCycleBean) {
			System.out.println("Inside if condition of postProcessBeforeInitialization() of BeanPostProcessor");
			LifeCycleBean lifeCycleBean =  (LifeCycleBean) bean;
			lifeCycleBean.setName(lifeCycleBean.getName().toUpperCase());
		}
		return bean;
	}
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("Inside postProcessAfterInitialization()");
		return bean;
	}
}
