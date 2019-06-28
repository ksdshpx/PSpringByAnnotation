package cn.ksdshpx.config;

import cn.ksdshpx.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/28
 * Time: 13:08
 * Description:Bean的生命周期主配置类
 */

/**
 * Bean的生命周期：
 *      bean创建 --> 初始化 -->销毁的全过程
 * IOC管理Bean的生命周期，我们可以自定义初始化和销毁方法，容器在bean进行到
 * 当前生命周期的时候调用我们自定义的初始化和销毁方法
 * 1）指定初始化和销毁方法
 *      通过@Bean注解指定init-method(对象创建完成并赋值好后)和
 *      destroy-method(单实例Bean容器关闭后，多实例Bean容器不会管理)
 * 2）通过Bean实现InitializingBean定义初始化逻辑，DisposableBean定义销毁逻辑
 * 3）使用JSR250规范定义的@PostConstruct以及@PreDestroy
 *         @PostConstruct:在Bean创建完成，并且赋值完成后进行初始化
 *         @PreDestroy：在IOC容器销毁Bean之前清理工作
 */
@ComponentScan("cn.ksdshpx.bean")
@Configuration
public class MainConfigOfLifeCycle {
    @Bean(value = "car",initMethod = "init",destroyMethod = "destroy")
    public Car car(){
        return new Car();
    }
}
