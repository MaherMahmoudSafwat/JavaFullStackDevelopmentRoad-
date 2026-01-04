package com.example.Stocking_Market.Models;

import jakarta.persistence.*;

@Entity
public class StockMarket
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "StockMarket_Id")
    @SequenceGenerator(name = "StockMarket_Id",sequenceName = "StockMarket_Id",allocationSize = 1)
    Integer id;

    String stockSymbol;

    double stockPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }
}
