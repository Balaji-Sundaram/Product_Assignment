package com.CarsLtd.stocks.services;

import com.CarsLtd.stocks.dto.StocksDto;
import com.CarsLtd.stocks.exception.StockException;
import com.CarsLtd.stocks.repository.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service

public class StocksServicesImplement implements StocksServices {
  @Autowired
  private StocksRepository stocksRepository;
    @Override
    public StocksDto regCarInGarage(StocksDto stocksDto) {
           return stocksRepository.addCarInGarage(stocksDto);  //repository linked
    }
    @Override
    public StocksDto getCarBySerialNo(Integer serialNo) throws StockException {
        StocksDto stocksDto =  stocksRepository.getCar(serialNo);
   if(stocksDto == null)
       throw new StockException("No Serial Number Found");
   else
       return stocksRepository.getCar(serialNo);
    }

    @Override
    public StocksDto updateCarSerialNo(Integer serialNo,Integer value) throws StockException {
        StocksDto stocksDto =  stocksRepository.getCar(serialNo);
        if(stocksDto == null)
            throw new StockException("No Car Found in Garage");
        else
            return stocksRepository.updateCarSerial(serialNo,value);
    }

    @Override
    public StocksDto updateCarInGarage(StocksDto stocksDto ) {
         return stocksRepository.updateCar(stocksDto );
    }

    @Override
    public StocksDto scrapCarInGarage(Integer serialNo) throws StockException {
        StocksDto stocksDto =  stocksRepository.getCar(serialNo);
        if(stocksDto == null)
            throw new StockException("No Car Found in Garage");
        else
            return stocksRepository.deleteCarInGarage(serialNo);
    }

    }

