package com.CarsLtd;

import com.CarsLtd.stocks.dto.StocksDto;
import com.CarsLtd.stocks.exception.StockException;
import com.CarsLtd.stocks.services.StocksServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import javax.validation.Valid;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)// it use random port not a specific port
public class StockControllerTest {
    @Value(value = "${local.server.port}")
    private int port;
 @Autowired
 private StocksServices stocksServices;
    @Autowired
    private TestRestTemplate restTemplate;
    @BeforeEach
    public void init(){//if we use beforeEach this method will execute before each method
        this.restTemplate.postForObject("http://localhost:" + port + "/addtogarage", new StocksDto(12222, "Dodge", "Srt", 48880.0, 28.9, 5200.0, "Petrol"),StocksDto.class);
        // in this method we have to declare the port and address to test and create Object
    }
    @Test
    public  void getCarBySerialNoTest() throws Exception{
        StocksDto stocksDto = this.restTemplate.getForObject("http://localhost:"+port+"/getcar/12222",StocksDto.class);
        assertEquals("Dodge",stocksDto.getCarBrand());  // individual testing for the obj is work correctly or not
    }
    @Test  // Exception Test for the SerialNumber
    public  void getCarBySerialNoExceptionTest() throws Exception{
        String exceptionMessage = this.restTemplate.getForObject("http://localhost:"+port+"/getcar/12225",String.class);
        assertEquals("No Serial Number Found",exceptionMessage);  // individual testing for the obj is wok correctly or not
    }
@Test
public void updateCarSerialNoTest() throws StockException {  //check for update on serial
    this.restTemplate.postForObject("http://localhost:"+port+"/serialupdate/12222/newserial/202368/su",null,StocksDto.class);
    StocksDto stocksDto = this.restTemplate.getForObject("http://localhost:"+port+"/getcar/202368",StocksDto.class);
    assertEquals(202368, stocksDto.getserialNo());
}
@Test
    public void updateCarSerialNoTestThrows() throws StockException {  //Exception checking if wrong serial enters
        String exception =this.restTemplate.postForObject("http://localhost:"+port+"/serialupdate/1010101/newserial/202368/su",null,String.class);
    assertEquals("No Car Found in Garage",exception);
    }
@Test
    public void scrapCarTest()  {  //delete and check the exception message  ( For Efficiency we Two Operation are in same Method)
     this.restTemplate.delete("http://localhost:" + port + "/scrap/12222", 12222,String.class);//delete the object
    String exceptionMessage = this.restTemplate.getForObject("http://localhost:"+port+"/getcar/12222",String.class);
    assertEquals("No Serial Number Found",exceptionMessage);//check exception if wrong value Id given or deleted Id given
}

@Test
    public void updateTest(){
        StocksDto stocksDto = new StocksDto(12222, "Dodge", "Challenger Srt", 48880.0, 40.0, 5200.0, "Petrol");
         this.restTemplate.put("http://localhost:" + port +"/updategarage",stocksDto );
    }
    }


