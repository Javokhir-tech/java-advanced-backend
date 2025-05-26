package com.advanced.backend.rpc_protocols.service;

import io.grpc.stub.StreamObserver;

public class PingPongServiceImpl extends PingPongGrpc.PingPongImplBase {
    @Override
    public void ping(PingRequest request, StreamObserver<PongResponse> responseObserver) {
        String message = request.getMessage();
        PongResponse response = PongResponse.newBuilder()
                .setMessage("Pong: " + message)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}