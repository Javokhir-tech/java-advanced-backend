import grpc
import pingpong_pb2
import pingpong_pb2_grpc

def run():
    with grpc.insecure_channel('localhost:8080') as channel:
        stub = pingpong_pb2_grpc.PingPongStub(channel)
        response = stub.Ping(pingpong_pb2.PingRequest(message="Ping"))
        print("Response:", response.message)

if __name__ == "__main__":
    run()