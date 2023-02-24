package com.CarsLtd;

import com.CarsLtd.stocks.dto.StocksDto;
import com.CarsLtd.stocks.exception.StockException;
import com.CarsLtd.stocks.repository.StocksRepository;
import com.CarsLtd.stocks.services.StocksServices;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.springframework.stereotype.Repository;

import java.util.function.Function;

//            StocksDto addCarInGarage(StocksDto stocksDto);
//             StocksDto getCar(Integer serialNo);
//             StocksDto updateCarSerial(Integer serialNo, Integer value);
//             StocksDto updateCar (StocksDto stocksDto );
//             StocksDto deleteCarInGarage(Integer serialNo);
@SpringBootTest
public class MockRepositoryTest {
    @Autowired
    private StocksServices stocksServices;
    @MockBean
    private StocksRepository stocksRepository;
    // MockBean is used to create a proxy Repository, it means if we test the data, and
    //it cannot go to the server or not stored in local repository
    //if we not use the MockBean the test data are stored on local repository or database

//    @BeforeEach   //same as it initialize before Each Method
//    public void init(){
//        stocksServices.regCarInGarage(new StocksDto(12222,"Ford","Mustang",48880.0,28.9,5200.0,"Petrol"));
//        stocksServices.regCarInGarage(new StocksDto(12245,"Dodge","Srt",62500.0,35.0,4800.0,"Petrol"));
//
//    }
    @Test
    public void getcar() throws StockException {
        //sample object to test instead of creating Real one
        //Mock Objects or Proxy Objects
        //it is on When Then Pattern
        given(this.stocksRepository.getCar(12222))
                .willReturn(new StocksDto(12222, "Dodge", "Srt", 48880.0, 28.9, 5200.0, "Petrol"));
        assertEquals("Dodge",this.stocksServices.getCarBySerialNo(12222).getCarBrand());
    }

    @Test
    public void getCarThrowExceptionTest() throws StockException{
        given(this.stocksRepository.getCar(12232))
                .willReturn(null);
        assertThrows(StockException.class,()->this.stocksServices.getCarBySerialNo(200));
    }

@Test
    public void  addCarInGarageTest() throws StockException {
        StocksDto stocksDto = new StocksDto(125567, "Dodge", "Srt", 48880.0, 28.9, 5200.0, "Petrol");
    given((this.stocksRepository.getCar(125567)))
            .willReturn(stocksDto);
   assertEquals("Srt",this.stocksServices.getCarBySerialNo(125567).getCarModel());
}
@Test
    public void  updateCarSerialTest() throws StockException {   //serial update of the car
    StocksDto stocksDto = new StocksDto(12222, "Dodge", "Srt", 48880.0, 28.9, 5200.0, "Petrol");
    StocksDto stocksDto1 = new StocksDto(125567, "Dodge", "Srt", 48880.0, 28.9, 5200.0, "Petrol");
    given(this.stocksRepository.getCar(12222))
            .willReturn(stocksDto);
 when(this.stocksRepository.updateCarSerial(12222,125567))
         .thenReturn(stocksDto1);
    assertEquals(125567,this.stocksServices.updateCarSerialNo(12222,125567).getserialNo());
}
    @Test
    public void  updateCarSerialTestThrow() throws StockException {   // Exception while enter the Wrong serial
        StocksDto stocksDto = new StocksDto(12222, "Dodge", "Srt", 48880.0, 28.9, 5200.0, "Petrol");
        StocksDto stocksDto1 = new StocksDto(125567, "Dodge", "Srt", 48880.0, 28.9, 5200.0, "Petrol");
        given(this.stocksRepository.getCar(12222))
                .willReturn(stocksDto);
        when(this.stocksRepository.updateCarSerial(12222,125567))
                .thenReturn(stocksDto1);
        assertThrows(StockException.class,()->this.stocksServices.updateCarSerialNo(125567,125567).getserialNo());
    }
    @Test
    public void updateCarTest(){
        StocksDto stocksDto = new StocksDto(125567, "Dodge", "Srt", 48880.0, 28.9, 5200.0, "Petrol");
        StocksDto stocksDto1 = new StocksDto(125567, "Ford", "Mustang", 50000.0, 40.0, 5200.0, "Petrol");
//        when(this.stocksRepository.getCar(125567))
//                .thenReturn(stocksDto=stocksDto1);
        when(this.stocksRepository.updateCar(stocksDto1))
                .thenReturn(stocksDto=stocksDto1);
        assertEquals("Mustang",this.stocksServices.updateCarInGarage(stocksDto1).getCarModel());
    }

   @Test
    public void scrapCarInGarageTest()throws StockException{  //scrap the car
       StocksDto stocksDto = new StocksDto(125567, "Dodge", "Srt", 48880.0, 28.9, 5200.0, "Petrol");
       when(this.stocksRepository.getCar(125567))
               .thenReturn(stocksDto);
       when(this.stocksRepository.deleteCarInGarage(125567))
               .thenReturn(null);
       assertEquals(null,this.stocksServices.scrapCarInGarage(125567));
   }
    @Test
    public void scrapCarInGarageTestThrow()throws StockException{  //shows Exception while enter wrong value
        StocksDto stocksDto = new StocksDto(125567, "Dodge", "Srt", 48880.0, 28.9, 5200.0, "Petrol");
        when(this.stocksRepository.getCar(125567))
                .thenReturn(stocksDto);
        when(this.stocksRepository.deleteCarInGarage(125567))
                .thenReturn(null);
        assertThrows(StockException.class,()-> this.stocksServices.scrapCarInGarage(125590));

    }
}