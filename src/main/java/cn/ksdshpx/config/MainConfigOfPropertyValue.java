package cn.ksdshpx.config;

import cn.ksdshpx.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/28
 * Time: 17:48
 * Description:属性赋值主配置文件
 */
@Configuration
public class MainConfigOfPropertyValue {
    @Bean
    public Person person(){
        return new Person();
    }
}
