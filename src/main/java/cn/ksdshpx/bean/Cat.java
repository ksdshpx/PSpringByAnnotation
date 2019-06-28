package cn.ksdshpx.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/28
 * Time: 13:52
 * Description:
 */
@Component
public class Cat implements InitializingBean, DisposableBean {
    public Cat(){
        System.out.println("Cat Constructor...");
    }

    public void destroy() throws Exception {
        System.out.println("Cat destroy()..");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("Cat init()..");
    }
}
