package com.CarsLtd;

import com.CarsLtd.stocks.dto.StocksDto;
import com.CarsLtd.stocks.exception.StockException;
import com.CarsLtd.stocks.services.StocksServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

// StocksDto regCarInGarage(StocksDto stocksDto);
//         StocksDto getCarBySerialNo(Integer serialNo)throws StockException;
//         StocksDto updateCarSerialNo(Integer serialNo, Integer value) throws StockException;
//         StocksDto updateCarInGarage (StocksDto stocksDto);
//         StocksDto scrapCarInGarage(Integer serialNo) throws StockException;
@SpringBootTest
public class StockServicesTest {

    @Autowired
    private StocksServices stocksServices;
    @BeforeEach   //same as it initialize before Each Method
    public void init(){
        stocksServices.regCarInGarage(new StocksDto(12222,"Ford","Mustang",48880.0,28.9,5200.0,"Petrol"));
        stocksServices.regCarInGarage(new StocksDto(12245,"Dodge","Srt",62500.0,35.0,4800.0,"Petrol"));

    }

    @Test
    public void getCarBySerialNo() throws StockException {
             StocksDto stocksDto = stocksServices.getCarBySerialNo(12222);
             assertEquals("Mustang",stocksServices.getCarBySerialNo(12222).getCarModel());
    }

    @Test
    void regCarInGarage() throws StockException {   //manual registration Test
        StocksDto stocksDto = new StocksDto(125567, "Ford", "Lightning", 48880.0, 28.9, 5200.0, "Electric");
        this.stocksServices.regCarInGarage(stocksDto);
        assertEquals(125567, stocksServices.getCarBySerialNo(125567).getserialNo());
    }
    @Test
    void getCarBySerialNoThrowsStockExceptionTest() {   //testing the Throw Exception is working or not
         assertThrows(StockException.class, () -> this.stocksServices.getCarBySerialNo(1212));
    }
    @Test
    void getCarBySerialNoTest() throws StockException {
        assertEquals(28.9,stocksServices.getCarBySerialNo(12222).getMileage());
    }
    @Test
    void updateCarInGarage() throws StockException {   //an update were given
        StocksDto stocksDto= stocksServices.getCarBySerialNo(12222);
        stocksDto.setCc(400.0);
        stocksServices.updateCarInGarage(stocksDto);
        assertEquals(400.0,stocksServices.getCarBySerialNo(12222).getCc());
    }
    @Test
    void updateCarSerialNoTest() throws StockException {  //update the  serial Number Only
        stocksServices.updateCarSerialNo(12222,125568);
        assertEquals(125568,stocksServices.getCarBySerialNo(125568).getserialNo());
    }
    @Test
    void updateCarSerialNoTestThrows() throws StockException {  //Exception Checking
       assertThrows(StockException.class,()->stocksServices.updateCarSerialNo(123456,123456));
    }

    @Test
    void scrapCarInGarageTest() throws StockException { //check for scrap
        stocksServices.scrapCarInGarage(12222);

        //Also we can use try catch block to note the responses and after deleting it must return exception
        // then the message is equal to my assert and the test is passed
        try{
            stocksServices.scrapCarInGarage(12222);
        }catch (StockException stockException){
            assertEquals("No Car Found in Garage",stockException.getMessage());
        }
    }
    @Test
    void scrapCarInGarageTestThrow(){  //check for exception
        assertThrows(StockException.class,()->stocksServices.scrapCarInGarage(12228));
    }


}
