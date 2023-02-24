package com.CarsLtd.stocks.repository;

import com.CarsLtd.stocks.dto.StocksDto;
import com.CarsLtd.stocks.exception.StockException;

public interface StocksRepository {
    StocksDto addCarInGarage(StocksDto stocksDto);
    StocksDto getCar(Integer serialNo);
    StocksDto updateCarSerial(Integer serialNo, Integer value);
    StocksDto updateCar (StocksDto stocksDto );
    StocksDto deleteCarInGarage(Integer serialNo);
}
