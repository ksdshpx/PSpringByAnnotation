package cn.ksdshpx.config;

import cn.ksdshpx.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/27
 * Time: 15:17
 * Description:主配置文件
 */
//@Configuration:告诉Spring这是一个配置类，相当于配置文件
@Configuration
//@ComponentScan(value = "cn.ksdshpx", excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, Service.class})})
@ComponentScan(value = "cn.ksdshpx", includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, Service.class})},useDefaultFilters = false)
public class MainConfig {
    //@Bean:往IOC容器中注册一个Bean,类型为返回值类型，id默认为方法名
    @Bean("person")
    public Person person() {
        return new Person("zhangSan", 20);
    }
}
