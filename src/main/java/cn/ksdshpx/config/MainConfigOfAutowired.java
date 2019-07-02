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
 * 1）@Autowired:自动注入,这是Spring提供的注解
 *      1.1）默认按照类型去容器中找对应的组件，找到就赋值
 *      1.2）如果找到多个相同类型的组件，再将属性名作为组件的id去容器中查找
 *      1.3）@Qualifier("bookDao")，明确指定装配的组件的id，而不是使用默认的属性名
 *      1.4）自动装配默认一定要将属性赋值好，否则没有就会报错，可以使用@Autowired(required = false)防止这个情况
 *      1.5）@Primary:让Spring进行自动装配的时候，默认使用首选的Bean,也可以使用@Qualifier("bookDao")指定到底装配哪个
 *
 *  2）Spring还支持使用@Resource(JSR250规范)和@Inject(JSR330规范),这两个都是Java规范的注解
 *      @Resource:可以和@Autowired一样可以实现自动装配功能，但是默认是按照组件名称进行装配，没有@Primary等功能
 *      @Inject:需要导入javax.inject的包，和@Autowired功能一样，但是没有required=false等功能
 *
 *  AutowiredAnnotationBeanPostProcessor:解析完成自动装配功能
 *
 *  3）@Autowired:构造器，参数，方法，属性上都可以使用，都是从IOC容器中获取组件的值
 *          3.1）标注在方法位置:@Bean+方法参数，参数从容器中获取，默认不写@Autowired都能自动装配
 *          3.2）标注在构造器上：如果组件只有一个有参构造器，这个有参构造器的@Autowired可以省略，参数位置的组件还是从IOC容器中获取
 *          3.3）标注在参数上
 *
 */
@ComponentScan({"cn.ksdshpx.controller","cn.ksdshpx.service","cn.ksdshpx.dao","cn.ksdshpx.bean"})
@Configuration
public class MainConfigOfAutowired {
}
