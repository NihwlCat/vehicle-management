package br.pedro.program.dto;

import br.pedro.program.entities.Vehicle;

public class VehicleDTO {
    private Integer id;
    private String brand;
    private String model;
    private String yearAndFuel;
    private Integer rotKey;
    private boolean rotation;
    private String price;

    public VehicleDTO(Vehicle vehicle){
        id = vehicle.getId();
        brand = vehicle.getBrand();
        model = vehicle.getModel();
        yearAndFuel = vehicle.getYearAndFuel();
    }

    public void setYearAndFuel(String yearAndFuel) {
        this.yearAndFuel = yearAndFuel;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYearAndFuel() {
        return yearAndFuel;
    }

    public void setYear(String yearAndFuel) {
        this.yearAndFuel = yearAndFuel;
    }

    public Integer getRotKey() {
        return rotKey;
    }

    public void setRotKey(Integer rotKey) {
        this.rotKey = rotKey;
    }

    public boolean isRotation() {
        return rotation;
    }

    public void setRotation(boolean rotation) {
        this.rotation = rotation;
    }
}
