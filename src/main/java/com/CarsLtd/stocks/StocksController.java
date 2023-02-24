package com.CarsLtd.stocks;

import com.CarsLtd.stocks.dto.StocksDto;
import com.CarsLtd.stocks.exception.StockException;
import com.CarsLtd.stocks.services.StocksServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class StocksController {
  @Autowired
    private StocksServices stocksServices;

  //Exception used---->
  @GetMapping("/getcar/{sNo}")
    public StocksDto getCarInGarage( @PathVariable Integer sNo)throws StockException {//you can give integer in the html link
       return stocksServices.getCarBySerialNo(sNo);   //service method
  }

  @PostMapping("/addtogarage")
    public StocksDto regCarInGarage(@Valid @RequestBody StocksDto stocksDto){//you need a body to give the data
       return stocksServices.regCarInGarage(stocksDto);
  }
  //   /serialupdate/125567/newserial/202368/su
@PostMapping("/serialupdate/{sNo}/newserial/{newSno}/su")
public StocksDto updateCarSerialNo( @PathVariable Integer sNo,  @PathVariable  Integer newSno) throws StockException{
    return stocksServices.updateCarSerialNo(sNo,newSno);
}
  @PutMapping("/updategarage")
  public StocksDto updateCarInGarage (  @RequestBody StocksDto stocksDto){//you need a body to give the data
    return stocksServices.regCarInGarage(stocksDto);
  }
@DeleteMapping("/scrap/{sNo}")
  public StocksDto scrapCarInGarage( @PathVariable Integer sNo)throws StockException{
    return stocksServices.scrapCarInGarage(sNo);
}

}
