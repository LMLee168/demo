package unicorn.mp.schedule.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
//@Import(AuthDataSourceConfig.class)
@MapperScan(basePackages = {DataSourceConfig.PACKAGE, DataSourceConfig.COMMON_PACKAGE}, sqlSessionFactoryRef = "mybatisSqlSessionFactory")
public class DataSourceConfig {

    static final String PACKAGE = "com.demo.api.mapper";
    static final String COMMON_PACKAGE = "com.demo.common.mapper";
    private static final String MAPPER_LOCATION = "classpath*:mybatis-mappers/*.xml";

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;


    @Bean(name = "mybatisDataSource")
    @Primary
    public DataSource createDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setValidationQuery("select 1");
        dataSource.setTestOnBorrow(true);
        List<String> connectionInitSqls = new ArrayList<>();
        connectionInitSqls.add("set names utf8mb4;");
        dataSource.setConnectionInitSqls(connectionInitSqls);
        return dataSource;
    }


    @Bean(name = "mybatisTransactionManager")
    @Primary
    public DataSourceTransactionManager mybatisTransactionManager(@Qualifier("mybatisDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mybatisSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("mybatisDataSource") DataSource mybatisDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(mybatisDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(DataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
