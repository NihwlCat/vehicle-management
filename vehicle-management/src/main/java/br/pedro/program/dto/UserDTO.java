package br.pedro.program.dto;

import br.pedro.program.entities.User;
import br.pedro.program.entities.Vehicle;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserDTO {
    private String cpfId;
    private String name;
    private String email;
    private Instant birthDate;

    private List<VehicleDTO> vehicles = new ArrayList<>();

    public UserDTO (User user){
        cpfId = user.getCpfId();
        name = user.getName();
        email = user.getEmail();
        birthDate = user.getBirthDate();
    }

    public UserDTO (User user, Set<Vehicle> vehicles){
        this(user);
        vehicles.forEach(v -> this.vehicles.add(new VehicleDTO(v)));
    }

    public UserDTO (User user, List<VehicleDTO> vehicles){
        this(user);
        this.vehicles = vehicles;
    }

    public List<VehicleDTO> getVehicles() {
        return vehicles;
    }

    public String getCpfId() {
        return cpfId;
    }

    public void setCpfId(String cpfId) {
        this.cpfId = cpfId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }
}
