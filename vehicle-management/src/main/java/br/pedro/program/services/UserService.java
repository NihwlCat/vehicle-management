package br.pedro.program.services;

import br.pedro.program.dto.UserDTO;
import br.pedro.program.dto.VehicleDTO;
import br.pedro.program.entities.User;
import br.pedro.program.repositories.UserRepository;
import br.pedro.program.services.exceptions.DataErrorException;
import br.pedro.program.services.feign.FIPEClient;
import br.pedro.program.services.feign.FIPEObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private FIPEClient fipe;

    private FIPEObject jsonToObject(String json){
        ObjectMapper mapper = new ObjectMapper();

        try {
            FIPEObject obj = mapper.readValue(json,FIPEObject.class);
            return mapper.readValue(json,FIPEObject.class);
        } catch (JsonProcessingException e) {
            throw new DataErrorException("Error mapping JSON to OBJECT");
        }
    }

    private static boolean tryRotation(int day, int rotKey){
        int auxDay;

        if(rotKey < 2)
            auxDay = Calendar.MONDAY;
        else if (rotKey < 4)
            auxDay = Calendar.TUESDAY;
        else if (rotKey < 6)
            auxDay = Calendar.WEDNESDAY;
        else if (rotKey < 8)
            auxDay = Calendar.THURSDAY;
        else
            auxDay = Calendar.FRIDAY;

        return auxDay == day;
    }

    @Transactional (readOnly = true)
    public UserDTO findByCpf(String cpf){
        User user = repository.findById(cpf).orElseThrow(() -> new DataErrorException("Entity not found!"));
        List<VehicleDTO> vehicles = user.getVehicles().stream().map(VehicleDTO::new).collect(Collectors.toList());

        vehicles.forEach(vehicle -> {
            vehicle.setRotKey(Integer.parseInt(vehicle.getYearAndFuel().substring(3,4)));

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int day = cal.get(Calendar.DAY_OF_WEEK);

            String json;

            try {
                json = fipe.getFipeInformations(vehicle.getBrand(), vehicle.getModel(), vehicle.getYearAndFuel());
            } catch (FeignException e){
                throw new DataErrorException("404 - Not found [GET] Feign");
            }

            vehicle.setPrice(jsonToObject(json).getValue());

            vehicle.setRotation(tryRotation(day,vehicle.getRotKey()));
        });

        return new UserDTO(user,vehicles);
    }


    @Transactional (readOnly = true)
    public List<VehicleDTO> findVehicles (String cpf){
        User user = repository.findById(cpf).orElseThrow(() -> new DataErrorException("Entity not found!"));
        List<VehicleDTO> vehicles = user.getVehicles().stream().map(VehicleDTO::new).collect(Collectors.toList());

        vehicles.forEach(vehicle -> {
            String json;

            try {
                json = fipe.getFipeInformations(vehicle.getBrand(), vehicle.getModel(), vehicle.getYearAndFuel());
            } catch (FeignException e){
                throw new DataErrorException("404 - Not found [GET] Feign");
            }

            FIPEObject obj = jsonToObject(json);

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int day = cal.get(Calendar.DAY_OF_WEEK);

            vehicle.setBrand(obj.getBrand());
            vehicle.setModel(obj.getModel());
            vehicle.setPrice(obj.getValue());
            vehicle.setRotation(tryRotation(day,Integer.parseInt(vehicle.getYearAndFuel().substring(3,4))));

        });

        return vehicles;
    }

    @Transactional
    public void insert (UserDTO dto){
        User user = new User (dto.getCpfId(), dto.getName(), dto.getEmail(), dto.getBirthDate());
        repository.save(user);
    }
}
