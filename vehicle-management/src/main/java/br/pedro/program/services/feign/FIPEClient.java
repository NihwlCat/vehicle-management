package br.pedro.program.services.feign;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${feign.name}", url = "${feign.url}")
public interface FIPEClient {

    @RequestMapping(method = RequestMethod.GET, value="carros/marcas/{brand}/modelos/{model}/anos/{yearAndFuel}")
    String getFipeInformations(@PathVariable String brand, @PathVariable String model, @PathVariable String yearAndFuel);

    @RequestMapping(method = RequestMethod.GET, value = "carros/marcas/{brand}/modelos")
    Response getFipeBrand(@PathVariable String brand);

    @RequestMapping(method = RequestMethod.GET, value = "carros/marcas/{brand}/modelos/{model}/anos")
    Response getFipeModel(@PathVariable String brand, @PathVariable String model);

    @RequestMapping(method = RequestMethod.GET, value = "carros/marcas/{brand}/modelos/{model}/anos/{yearAndFuel}")
    Response getFipeYearAndFuel(@PathVariable String brand, @PathVariable String model, @PathVariable String yearAndFuel);
}

