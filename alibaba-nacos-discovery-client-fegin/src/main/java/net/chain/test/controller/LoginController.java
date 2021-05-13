package net.chain.test.controller;

import com.test.fegin.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import net.chain.test.config.Redis;
import net.chain.test.controller.service.CoinService;
import net.chain.test.controller.service.UserService;
import net.chain.test.dto.Result;
import net.chain.test.grpc.GrpcClientUserService;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author MsencSir
 * @Date 2020/5/12 15:44
 */
@Slf4j
@RefreshScope
@RestController
public class LoginController {

    @Resource
    LoginService loginService;

    @Resource
    CoinService coinService;

    @Resource
    GrpcClientUserService grpcClientUserService;

    @Resource
    Redis redis;

    @Resource
    UserService userService;

    @GetMapping("/login")
    public String login(@RequestParam("name") String name){
        log.info("收到登录1请求："+name);
        return loginService.login(name);
    }

    @GetMapping("/login2")
    public String login2(@RequestParam("name") String name){
        log.info("收到登录2请求："+name);
        return loginService.login2(name);
    }

    @GetMapping("/getUser")
    public Result getUser(@RequestParam("name") String name){
        log.info("收到获取用户信息的请求："+name);
        return grpcClientUserService.getUserInfo(name);
    }
    @RequestMapping(value = "/addCoins",method = {RequestMethod.GET})
    public String addCoins() {
        log.info("测试nacos配置mysql参数");
        log.info("批量添加币种请求进入。。。。");
        return coinService.addCoins();
    }
    @RequestMapping(value = "/getCoins",method = {RequestMethod.GET})
    public Result getCoins(@RequestParam("pageSize") Integer pageSize) {
        return coinService.getCoins(pageSize);
    }

    @RequestMapping(value = "/testRedis",method = {RequestMethod.GET})
    public String testRedis() {
        log.info("测试nacos配置redis参数");
        return redis.get("test");
    }
    //测试merge分表 插入
    @GetMapping("/insert")
    public Result insert (){
        userService.insert();
        return Result.succ("成功");
    }
    //测试merge分表 查询
    @GetMapping("/select")
    public Result select (@RequestParam("pageSize") Integer pageSize){

        return userService.select(pageSize);
    }

}
