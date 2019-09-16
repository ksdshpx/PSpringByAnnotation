package cn.ksdshpx.config;

import cn.ksdshpx.aop.LogAspects;
import cn.ksdshpx.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/7/23
 * Time: 9:43
 * Description:面向切面编程AOP
 *         AOP:【底层是动态代理】
 *             指在程序运行期间动态的将某段代码切入到指定方法指定位置运行的编程方式
 *
 *         1.导入AOP模块:spring-aspects
 *         2.定义一个业务逻辑类MathCalculator,在业务逻辑运行的时候将日志进行打印(方法之前，方法运行结束，方法出现异常)
 *         3.定义一个日志切面类LogAspects,切面类里面的方法需要动态感知MathCalculator.div运行到哪里，然后进行执行
 *              通知方法：
 *                  前置通知(@Before)：logStart(),在目标方法执行之前执行
 *                  后置通知(@After)：logEnd(),在目标方法执行之后执行(无论方法正常结束还是异常结束)
 *                  返回通知(@AfterReturning)：logReturn(),在目标方法正常返回之后执行
 *                  异常通知(@AfterThrowing)：logException(),在目标方法执行出现异常之后执行
 *                  环绕通知(@Around)：动态代理，手动推进目标方法运行（joinPoint.proceed）
 *         4.给切面类的目标方法标注何时何地运行（通知注解）
 *         5.将切面类和业务逻辑类（目标方法所在的类）都加入到IOC容器中
 *         6.告诉Spring哪个是切面类（给切面类上加一个注解@Aspect）
 *         7.给配置类中加@EnableAspectJAutoProxy，开启基于注解的AOP模式
 *              在Spring中很多的@EnableXXX.
 *
 *       主要三步：①.将业务逻辑组件和切面类都加入到容器中，告诉Spring哪个是切面类
 *               ②.在切面类上的每个通知方法上标注通知注解，告诉Spring何时何地运行（切入点表达式）
 *               ③.开启基于注解的AOP模式@EnableAspectJAutoProxy
 *
 *       AOP原理：【看给容器中注册了什么组件，这个组件什么时候工作，这个组件工作时候的功能是什么】
 *             @EnableAspectJAutoProxy
 *         1.@EnableAspectJAutoProxy是什么？
 *              @Import(AspectJAutoProxyRegistrar.class)：给容器中导入AspectJAutoProxyRegistrar
 *                  利用AspectJAutoProxyRegistear自定义给容器中注册bean
 *                  internalAutoProxyCreator=AnnotationAwareAspectJAutoProxyCreator
 *
 *              给容器中注册一个AnnotationAwareAspectJAutoProxyCreator
 *
 *         2.AnnotationAwareAspectJAutoProxyCreator
 *              AnnotationAwareAspectJAutoProxyCreator
 *                  ->AspectJAwareAdvisorAutoProxyCreator
 *                      ->AbstractAdvisorAutoProxyCreator
 *                          ->AbstractAutoProxyCreator
 *                              ->implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *                              关注后置处理器（在Bean初始化前后做事情）、自动注入BeanFactory
 *
 *              AbstractAutoProxyCreator.setBeanFactory()
 *              AbstractAutoProxyCreator.有后置处理器的逻辑
 *
 *              AbstractAdvisorAutoProxyCreator.setBeanFactory()->initBeanFactory()
 *
 *              AnnotationAwareAspectJAutoProxyCreator.initBeanFactory()
 */
@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAOP {

    //业务逻辑类加入到容器中
    @Bean
    public MathCalculator calculator(){
        return new MathCalculator();
    }

    //切面类加入到容器中
    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }
}
