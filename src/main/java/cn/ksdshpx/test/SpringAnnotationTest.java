package cn.ksdshpx.test;

import cn.ksdshpx.bean.Person;
import cn.ksdshpx.config.MainConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/27
 * Time: 15:23
 * Description:测试类
 */
public class SpringAnnotationTest {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        Person person = ctx.getBean("person", Person.class);
        System.out.println(person);
    }
}
