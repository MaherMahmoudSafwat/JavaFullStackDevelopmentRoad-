package com.example.Stocking_Market.Service;

import com.StockMarket.grpc.*;
import com.example.Stocking_Market.Models.StockMarket;
import com.example.Stocking_Market.Repository.StockMarketRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.coyote.Request;

import java.security.Timestamp;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@GrpcService
public class StockTradingServiceImpl extends StockMarketGrpc.StockMarketImplBase
{
    private StockMarketRepository stockMarketRepository;

    public StockTradingServiceImpl(StockMarketRepository stockMarketRepository)
    {
        this.stockMarketRepository = stockMarketRepository;
    }
    @Override
    public void getStockMarketDetails(StockMarketRequest request, StreamObserver<StockMarketResponse> responseObserver)
    {
        String Symbol = request.getStockSymbol();
        StockMarket stockMarket = stockMarketRepository.findByStockSymbol(Symbol);

        StockMarketResponse stockMarketResponse = StockMarketResponse.newBuilder()
                .setStockSymbol(stockMarket.getStockSymbol())
                .setStockPrice(stockMarket.getStockPrice())
                .build();

        responseObserver.onNext(stockMarketResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void subscribeStockPrice(StockMarketRequest request, StreamObserver<StockMarketResponse> responseObserver)
    {
        String Symbol = request.getStockSymbol();

        for(int i = 0; i < 10; i++)
        {
            StockMarketResponse stockMarketResponse = StockMarketResponse.newBuilder()
                    .setStockSymbol(Symbol)
                    .setStockPrice(new Random().nextDouble(200))
                    .build();

            responseObserver.onNext(stockMarketResponse);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<StockMarketRequest> orderBulk(StreamObserver<StockMarketResponseOrders> responseObserver)
    {
        return new StreamObserver<StockMarketRequest>() {
            int Order=0;
            @Override
            public void onNext(StockMarketRequest stockMarketRequest)
            {
                Order++;
                System.out.println(stockMarketRequest);
            }

            @Override
            public void onError(Throwable throwable)
            {
                System.out.println("Error");
            }

            @Override
            public void onCompleted()
            {
                StockMarketResponseOrders stockMarketResponseOrders = StockMarketResponseOrders.newBuilder()
                        .setOrder(Order)
                        .build();

                responseObserver.onNext(stockMarketResponseOrders);
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<OrderQuantity> liveTrading(StreamObserver<OrderResponse> responseObserver) {

        return new StreamObserver<OrderQuantity>() {
            @Override
            public void onNext(OrderQuantity orderQuantity)
            {
                System.out.println("Order " + orderQuantity);
                String status ="EXECUTED";
                if(orderQuantity.getOrderQuantity() >= 0)
                {
                    OrderResponse orderResponse = OrderResponse.newBuilder()
                            .setStatus("EXECUTED")
                            .build();
                    responseObserver.onNext(orderResponse);
                }
                else
                {
                    OrderResponse orderResponse = OrderResponse.newBuilder()
                            .setStatus("CANCELED OR PENDING")
                            .build();
                    responseObserver.onNext(orderResponse);
                }
            }

            @Override
            public void onError(Throwable throwable)
            {
                System.out.println("Errors has been occurred");
            }

            @Override
            public void onCompleted()
            {
                responseObserver.onCompleted();
                System.out.println("Completed");
            }
        };

    }
}
