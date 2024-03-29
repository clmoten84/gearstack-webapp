package io.gearstack.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * GearstackApplication
 *
 * Spring Boot runner class
 */
@SpringBootApplication
@ComponentScan(basePackages = "io.gearstack")
@EntityScan(basePackages = "io.gearstack.models")
@EnableJpaRepositories(basePackages = "io.gearstack.repos")
@EnableAutoConfiguration
@RefreshScope
public class GearstackApplication {
    public static void main(String[] args) {
        SpringApplication.run(GearstackApplication.class, args);
    }
}
