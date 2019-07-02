package cn.ksdshpx.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/7/2
 * Time: 11:03
 * Description:Boss类
 */
//默认加在IOC容器中的组件，容器启动会调用无参构造器创建对象，再进行初始化赋值等操作
@Component
public class Boss {
    private Car car;

    //构造器要用的组件，也都是从IOC容器中获取
    @Autowired
    public Boss(Car car) {
        this.car = car;
        System.out.println("Boss Constructor()...");
    }

    //@Autowired标注在方法，Spring容器创建对象，就会调用方法，完成赋值
    //方法使用的参数，自定义类型的值从IOC容器中获取
    //@Autowired
    public void setCar(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }
}
