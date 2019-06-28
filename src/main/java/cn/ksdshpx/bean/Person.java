package cn.ksdshpx.bean;

import org.springframework.beans.factory.annotation.Value;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/27
 * Time: 15:19
 * Description:Person实体类
 */
public class Person {
    /*
     * 使用@Value进行属性赋值
     * 1.基本数值
     * 2.SpEl:#{}
     * 3.可以使用${}取出配置文件中的值(在运行环境中)
     */
    @Value("zhangSan")
    private String name;
    @Value("#{20-2}")
    private Integer age;

    public Person() {
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
