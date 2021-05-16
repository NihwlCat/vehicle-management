package br.pedro.program.entities;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_user")
public class User {

    @Column (unique = true)
    @Id
    private String cpfId;

    private String name;

    @Column (unique = true)
    private String email;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant birthDate;

    @OneToMany (mappedBy = "user")
    Set<Vehicle> vehicles = new HashSet<>();

    public User (){ }

    public User(String cpfId, String name, String email, Instant birthDate) {
        this.cpfId = cpfId;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
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

    public String getCpfId() {
        return cpfId;
    }

    public void setCpfId(String cpfId) {
        this.cpfId = cpfId;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return cpfId.equals(user.cpfId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpfId);
    }
}
