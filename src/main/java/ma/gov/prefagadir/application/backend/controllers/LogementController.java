package ma.gov.prefagadir.application.backend.controllers;

import ma.gov.prefagadir.application.backend.models.*;
import ma.gov.prefagadir.application.backend.payload.request.AddLogementRequest;
import ma.gov.prefagadir.application.backend.repository.ImmebleRepository;
import ma.gov.prefagadir.application.backend.repository.MaisonRepository;
import ma.gov.prefagadir.application.backend.repository.QuartierRepository;
import ma.gov.prefagadir.application.backend.repository.VillaRepository;
import ma.gov.prefagadir.application.backend.services.LogementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logements")
@CrossOrigin(origins = "*")
public class LogementController {

    @Autowired
    private LogementService logementService;

    @Autowired
    private MaisonRepository maisonRepository;

    @Autowired
    private VillaRepository villaRepository;

    @Autowired
    private ImmebleRepository immebleRepository;

    @Autowired
    private QuartierRepository quartierRepository;

    @GetMapping(value = {"","/"})
    public List<Logement> findAll() {
        return  logementService.getAllLogements();
    }

    @GetMapping(value = "/maisons")
    public  List<Maison>  getAllMaisons(){
        return maisonRepository.findAll();
    }

    @GetMapping(value = "/villas")
    public  List<Villa>  getAllVillas(){
        return villaRepository.findAll();
    }

    @GetMapping(value = "/immeubles")
    public  List<Immeuble>  getAllImmeubles(){
        return immebleRepository.findAll();
    }

    @GetMapping("/types")
    public List<TypeLogement> getAllTypes(){
        return logementService.getAllTypes();
    }

    @GetMapping("/quartiers")
    public List<Quartier> getAllQuartiers(){
        return quartierRepository.findAll();
    }

    @PostMapping(value = {"", "/"})
    public  ResponseEntity<?> addLogement(@RequestBody AddLogementRequest request){
        return  ResponseEntity.ok(logementService.addLogement(request));
    }
}
