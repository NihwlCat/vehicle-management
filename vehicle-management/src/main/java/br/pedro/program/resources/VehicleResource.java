package br.pedro.program.resources;

import br.pedro.program.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (value = "/vehicles")
public class VehicleResource {

    @Autowired
    private VehicleService service;

}
