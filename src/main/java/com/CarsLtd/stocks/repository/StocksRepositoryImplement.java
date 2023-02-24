package com.CarsLtd.stocks.repository;

import com.CarsLtd.stocks.dto.StocksDto;
import com.CarsLtd.stocks.exception.StockException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class StocksRepositoryImplement implements StocksRepository{
    Map<Integer,StocksDto> stocksDtoMap = new HashMap<>();  //hashamp
    @Override
    public StocksDto addCarInGarage(StocksDto stocksDto) {

       return stocksDtoMap.put(stocksDto.getserialNo(),stocksDto);
    }

    @Override
    public StocksDto getCar(Integer serialNo)   {
//        try{
//            return  stocksDtoMap.get(serialNo)
//        }catch (StockException s){
//            return  s.getMessage();
//        }
//        if (stocksDtoMap.containsKey(serialNo))
//            return stocksDtoMap.get(serialNo);
//        else
//            throw new StockException("No Serial Number Found");
 return  stocksDtoMap.get(serialNo);
    }

    @Override
    public StocksDto updateCarSerial(Integer serialNo, Integer value) {
        StocksDto stocksDto1 =  stocksDtoMap.get(serialNo);
        stocksDto1.setserialNo(value);
        stocksDtoMap.remove(serialNo);
          stocksDtoMap.put(value,stocksDto1);//inserting the new value
        return stocksDtoMap.get(value);
    }

    @Override
    public StocksDto updateCar(StocksDto stocksDto) {

        return stocksDtoMap.replace(stocksDto.getserialNo(),stocksDto);
    }

    @Override
    public StocksDto deleteCarInGarage(Integer serialNo) {
        return stocksDtoMap.remove(serialNo);
    }
}
