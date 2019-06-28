package cn.ksdshpx.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/28
 * Time: 15:28
 * Description:
 */
@Component
public class Dog {
    public Dog(){
        System.out.println("Dog Constructor()...");
    }

    //对象创建并赋值之后调用
    @PostConstruct
    public void init(){
        System.out.println("Dog @PostConstruct...");
    }

    //IOC容器移除对象之前
    @PreDestroy
    public void destroy(){
        System.out.println("Dog @PreDestroy...");
    }
}
