package cn.ksdshpx.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/6/28
 * Time: 10:45
 * Description:自定义逻辑返回需要导入的组件
 */
public class MyImportSelector implements ImportSelector {
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"cn.ksdshpx.bean.Red","cn.ksdshpx.bean.Yellow"};
    }
}
