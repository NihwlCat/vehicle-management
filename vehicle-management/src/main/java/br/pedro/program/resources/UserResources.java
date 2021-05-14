package br.pedro.program.resources;

import br.pedro.program.dto.UserDTO;
import br.pedro.program.entities.User;
import br.pedro.program.services.UserService;
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

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll (){
        List<UserDTO> users = service.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping (value = "/{cpf}") // Value equivale ao atributo a ser pesquisado
    public ResponseEntity<UserDTO> findByCpf (@PathVariable String cpf){
        return ResponseEntity.ok().body(service.findByCpf(cpf));
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert (@RequestBody UserDTO user) {
        service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{cpf}").buildAndExpand(user.getCpfId()).toUri();
        return ResponseEntity.created(uri).body(user);

    }
}
