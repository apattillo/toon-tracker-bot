package com.pattillo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.security.auth.login.LoginException;
import javax.sql.DataSource;

@SpringBootApplication
@ComponentScan({"com.pattillo"})
@EnableJpaRepositories("com.pattillo.repository")
@EntityScan("com.pattillo.entity")
public class EdIntegrationApplication {

    @Autowired
    DataSource dataSource;

    public static void main(String[] args) throws LoginException {
        SpringApplication.run(EdIntegrationApplication.class, args);
    }
}
