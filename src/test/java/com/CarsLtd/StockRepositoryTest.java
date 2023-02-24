package com.CarsLtd;

import com.CarsLtd.stocks.dto.StocksDto;
import com.CarsLtd.stocks.exception.StockException;
import com.CarsLtd.stocks.repository.StocksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

//    StocksDto addCarInGarage(StocksDto stocksDto);
//    StocksDto getCar(Integer serialNo);
//    StocksDto updateCarSerial(Integer serialNo, Integer value);
//    StocksDto updateCar (StocksDto stocksDto);
//    StocksDto deleteCarInGarage(Integer serialNo);
@SpringBootTest
public class StockRepositoryTest {
    @Autowired
    private StocksRepository stocksRepository;
    @BeforeEach   //same as it initialize before Each Method
    public void init(){
        stocksRepository.addCarInGarage(new StocksDto(12222,"Ford","Mustang",48880.0,28.9,5200.0,"Petrol"));
    }

  @Test
  public void getCarTest(){
        assertEquals("Ford",stocksRepository.getCar(12222).getCarBrand());
  }

  @Test
  public void updateCarTest(){
        StocksDto stocksDto = stocksRepository.getCar(12222);
        stocksDto.setCarModel("Challenger SRT");
        stocksDto.setCarBrand("Dodge");
        stocksRepository.updateCar(stocksDto);
        assertEquals("Challenger SRT", stocksRepository.getCar(12222).getCarModel());
  }
  @Test
  public void deleteCarInGarageTest(){
        stocksRepository.deleteCarInGarage(12222);
  }
    @Test
    public void updateCarSerialTest()throws StockException{
        StocksDto stocksDto = stocksRepository.updateCarSerial(12222,125567);
        assertEquals(125567,stocksDto.getserialNo());
    }

}
