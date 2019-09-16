package cn.ksdshpx.aop;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/7/23
 * Time: 9:48
 * Description:AOP业务逻辑类
 */
public class MathCalculator {
    public int div(int i,int j){
        System.out.println("MathCalculator.div()...");
        return i/j;
    }
}
