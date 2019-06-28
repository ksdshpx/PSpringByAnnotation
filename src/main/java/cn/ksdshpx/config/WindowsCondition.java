package cn.ksdshpx.config;


import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/28
 * Time: 10:00
 * Description:判断系统是否是Windows
 */
public class WindowsCondition implements Condition {
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //1.获取IOC容器使用的bean工厂
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        //2.获取类加载器
        ClassLoader classLoader = context.getClassLoader();
        //3.获取当前环境信息
        Environment environment = context.getEnvironment();
        //4.获取bean定义的注册类
        BeanDefinitionRegistry registry = context.getRegistry();
        return "Windows 10".equals(environment.getProperty("os.name"));
    }
}
