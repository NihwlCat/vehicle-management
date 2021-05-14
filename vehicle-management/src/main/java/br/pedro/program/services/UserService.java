package br.pedro.program.services;

import br.pedro.program.dto.UserDTO;
import br.pedro.program.entities.User;
import br.pedro.program.repositories.UserRepository;
import br.pedro.program.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional (readOnly = true)
    public List<UserDTO> findAll (){
        List<User> users = repository.findAll();
        return users.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Transactional (readOnly = true)
    public UserDTO findByCpf(String cpf){
        User user = repository.findById(cpf).orElseThrow(() -> new EntityNotFoundException("Entity not found!"));
        return new UserDTO(user,user.getVehicles());
    }

    @Transactional
    public void insert (UserDTO dto){
        User user = new User (dto.getName(), dto.getEmail(), dto.getCpfId(), dto.getBirthDate());
        repository.save(user);
    }
}
