package net.chain.test.grpc;


import io.grpc.stub.StreamObserver;
import net.gichain.test.grpc.dto.Result;


/**
 * @Author MsencSir
 * @Date 2020/1/11 15:54
 */

public class GrpcResponse {

    public static void succResponse(String data, StreamObserver<Result> responseObserver){
        Result grpcResult = Result.newBuilder()
                .setCode("0")
                .setMessage("ok")
                .setSuccess("true")
                .setData(data).build();
        responseObserver.onNext(grpcResult);
        responseObserver.onCompleted();
    }

    public static void failResponse(String remark, StreamObserver<Result> responseObserver){
        Result grpcResult = Result.newBuilder()
                .setCode("-2")
                .setMessage(remark)
                .setSuccess("false")
                .setData("")
                .build();
        responseObserver.onNext(grpcResult);
        responseObserver.onCompleted();
    }
}
