package net.chain.test.grpc;

import lombok.extern.slf4j.Slf4j;
import net.chain.test.dto.Result;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.gichain.test.grpc.dto.UserInfoRequest;
import net.gichain.test.grpc.user.UserServiceGrpc;
import org.springframework.stereotype.Service;

/**
 * @Author MsencSir
 * @Date 2020/5/22 15:19
 */
@Slf4j
@Service
public class GrpcClientUserService {

    @GrpcClient("grpc-service")
    private UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    public Result<String> getUserInfo(String name){
        UserInfoRequest request = UserInfoRequest.newBuilder().setName(name).build();
        net.gichain.test.grpc.dto.Result result = userServiceBlockingStub.getUserInfo(request);
        return checkResult(result);
    }

    private Result<String> checkResult(net.gichain.test.grpc.dto.Result result){
        if("0".equals(result.getCode())){
            return Result.succ(result.getData());
        }else {
            return Result.fail(result.getMessage());
        }
    }
}
