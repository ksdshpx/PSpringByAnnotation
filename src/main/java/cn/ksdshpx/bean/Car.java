package cn.ksdshpx.bean;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/28
 * Time: 13:24
 * Description:Car实体
 */
public class Car {
    public Car(){
        System.out.println("Car Constructor...");
    }

    public void init(){
        System.out.println("Car init()...");
    }

    public void destroy(){
        System.out.println("Car destroy()...");
    }
}
