package cn.ksdshpx.config;

import cn.ksdshpx.bean.RainBow;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/28
 * Time: 11:05
 * Description:
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean definition = registry.containsBeanDefinition("cn.ksdshpx.bean.Red");
        boolean definition2 = registry.containsBeanDefinition("cn.ksdshpx.bean.Yellow");
        if(definition && definition2){
            registry.registerBeanDefinition("rainBow",new RootBeanDefinition(RainBow.class));
        }
    }
}
