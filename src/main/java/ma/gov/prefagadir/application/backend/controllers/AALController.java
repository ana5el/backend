package ma.gov.prefagadir.application.backend.controllers;

import ma.gov.prefagadir.application.backend.models.AAL;
import ma.gov.prefagadir.application.backend.models.TypeAAL;
import ma.gov.prefagadir.application.backend.payload.request.AddAALRequest;
import ma.gov.prefagadir.application.backend.services.AALService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aal")
@CrossOrigin(origins = "*")
public class AALController {

    @Autowired
    private AALService service;

    @GetMapping(value = {"","/"})
    public List<AAL> getAllAal(){
        return service.getAll();
    }

    @GetMapping("/types")
    public List<TypeAAL> getTypes(){
        return service.getTypes();
    }

    @GetMapping("/{id}")
    public AAL getAalById(@PathVariable Long id){

        return service.getAAL(id);
    }

    @PostMapping
    public AAL addAAL(@RequestBody AddAALRequest request){
        return service.create(request);
    }

    @PutMapping("/{id}")
    public AAL updateAAL(@PathVariable Long id, @RequestBody AAL aal){
        return service.updateAal(id,aal);
    }

    @DeleteMapping("/{id}")
    private void deleteAAL(@PathVariable Long id){
        service.delete(id);
    }

}
