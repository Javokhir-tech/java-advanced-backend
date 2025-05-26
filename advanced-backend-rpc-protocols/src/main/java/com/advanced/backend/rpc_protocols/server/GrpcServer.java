package com.advanced.backend.rpc_protocols.server;

import com.advanced.backend.rpc_protocols.service.PingPongServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {
    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(8080)
                .addService(new PingPongServiceImpl())
                .build();

        System.out.println("gRPC Server started on port 8080");
        server.start();
        server.awaitTermination();
    }
}