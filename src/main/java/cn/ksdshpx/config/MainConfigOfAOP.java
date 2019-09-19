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
 *
 *              流程：
 *                  1）传入配置类，创建IOC容器
 *                  2）注册配置类，调用refresh()方法刷新容器
 *                  3）registerBeanPostProcessors(beanFactory);注册bean的后置处理器来方便拦截bean的创建
 *                      ①先获取IOC容器中已经定义了的需要创建对象的所有BeanPostProcessor
 *                      ②给容器中添加别的BeanPostProcessor
 *                      ③优先注册实现了PriorityOrdered接口的BeanPostProcessor
 *                      ④再给容器中注册实现了Ordered接口的BeanPostProcessor
 *                      ⑤最后注册没实现优先级接口的BeanPostProcessor
 *                      ⑥注册BeanPostProcessor,实际上就是创建对象，保存到容器中
 *                          创建internalAutoProxyCreator的BeanPostProcessor[AnnotationAwareAspectJAutoProxyCreator]
 *                              a.创建Bean的实例
 *                              b.populateBean:给Bean的属性赋值
 *                              c.initializeBean:初始化Bean
 *                                  aa.invokeAwareMethods:处理Aware接口的方法回调
 *                                  bb.applyBeanPostProcessorsBeforeInitialization():调用后置处理器的postProcessBeforeInitialization()
 *                                  cc.invokeInitMethods():执行自定义的初始化方法
 *                                  dd.applyBeanPostProcessorsBeforeInitialization():调用后置处理器的postProcessBeforeInitialization()
 *                              d.BeanPostProcessor[AnnotationAwareAspectJAutoProxyCreator]创建成功
 *                      ⑦把BeanPostProcessor注册到BeanFactory中
 *                          beanFactory.addBeanPostProcessor(postProcessor)
 *      ==============以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程=============
 *      AnnotationAwareAspectJAutoProxyCreator => InstantiationAwareBeanPostProcessor
 *                  4）finishBeanFactoryInitialization(beanFactory);完成BeanFactory的初始化工作，创建剩下的单实例Bean
 *                      ①遍历获取容器中所有的Bean,依次创建对象getBean(beanName);
 *                          getBean()->doGetBean()->getSingleton()
 *                      ②创建Bean
 *                          【AnnotationAwareAspectJAutoProxyCreator会在所有Bean对象创建之前会有一个拦截，InstantiationAwareBeanPostProcessor，会调用postProcessBeforeInstantiation】
 *                          a.先从缓存中获取当前Bean,如果能获取到,说明bean之前已经被创建过,直接使用,否则再创建
 *                            只要创建好的Bean都会被缓存起来
 *                          b.createBean();创建Bean；AnnotationAwareAspectJAutoProxyCreator会在任何Bean对象创建之前先尝试返回Bean的实例
 *                              【BeanPostProcessor是在Bean对象创建完成初始化前后调用的】
 *                              【InstantiationAwareBeanPostProcessor是在创建Bean实例之前就尝试用后置处理器返回对象】
 *                              aa.resolveBeforeInstantiation(beanName, mbdToUse);解析BeforeInstantiation
 *                                  希望后置处理器在此能返回一个代理对象,如果能返回代理对象就是使用,如果不能就继续
 *                                  aaa.后置处理器先尝试返回对象
 *                                      bean = applyBeanPostProcessorsBeforeInstantiation()
 *                                          拿到所有后置处理器，如果是InstantiationAwareBeanPostProcessor就执行
 *                                          后置处理器的postProcessBeforeInstantiation()
 *                                      if (bean != null) {
 *                    						bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
 *                                       }
 *                              bb.doCreateBean(beanName, mbdToUse, args);真正的创建Bean实例，和3.6流程一样
 *
 *       AnnotationAwareAspectJAutoProxyCreator【InstantiationAwareBeanPostProcessor】的作用：
 *       1）在每个Bean创建之前调用postProcessBeforeInstantiation
 *          关心MathCalculator和LogAspects的创建
 *          aa.判断当前Bean是否在advisedBeans（保存了所有需要增强的Bean）中
 *          bb.判断当前Bean是否是基础类型的Advice、Pointcut、Advisor、AopInfrastructureBean，
 *             或者是否是切面（@AspectJ）
 *          cc.是否需要跳过
 *              ①获取候选的增强器（切面的通知方法）【List<Advisor> candidateAdvisors】
 *                  每一个封装的通知方法的增强器是InstantiationModelAwarePointcutAdvisor
 *                  判断每一个增强器是否是AspectJPointcutAdvisor类型
 *              ②永远放回false
 *        2）创建对象
 *         postProcessAfterInitialization
 *           wrapIfNecessary(bean, beanName, cacheKey);//包装如果需要的情况下
 *             aa.获取当前Bean的所有增强器（通知方法） Object[] specificInterceptors
 *                aaa.找到所有候选的增强器（找哪些通知方法是需要切入当前Bean通知方法的）
 *                bbb.找到能在当前Bean使用的增强器
 *                ccc.给增强器排序
 *             bb.保存当前Bean到advisedBeans中；
 *             cc.如果当前Bean需要增强，创建当前Bean的代理对象
 *                aaa.获取所有增强器（通知方法）
 *                bbb.保存到proxyFactory中
 *                ccc.创建代理对象：Spring自动决定
 *                    JdkDynamicAopProxy(config)：JDK动态代理
 *                    ObjenesisCglibAopProxy(config)：CGLIB动态代理
 *             dd.给容器中返回当前组件使用cglib增强了的代理对象
 *             ee.以后容器中获取到的就是这个组件的代理对象，执行目标方法的时候就会执行通知方法的流程
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
