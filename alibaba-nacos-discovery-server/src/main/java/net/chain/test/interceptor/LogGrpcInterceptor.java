package net.chain.test.interceptor;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * springBootgrpc
 * 2019/12/4 15:35
 *
 * @author ck
 * @since
 **/
@Slf4j
@Component
public class LogGrpcInterceptor implements ServerInterceptor {


    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata,
                                                                 ServerCallHandler<ReqT, RespT> serverCallHandler) {
        log.debug("调用服务接口："+serverCall.getMethodDescriptor().getServiceName());
        return serverCallHandler.startCall(serverCall, metadata);
    }

}