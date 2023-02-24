package com.CarsLtd.stocks.services;

import com.CarsLtd.stocks.dto.StocksDto;
import com.CarsLtd.stocks.exception.StockException;
import com.sun.jdi.IntegerValue;

public interface StocksServices {
    StocksDto regCarInGarage(StocksDto stocksDto);
    StocksDto getCarBySerialNo(Integer serialNo)throws StockException;
    StocksDto updateCarSerialNo(Integer serialNo, Integer value) throws StockException;
    StocksDto updateCarInGarage (StocksDto stocksDto );
    StocksDto scrapCarInGarage(Integer serialNo) throws StockException;

}
