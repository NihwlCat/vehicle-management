package br.pedro.program.dto;

import br.pedro.program.entities.Vehicle;
import br.pedro.program.services.validation.VehicleValidData;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.Null;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@VehicleValidData
public class VehicleDTO implements Serializable {

    @Null (message = "Impossível definir um ID")
    private Integer id;
    private String brand;
    private String model;
    private String yearAndFuel;
    private Integer rotKey;
    private boolean rotation;
    private String price;

    public VehicleDTO(){}

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
