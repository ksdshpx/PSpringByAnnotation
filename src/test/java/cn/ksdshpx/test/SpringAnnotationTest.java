package cn.ksdshpx.test;

import cn.ksdshpx.aop.MathCalculator;
import cn.ksdshpx.bean.Boss;
import cn.ksdshpx.bean.Car;
import cn.ksdshpx.bean.Person;
import cn.ksdshpx.bean.Yellow;
import cn.ksdshpx.config.*;
import cn.ksdshpx.dao.BookDao;
import cn.ksdshpx.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/27
 * Time: 15:23
 * Description:测试类
 */
public class SpringAnnotationTest {
    @Test
    public void test1() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        Person person = ctx.getBean("person", Person.class);
        System.out.println(person);
    }

    @Test
    public void test2(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

    @Test
    public void test3(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        //Person person = ctx.getBean("person",Person.class);
        //Person person2 = ctx.getBean("person",Person.class);
        //System.out.println(person == person2);
    }

    @Test
    public void test4(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] beanNamesForType = ctx.getBeanNamesForType(Person.class);
        for (String name : beanNamesForType) {
            System.out.println(name);
        }
        Environment environment = ctx.getEnvironment();
        String osName = environment.getProperty("os.name");
        System.out.println(osName);
        Map<String, Person> persons = ctx.getBeansOfType(Person.class);
        System.out.println(persons);
    }

    @Test
    public void test5(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        Object bean = ctx.getBean("colorFactoryBean");
        System.out.println(bean.getClass());//class cn.ksdshpx.bean.Color
        Object bean2 = ctx.getBean("&colorFactoryBean");
        System.out.println(bean2.getClass());//       class cn.ksdshpx.bean.ColorFactoryBean
    }

    @Test
    public void test6(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("IOC容器创建完成...");
        //关闭容器
        ctx.close();
    }

    @Test
    public void test7(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfigOfPropertyValue.class);
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        Person person = ctx.getBean("person", Person.class);
        System.out.println(person);
        ctx.close();
    }

    @Test
    public void test8(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        BookService bookService = ctx.getBean("bookService",BookService.class);
        System.out.println(bookService);
        BookDao bookDao = ctx.getBean("bookDao",BookDao.class);
        System.out.println(bookDao);
        ctx.close();
    }

    @Test
    public void test9(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        Boss boss = ctx.getBean("boss", Boss.class);
        System.out.println(boss);
        System.out.println("---------");
        Car car = ctx.getBean("car", Car.class);
        System.out.println(car);
    }

    @Test
    public void test10(){
        //AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfigOfProfile.class);
        //1.创建一个IOC容器
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        //2.设置需要激活的环境
        ctx.getEnvironment().setActiveProfiles("dev");
        //3.注册主配置类
        ctx.register(MainConfigOfProfile.class);
        //4.启动刷新容器
        ctx.refresh();
        String[] beanNamesForType = ctx.getBeanNamesForType(DataSource.class);
        for (String beanName : beanNamesForType) {
            System.out.println(beanName);
        }
        Yellow yellow = ctx.getBean("yellow", Yellow.class);
        System.out.println(yellow);
        ctx.close();
    }

    @Test
    public void test11(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
        MathCalculator mathCalculator = ctx.getBean(MathCalculator.class);
        mathCalculator.div(1,0);
        ctx.close();
    }
}
