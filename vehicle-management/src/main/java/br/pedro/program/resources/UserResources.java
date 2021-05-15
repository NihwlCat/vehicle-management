package br.pedro.program.resources;

import br.pedro.program.dto.UserDTO;
import br.pedro.program.dto.VehicleDTO;
import br.pedro.program.services.UserService;
import br.pedro.program.services.VehicleService;
import br.pedro.program.services.feign.FIPEClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping (value = "/users")
public class UserResources {

    @Autowired
    private UserService service;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private FIPEClient fipe;


    @GetMapping (value = "/{cpf}") // Value equivale ao atributo a ser pesquisado
    public ResponseEntity<UserDTO> findByCpf (@PathVariable String cpf){
        return ResponseEntity.ok().body(service.findByCpf(cpf));
    }

    // Lista veículos de um CPF específico
    @GetMapping (value = "/{cpf}/vehicles")
    public ResponseEntity<List<VehicleDTO>> findVehicles (@PathVariable String cpf){
        UserDTO user = service.findByCpf(cpf);

        return ResponseEntity.ok().body(user.getVehicles());
    }

    @PostMapping
    public ResponseEntity<UserDTO> insertUser (@RequestBody UserDTO user) {
        service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{cpf}").buildAndExpand(user.getCpfId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }
    @PostMapping (value = "/{cpf}/vehicles")
    public ResponseEntity<VehicleDTO> insertVehicle (@RequestBody VehicleDTO vehicle, @PathVariable String cpf){
        return null;
    }

}
