package com.prueba.java;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.text.NumberFormat;
import java.util.TimeZone;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.prueba.java.*","com.prueba.java.repository.*"})
@EntityScan
public class ArquitecturaJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArquitecturaJavaApplication.class, args);
    }

    public static void printInfo() {
        Runtime runtime = Runtime.getRuntime();
        final NumberFormat format = NumberFormat.getInstance();
        final long maxMemory = runtime.maxMemory();
        final long allocatedMemory = runtime.totalMemory();
        final long freeMemory = runtime.freeMemory();
        final long mb = 1024L * 1024L;
        final String mega = " MB";
        log.info("========================== Memory Info ==========================");
        log.info("Free memory: {}", format.format(freeMemory / mb) + mega);
        log.info("Allocated memory: {}", format.format(allocatedMemory / mb) + mega);
        log.info("Max memory: {}", format.format(maxMemory / mb) + mega);
        log.info("Total free memory: {}", format.format((freeMemory + (maxMemory - allocatedMemory)) / mb) + mega);
        log.info("=================================================================");
    }

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Bean
    public CommandLineRunner memInfoRunner() {
        return args -> printInfo();
    }

}
