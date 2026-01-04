package com.example.Client_Server.Service;


import com.StockMarket.grpc.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;

@org.springframework.stereotype.Service
public class Service
{
    // - Connects to gRPC server defined in properties
    @GrpcClient("StockMarketStub")
    private StockMarketGrpc.StockMarketBlockingStub NewStub;

    // - Connects to gRPC server defined in properties
    @GrpcClient("StockMarketStub")
    private StockMarketGrpc.StockMarketStub StreamStub;


    public StockMarketResponse getStockPrice(String Symbol)
    {
        StockMarketRequest stockMarketRequest = StockMarketRequest.newBuilder().setStockSymbol(Symbol).build();
        return NewStub.getStockMarketDetails(stockMarketRequest);
    }

    public void SubscribeStockPrice(String Symbol)
    {
        StockMarketRequest stockMarketRequest = StockMarketRequest.newBuilder()
                .setStockSymbol(Symbol)
                .build();

        StreamStub.subscribeStockPrice(stockMarketRequest, new StreamObserver<StockMarketResponse>() {
            @Override
            public void onNext(StockMarketResponse stockMarketResponse)
            {
                System.out.println(stockMarketResponse.getStockSymbol() + " " + stockMarketResponse.getStockPrice());
            }

            @Override
            public void onError(Throwable throwable)
            {
                System.out.println("An Error has occurred");
            }

            @Override
            public void onCompleted()
            {
                System.out.println("Completed");
            }
        });
    }

    public void PlaceBulkOrders()
    {
        StreamObserver<StockMarketResponseOrders> responseObserver = new StreamObserver<StockMarketResponseOrders>() {
            @Override
            public void onNext(StockMarketResponseOrders stockMarketResponseOrders)
            {
                System.out.println(stockMarketResponseOrders.getOrder());
            }

            @Override
            public void onError(Throwable throwable)
            {
                System.out.println("Errors" + throwable.getMessage());
            }

            @Override
            public void onCompleted()
            {
                System.out.println("Completed");
            }
        };

        StreamObserver<StockMarketRequest> requestObserver = StreamStub.orderBulk(responseObserver);

        try
        {
            requestObserver.onNext
                    (
                            StockMarketRequest.newBuilder()
                                    .setStockSymbol("2")
                                    .build()
                    );
            requestObserver.onNext
                    (
                            StockMarketRequest.newBuilder()
                                    .setStockSymbol("2")
                                    .build()
                    );
            requestObserver.onNext
                    (
                            StockMarketRequest.newBuilder()
                                    .setStockSymbol("2")
                                    .build()
                    );
            requestObserver.onNext
                    (
                            StockMarketRequest.newBuilder()
                                    .setStockSymbol("2")
                                    .build()
                    );
            requestObserver.onNext
                    (
                            StockMarketRequest.newBuilder()
                                    .setStockSymbol("2")
                                    .build()
                    );
            requestObserver.onCompleted();
        }
        catch (Exception ex)
        {
            requestObserver.onError(ex);
        }
    }

    public void StartLiveTrading ()
    {
        StreamObserver<OrderQuantity> requestObservers = StreamStub.liveTrading(new StreamObserver<OrderResponse>() {
            @Override
            public void onNext(OrderResponse orderResponse) {
                System.out.println(orderResponse);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error");
            }

            @Override
            public void onCompleted() {
                System.out.println("Completed");
            }
        });

        for(int i = -1; i<10; i++)
        {
            OrderQuantity orderQuantity = OrderQuantity.newBuilder()
                    .setOrderQuantity(i)
                    .build();

            requestObservers.onNext(orderQuantity);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        requestObservers.onCompleted();
    }
}
