package se.experis.tidsbankenbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"se.experis.tidsbankenbackend"})
@EntityScan("se.experis.tidsbankenbackend")
public class TidsbankenBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TidsbankenBackendApplication.class, args);
    }

}
