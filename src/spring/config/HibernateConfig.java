package spring.config;

import daoClasses.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("/WEB-INF/database.properties")
public class HibernateConfig {
    @Autowired
    private Environment env;

    private Properties hibernateProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        hibernateProperties.setProperty("current_session_context_class", env.getRequiredProperty("current_session_context_class"));

        return hibernateProperties;
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("postgresql.driver"));
        dataSource.setUrl(env.getRequiredProperty("postgresql.localhost") +
                env.getRequiredProperty("postgresql.database"));
        dataSource.setUsername(env.getRequiredProperty("postgresql.user"));
        dataSource.setPassword(env.getRequiredProperty("postgresql.password"));

        return dataSource;
    }

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setPackagesToScan("entity");
        factoryBean.setDataSource(dataSource);
        factoryBean.setHibernateProperties(hibernateProperties());

        return factoryBean;
    }

    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean(name = "CompetitionDAO")
    public CompetitionDAO CompetitionDAO() {
        return new CompetitionDAO();
    }

    @Bean(name = "CompSportsmenDAO")
    public CompSportsmenDAO CompSportsmenDAO() {
        return new CompSportsmenDAO();
    }

    @Bean(name = "CompTeamsDAO")
    public CompTeamsDAO CompTeamsDAO() {
        return new CompTeamsDAO();
    }

    @Bean(name = "SeatsDAO")
    public SeatsDAO SeatsDAO() {
        return new SeatsDAO();
    }

    @Bean(name = "SportsmanDAO")
    public SportsmanDAO SportsmanDAO() {
        return new SportsmanDAO();
    }

    @Bean(name = "SportsmenTeamsDAO")
    public SportsmenTeamsDAO SportsmenTeamsDAO() {
        return new SportsmenTeamsDAO();
    }

    @Bean(name = "TeamDAO")
    public TeamDAO TeamDAO() {
        return new TeamDAO();
    }

    @Bean(name = "TrainerDAO")
    public TrainerDAO TrainerDAO() {
        return new TrainerDAO();
    }
}
