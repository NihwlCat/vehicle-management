package br.pedro.program.resources;

import br.pedro.program.dto.UserDTO;
import br.pedro.program.dto.VehicleDTO;
import br.pedro.program.services.UserService;
import br.pedro.program.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping (value = "/users")
public class Resource {

    @Autowired
    private UserService service;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping (value = "/{cpf}")
    public ResponseEntity<UserDTO> findByCpf (@PathVariable String cpf){
        return ResponseEntity.ok().body(service.findByCpf(cpf));
    }

    @GetMapping (value = "/{cpf}/vehicles")
    public ResponseEntity<List<VehicleDTO>> findVehicles (@PathVariable String cpf){
        return ResponseEntity.ok().body(service.findVehicles(cpf));
    }

    @PostMapping
    public ResponseEntity<UserDTO> insertUser (@Valid @RequestBody UserDTO user) {
        service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{cpf}").buildAndExpand(user.getCpfId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }
    @PostMapping (value = "/{cpf}/vehicles")
    public ResponseEntity<VehicleDTO> insertVehicle (@Valid @RequestBody VehicleDTO vehicle, @PathVariable String cpf){
        vehicleService.insert(cpf,vehicle);
        return ResponseEntity.status(HttpStatus.valueOf(201)).body(vehicle);
    }
}
