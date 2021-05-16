package br.pedro.program.services.validation;

import java.util.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.pedro.program.dto.VehicleDTO;
import br.pedro.program.resources.exceptions.FieldMessage;
import br.pedro.program.services.feign.FIPEClient;
import org.springframework.beans.factory.annotation.Autowired;

public class VehicleValidatorData implements ConstraintValidator<VehicleValidData, VehicleDTO> {

    @Autowired
    private FIPEClient fipe;

    @Override
    public boolean isValid(VehicleDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if(fipe.getFipeBrand(dto.getBrand()).status() == 404){
            list.add(new FieldMessage("brand", "Marca incompatível com a base da FIPE"));
        }else{
            if(fipe.getFipeModel(dto.getBrand(),dto.getModel()).status() == 404){
                list.add(new FieldMessage("model", "Modelo incompatível com a base da FIPE"));
            }else{
                if(fipe.getFipeYearAndFuel(dto.getBrand(),dto.getModel(),dto.getYearAndFuel()).status() == 404){
                    list.add(new FieldMessage("yearAndFuel","Ano-combustível incompatível com a base da FIPE"));
                }
            }
        }

        for(FieldMessage f : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(f.getMessage()).addPropertyNode(f.getName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}
