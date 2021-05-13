package net.chain.test.grpc.service;

import com.alibaba.fastjson.JSON;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import net.gichain.test.grpc.dto.Result;
import net.gichain.test.grpc.dto.UserInfoRequest;
import net.gichain.test.grpc.user.UserServiceGrpc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.HashMap;

import static net.chain.test.grpc.GrpcResponse.failResponse;
import static net.chain.test.grpc.GrpcResponse.succResponse;

/**
 * @Author MsencSir
 * @Date 2020/5/22 15:02
 */
@Slf4j
@RefreshScope
@GrpcService
public class UserService extends UserServiceGrpc.UserServiceImplBase {

    @Value("${conf.nacosName}")
    private String nacosName;
    @Override
    public void getUserInfo(UserInfoRequest request, StreamObserver<Result> responseObserver) {

        try {

            String name = request.getName();
            log.info("name:"+name);
            log.info("nacos:"+nacosName);
            HashMap<String,String> map = new HashMap<>();
            map.put("name",name);
            map.put("age","10");
            map.put("gender","男");

            succResponse(JSON.toJSONString(map),responseObserver);

        }catch (Exception e){
            failResponse("系统异常",responseObserver);
        }
    }
}
