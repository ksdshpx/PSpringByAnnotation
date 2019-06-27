package cn.ksdshpx.config;

import cn.ksdshpx.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/27
 * Time: 15:17
 * Description:主配置文件
 */
//@Configuration:告诉Spring这是一个配置类，相当于配置文件
@Configuration
public class MainConfig {
    //@Bean:往IOC容器中注册一个Bean,类型为返回值类型，id默认为方法名
    @Bean("person")
    public Person person(){
        return new Person("zhangSan",20);
    }
}
