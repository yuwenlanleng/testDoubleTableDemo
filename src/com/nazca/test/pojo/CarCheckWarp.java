/*
 * CarCheckWarp.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-10-17 16:32:30
 */
package com.nazca.test.pojo;

import java.io.Serializable;

/**
 *
 * @author Hu Qin<huqin@yzhtech.com>
 */
public class CarCheckWarp  implements Serializable{
    private static final long serialVersionUID = 6393940008275559061L;
    
    private String driverName;
    private String carPlateNumber;
    private String carCarColor;
    private String carCarModel;
    
    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getCarPlateNumber() {
        return carPlateNumber;
    }

    public void setCarPlateNumber(String carPlateNumber) {
        this.carPlateNumber = carPlateNumber;
    }

    public String getCarCarColor() {
        return carCarColor;
    }

    public void setCarCarColor(String carCarColor) {
        this.carCarColor = carCarColor;
    }

    public String getCarCarModel() {
        return carCarModel;
    }

    public void setCarCarModel(String carCarModel) {
        this.carCarModel = carCarModel;
    }
    
}
