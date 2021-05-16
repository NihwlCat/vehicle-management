package br.pedro.program.services.feign;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties (ignoreUnknown = true)
public class FIPEObject implements Serializable {

    @JsonProperty("Valor")
    private String value;

    @JsonProperty("Marca")
    private String brand;

    @JsonProperty("Modelo")
    private String model;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
