package com.example.Client_Server;

import com.example.Client_Server.Service.Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientServerApplication implements CommandLineRunner
{
	private Service service;

	public ClientServerApplication(Service service)
	{
		this.service = service;
	}
	public static void main(String[] args) {
		SpringApplication.run(ClientServerApplication.class, args);
	}

	@Override
	public void run(String ...args) throws Exception
	{
		System.out.println(service.getStockPrice("2"));
		service.SubscribeStockPrice("2");
		service.PlaceBulkOrders();
		service.StartLiveTrading();
	}
}
