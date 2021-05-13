package net.chain.test.controller.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import net.chain.test.annotations.TransactionEx;
import net.chain.test.dto.Result;
import net.chain.test.mapper.UserMapper;
import net.chain.test.user.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author MsencSir
 * @Date 2020/5/26 17:45
 */
@Slf4j
@Component
public class UserService {

    @Resource
    UserMapper userMapper;

    //merge 不支持事务 测试结果：不支持事务
    @TransactionEx
    public void insert(){
        User user1 = new User(null,"HHH");
        User user2 = new User(null,"LLLL");
        User user3 = new User(null,"UUUU");

        userMapper.insert(user1);
        userMapper.insert(user2);
        userMapper.insert(user3);
        String ss =null;
        ss.length();
    }

    public Result select(int pageSize){

        IPage<User> userIPage = userMapper.selectPage(new Page<>(0,pageSize,false),Wrappers.lambdaQuery(new User()));

        List<User> users = userIPage.getRecords();

        log.info("获取到的用户列表:"+users);
        return Result.succ(users);
    }

}
