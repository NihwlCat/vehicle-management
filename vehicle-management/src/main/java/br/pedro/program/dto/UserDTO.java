package br.pedro.program.dto;

import br.pedro.program.entities.User;
import br.pedro.program.services.validation.UserValidData;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@UserValidData
public class UserDTO implements Serializable {

    @NotBlank
    private String cpfId;

    private String name;

    @Email(message = "Deve ser inserido um email")
    @NotBlank
    private String email;

    private Instant birthDate;

    private List<VehicleDTO> vehicles = new ArrayList<>();

    public UserDTO (){ }

    public UserDTO (User user){
        cpfId = user.getCpfId();
        name = user.getName();
        email = user.getEmail();
        birthDate = user.getBirthDate();
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
