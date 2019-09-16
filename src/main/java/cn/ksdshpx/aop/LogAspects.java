package cn.ksdshpx.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/7/23
 * Time: 9:52
 * Description:日志切面类
 *
 * @Aspect:告诉Spring,当前类是一个切面类
 */
@Aspect
public class LogAspects {
    //抽取公共的切入点表达式
    //1.本类引用
    //2.其他的切面引用
    @Pointcut("execution(public int cn.ksdshpx.aop.MathCalculator.*(..))")
    public void pointCut() {
    }

    //@Before:在目标方法之前切入，切入点表达式（指定在哪个方法切入）
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println(joinPoint.getSignature().getName() + "运行...参数列表是：{" + Arrays.asList(args) + "}");
    }

    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + "结束");
    }

    //注意，JointPoint一定要出现在参数列表的第一位
    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println(joinPoint.getSignature().getName() + "正常返回，运行结果是：{" + result + "}");
    }

    @AfterThrowing(value = "pointCut()", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        System.out.println(joinPoint.getSignature().getName() + "异常，异常信息是：{" + ex + "}");
    }
}
