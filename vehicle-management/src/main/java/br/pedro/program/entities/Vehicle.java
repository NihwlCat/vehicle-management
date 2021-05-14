package br.pedro.program.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "tb_vehicle")
public class Vehicle {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String brand;
    private String model;
    private Integer year;

    @ManyToOne
    @JoinColumn (name = "owner_cpf")
    private User user;

    public Vehicle () {
    }

    public Vehicle(Integer id, String brand, String model, Integer year, User user) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return id.equals(vehicle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
