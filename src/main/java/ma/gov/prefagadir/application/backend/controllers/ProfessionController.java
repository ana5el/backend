package ma.gov.prefagadir.application.backend.controllers;

import ma.gov.prefagadir.application.backend.models.Profession;
import ma.gov.prefagadir.application.backend.services.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profession")
@CrossOrigin(origins = "*")

public class ProfessionController {
    @Autowired
    private ProfessionService professionService;

    @GetMapping(value = {"/", ""})
    public List<Profession> getAll(){
        return professionService.getAll();
    }

    @GetMapping("/{id}")
    public Profession getById(@PathVariable Long id){
        return professionService.getById(id);
    }

}
