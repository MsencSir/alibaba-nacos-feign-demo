package net.chain.test.controller.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import net.chain.test.annotations.TransactionEx;
import net.chain.test.dto.Result;
import net.chain.test.mapper.CoinsMapper;
import net.chain.test.project.Coins;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @Author MsencSir
 * @Date 2020/1/8 17:13
 */
@Slf4j
@Service
public class CoinService {
    @Resource
    private CoinsMapper coinsMapper;

    @TransactionEx
    public String addCoins() {
        Coins coin1 = new Coins();
        coin1.setName("usnd");
        coin1.setCoin("USDT");
        coin1.setChainType("USDT");
        coin1.setIconUrl("dsdsd");
        Coins coin2 = new Coins();
        coin2.setCoin("DC");
        coin2.setChainType("DC");
        coin2.setIconUrl("dsdsd");
        Set<Coins> coinsSet = new HashSet<>();
        coinsSet.add(coin1);
        coinsSet.add(coin2);
        boolean flag = coinsMapper.bathInsertCoins(coinsSet);
        Coins coins = new Coins("MMM","MMM","MMM","MMM");
        coinsMapper.insert(coins);
        String aa = null;
        //System.out.println(aa.length()); //检测事务
        if(flag){
            return "插入成功";
        }
        return "插入失败";
    }

    public Result getCoins(int pageSize){
        log.info("请求条数："+pageSize);
        IPage<Coins> coinsIPage = coinsMapper.selectPage(new Page<>(1,pageSize),Wrappers.lambdaQuery(new Coins()));
        List<Coins> coins = coinsIPage.getRecords();
        log.info("获取到的币种列表:"+coins);
        return Result.succ(coins);
    }
}
