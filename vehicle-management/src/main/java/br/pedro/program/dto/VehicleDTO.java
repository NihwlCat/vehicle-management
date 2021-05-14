package br.pedro.program.dto;

import br.pedro.program.entities.Vehicle;

public class VehicleDTO {
    private Integer id;
    private String brand;
    private String model;
    private Integer year;
    private Integer rotKey;
    private boolean rotation;

    private UserDTO user;

    public VehicleDTO(){}

    public VehicleDTO(Vehicle vehicle){
        id = vehicle.getId();
        brand = vehicle.getBrand();
        model = vehicle.getModel();
        year = vehicle.getYear();
    }

    public VehicleDTO(Vehicle vehicle, UserDTO user){
        this(vehicle);
        this.user = user;
    }
    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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
