package com.example.Stocking_Market.Repository;

import com.example.Stocking_Market.Models.StockMarket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMarketRepository extends JpaRepository<StockMarket,Integer>
{
    StockMarket findByStockSymbol(String Symbol);
}
