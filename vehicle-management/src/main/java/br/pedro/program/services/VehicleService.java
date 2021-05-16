package br.pedro.program.services;

import br.pedro.program.dto.VehicleDTO;
import br.pedro.program.entities.User;
import br.pedro.program.entities.Vehicle;
import br.pedro.program.repositories.UserRepository;
import br.pedro.program.repositories.VehicleRepository;
import br.pedro.program.services.exceptions.DataErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void insert (String cpf, VehicleDTO dto){
        User user = userRepository.findById(cpf).orElseThrow(() -> new DataErrorException("Entity not found! "));
        Vehicle vehicle = new Vehicle(null, dto.getBrand(), dto.getModel(), dto.getYearAndFuel(),user);
        repository.save(vehicle);
    }
}
