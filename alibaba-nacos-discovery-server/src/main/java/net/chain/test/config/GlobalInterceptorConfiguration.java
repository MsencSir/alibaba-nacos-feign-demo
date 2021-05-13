package net.chain.test.config;

import net.devh.boot.grpc.server.interceptor.GlobalServerInterceptorConfigurer;
import net.devh.boot.grpc.server.interceptor.GlobalServerInterceptorRegistry;
import net.chain.test.interceptor.LogGrpcInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * springBootgrpc
 * 2019/12/4 15:34
 *
 * @author ck
 * @since
 **/
@Configuration
public class GlobalInterceptorConfiguration {

    @Resource
    private LogGrpcInterceptor logGrpcInterceptor;

    @Bean
    public GlobalServerInterceptorConfigurer globalInterceptorConfigurerAdapter() {
        return new GlobalServerInterceptorConfigurer() {
            @Override
            public void addServerInterceptors(GlobalServerInterceptorRegistry registry) {
                registry.addServerInterceptors(logGrpcInterceptor);
            }
        };
    }
}
