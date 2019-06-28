package cn.ksdshpx.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/28
 * Time: 11:16
 * Description:工厂Bean
 */
public class ColorFactoryBean implements FactoryBean<Color> {
    public Color getObject() throws Exception {
        System.out.println("ColorFactoryBean#getObject()...");
        return new Color();
    }

    public Class<?> getObjectType() {
        return Color.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
