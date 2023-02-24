package com.CarsLtd.stocks.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class StocksDto { //POJO or Data Transfer Object
    //Validation will be given
   @NotNull(message = "Serial Number Not be Null")      //Based on the Business Requirement need to Write the Validation
    private  Integer serialNo;
    @Pattern(regexp = "[a-zA-Z]{2,8}",message = "Brand Contain only String")
    private String carBrand;
    private String carModel;
    private Double price;
    private Double mileage;
    private Double cc;
    private String fuelType;


    public StocksDto() {
    }

    public StocksDto(Integer serialNo, String carBrand, String carModel, Double price, Double mileage, Double cc, String fuelType) {
        this. serialNo= serialNo;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.price = price;
        this.mileage = mileage;
        this.cc = cc;
        this.fuelType = fuelType;
    }

    public Integer getserialNo() {
        return serialNo;
    }

    public void setserialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public Double getCc() {
        return cc;
    }

    public void setCc(Double cc) {
        this.cc = cc;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return "StocksDto{" +
                "serialNo=" + serialNo +
                ", carBrand='" + carBrand + '\'' +
                ", carModel='" + carModel + '\'' +
                ", price=" + price +
                ", mileage=" + mileage +
                ", cc=" + cc +
                ", fuelType='" + fuelType + '\'' +
                '}';
    }
}
