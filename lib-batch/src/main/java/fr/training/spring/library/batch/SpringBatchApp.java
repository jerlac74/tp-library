package fr.training.spring.library.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchApp {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringBatchApp.class, args);
        System.exit(SpringApplication.exit(context));
    }
}
