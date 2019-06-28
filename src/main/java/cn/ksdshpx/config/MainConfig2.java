package cn.ksdshpx.config;

import cn.ksdshpx.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/27
 * Time: 16:21
 * Description:主配置类
 */
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
}
