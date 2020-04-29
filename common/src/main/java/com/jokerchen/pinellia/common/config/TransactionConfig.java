package com.jokerchen.pinellia.common.config;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @description: 定义系统的事务注解使用的hibernate的事务控制器
 * @author Joker Chen
 * @date 2019-03-18 17:08:32
 */
@Configuration
public class TransactionConfig {

	@Bean
	public PlatformTransactionManager hibernatgeTransactionManager(EntityManagerFactory entityManagerFactory) {
		return new HibernateTransactionManager(entityManagerFactory.unwrap(SessionFactory.class));
	}

    /*@Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }*/
}
