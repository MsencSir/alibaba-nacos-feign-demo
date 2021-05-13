package net.chain.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author MsencSir
 * @Date 2020/5/12 15:40
 */
@Slf4j
@RefreshScope
@RestController
public class LoginController {

    @Value("${conf.debug:false}")
    private Boolean debug;
    @Value("${conf.nacosName}")
    private String nacosName;

    @GetMapping("/login")
    public String login(@RequestParam("name") String name){
        log.info("收到登录1请求："+name);
        log.info("是否登录："+debug);
        log.info("nacos:"+nacosName);
        try {

            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return name+"登录1成功:"+debug;
    }

    @GetMapping("/login2")
    public String login2(@RequestParam("name") String name){
        log.info("收到登录2请求："+name);
        log.info("是否登录："+debug);
        log.info("nacos:"+nacosName);
        return name+"登录2成功:"+debug;
    }
}
