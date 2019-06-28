package cn.ksdshpx.config;

import cn.ksdshpx.bean.ColorFactoryBean;
import cn.ksdshpx.bean.Person;
import cn.ksdshpx.bean.Student;
import org.springframework.context.annotation.*;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/27
 * Time: 16:21
 * Description:主配置类
 */
//@Import：快速给IOC容器中导入一个组件，id默认为类的全类名
@Import({Student.class,MyImportSelector.class,MyImportBeanDefinitionRegistrar.class})
@Configuration
public class MainConfig2 {
    //默认是单实例的
    //prototype:多实例，每次使用时才创建对象
    //singleton:单实例，在IOC启动时调用方法创建对象放到IOC容器中
    /*
    懒加载：单实例bean默认在IOC容器启动时创建对象，使用懒加载可以使得在IOC容器
    容器启动时不创建对象，而在第一次使用Bean对象时创建对象，并初始化
    */
    //@Scope("prototype")
    @Lazy
    @Bean("person")
    public Person person(){
        System.out.println("person()...");
        return new Person("liSi",25);
    }

    /**
     * @Conditional:按照一定的条件注册Bean
     * 需求：如果系统是windows,给容器中注册bili,如果是linux,给容器注册linus
     */
    @Conditional(WindowsCondition.class)
    @Bean("bill")
    public Person person01(){
        return new Person("bili",60);
    }

    @Conditional(LinuxCondition.class)
    @Bean("linus")
    public Person person02(){
        return new Person("linus",50);
    }

    /**
     * 给容器中注册组件的方式
     * 1)自己写的类：包扫描+组件标注注解(@Controller/@Service/@Repository/@Component)
     * 2)导入的第三方包：@Bean
     * 3)快速给容器中导入一个组件：@Import
     *      3.1）@Import(要导入到容器中的组件)：容器中就会自动注册这个组件
     *              id默认为全类名
     *      3.2）ImportSelector:返回需要导入的组件的全类名数组
     *      3.3）ImportBeanDefinitionRegistrar:手动注册bean到IOC容器中
     * 4)使用Spring提供的FactoryBean(工厂Bean):工厂Bean获取的getObject()方法返回的对象
     */

    /**
     * 工厂Bean
     */
    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }
}
