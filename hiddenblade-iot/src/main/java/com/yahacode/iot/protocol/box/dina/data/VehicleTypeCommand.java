package com.yahacode.iot.protocol.box.dina.data;

public class VehicleTypeCommand extends BaseCommand {

    Integer brandId = 1;

    Integer productId = 2;

    Integer yearId = 3;

    Integer gearType = 4;

    Double capacity = 1.6;

    public VehicleTypeCommand() {
    }

    public VehicleTypeCommand(String deviceId) {
        super(deviceId);
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getYearId() {
        return yearId;
    }

    public void setYearId(Integer yearId) {
        this.yearId = yearId;
    }

    public Integer getGearType() {
        return gearType;
    }

    public void setGearType(Integer gearType) {
        this.gearType = gearType;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }
}
