package com.advanced.backend.rpc_protocols.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        PingPongGrpc.PingPongBlockingStub stub = PingPongGrpc.newBlockingStub(channel);

        PingRequest request = PingRequest.newBuilder()
                .setMessage("Ping")
                .build();

        PongResponse response = stub.ping(request);
        System.out.println("Response: " + response.getMessage());

        channel.shutdown();
    }
}
