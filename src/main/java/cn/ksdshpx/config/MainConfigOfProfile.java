package cn.ksdshpx.config;

import cn.ksdshpx.bean.Yellow;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;

/**
 * Create with IntelliJ IDEA
 * Create by peng.xing
 * Date: 2019/7/2
 * Time: 13:41
 * Description:Profile主配置文件
 * Profile:Spring为我们提供的根据当前环境，动态的激活和切换一系列组件的功能
 * 开发环境、测试环境、生产环境
 * 数据源：
 * @Profile:指定组件在哪个环境的情况下才能被注册到容器中，不指定任何环境下都能注册这个组件
 * 1)加了环境标识的Bean，只有这个环境被激活的时候，才能注册到IOC容器中.默认是default环境
 *      1使用命令行动态参数，在虚拟机参数位置加载-Dspring.profiles.active=test
 *      2使用代码方式激活环境
 *
 * 2)写在配置类上，只有是指定的环境的时候，整个配置类里的所有配置才能生效
 *
 * 3)没有标注环境标识的Bean,在任何环境下都是被加载的
 *
 */
@PropertySource("classpath:/dbconfig.properties")
@Configuration
public class MainConfigOfProfile implements EmbeddedValueResolverAware {
    private StringValueResolver valueResolver;
    private String driverClass;

    @Value("${db.user}")
    private String user;

    @Bean
    public Yellow yellow(){
        return new Yellow();
    }

    @Profile("test")
    @Bean("testDataSource")
    public DataSource dataSourceForTest(@Value("${db.password}") String password) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Profile("dev")
    @Bean("devDataSource")
    public DataSource dataSourceForDev(@Value("${db.password}") String password) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/dev");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Profile("pro")
    @Bean("proDataSource")
    public DataSource dataSourceForPro(@Value("${db.password}") String password) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/pro");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.valueResolver = resolver;
        this.driverClass = valueResolver.resolveStringValue("${db.driverClass}");
    }
}
