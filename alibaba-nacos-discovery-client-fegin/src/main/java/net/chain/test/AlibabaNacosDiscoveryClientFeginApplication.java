package net.chain.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.test.fegin.service")
@MapperScan(basePackages = {"net.chain.test.mapper"})
@ComponentScan(basePackages = {"com.test.fegin.config","net.chain.test"})
@SpringBootApplication
public class AlibabaNacosDiscoveryClientFeginApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaNacosDiscoveryClientFeginApplication.class, args);
    }

}
