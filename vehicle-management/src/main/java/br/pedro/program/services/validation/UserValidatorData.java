package br.pedro.program.services.validation;

import java.util.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.pedro.program.dto.UserDTO;
import br.pedro.program.repositories.UserRepository;
import br.pedro.program.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

public class UserValidatorData implements ConstraintValidator<UserValidData, UserDTO> {

    @Autowired
    private UserRepository repository;

    @Override
    public boolean isValid(UserDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if(repository.findByEmail(dto.getEmail()) != null){
            list.add(new FieldMessage("email","Email já existe no banco de dados"));
        }

        if(repository.findById(dto.getCpfId()).isPresent()){
            list.add(new FieldMessage("cpfId","CPF já existe no banco de dados"));
        }

        for(FieldMessage f : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(f.getMessage()).addPropertyNode(f.getName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}