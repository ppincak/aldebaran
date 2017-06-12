package com.aldebaran.auth.server.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource


const val entitiesToScan = "com.aldebaran"


@Configuration
open class JpaConfiguration {

    @Primary
    @Bean(name = arrayOf("dataSource"))
    @ConfigurationProperties(prefix = "spring.datasource")
    open fun dataSource(): DataSource {
        return DataSourceBuilder
                .create()
                .type(HikariDataSource::class.java)
                .build()
    }

    @Primary
    @Bean(name = arrayOf("entityManagerFactory"))
    open fun entityManagerFactory(builder: EntityManagerFactoryBuilder,
                                  dataSource: DataSource): LocalContainerEntityManagerFactoryBean {
        return builder
                .dataSource(dataSource)
                .packages(entitiesToScan)
                .persistenceUnit("JpaUnit")
                .build()
    }

    @Primary
    @Bean(name = arrayOf("transactionManager"))
    open fun transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }
}