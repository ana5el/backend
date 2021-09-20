package ma.gov.prefagadir.application.backend.controllers;

import ma.gov.prefagadir.application.backend.models.Citoyen;
import ma.gov.prefagadir.application.backend.services.CitoyenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citoyen")
@CrossOrigin(origins = "*")
public class CitoyenController {

    @Autowired
    private CitoyenService citoyenService;

    @GetMapping(value = {"/",""})
    public List<Citoyen> getAll(){
        return citoyenService.getAll();
    }

    @GetMapping("/{cin}")
    public Citoyen getByCin(@PathVariable String cin){
        return citoyenService.getByCin(cin);
    }

    @PostMapping(value = {"/",""})
    public Citoyen addCitoyen(@RequestBody Citoyen citoyen){
        return citoyenService.insert(citoyen);
    }
}
