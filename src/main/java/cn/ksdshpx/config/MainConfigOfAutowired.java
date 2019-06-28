package cn.ksdshpx.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/28
 * Time: 18:21
 * Description:自动装配主配置类
 *
 */

/**
 * 自动装配：Spring利用依赖注入（DI）,完成对IOC容器中各个组件的依赖关系赋值
 * 1）@Autowired:自动注入
 *      1.1）默认按照类型去容器中找对应的组件，找到就赋值
 *      1.2）如果找到多个相同类型的组件，再将属性名作为组件的id去容器中查找
 *      1.3）@Qualifier("bookDao")，明确指定装配的组件的id，而不是使用默认的属性名
 *      1.4）自动装配默认一定要将属性赋值好，否则没有就会报错，可以使用@Autowired(required = false)防止这个情况
 *      1.5）@Primary:让Spring进行自动装配的时候，默认使用首选的Bean,也可以使用@Qualifier("bookDao")指定到底装配哪个
 */
@ComponentScan({"cn.ksdshpx.controller","cn.ksdshpx.service","cn.ksdshpx.dao"})
@Configuration
public class MainConfigOfAutowired {
}
