package ma.gov.prefagadir.application.backend.controllers;

import ma.gov.prefagadir.application.backend.models.Point;
import ma.gov.prefagadir.application.backend.models.Zone;
import ma.gov.prefagadir.application.backend.payload.request.ListPointsRequest;
import ma.gov.prefagadir.application.backend.services.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
@CrossOrigin(origins = "*")

public class ZoneController {
    @Autowired
    private ZoneService service;

    @GetMapping(value = {"","/"})
    public List<Zone> getAll(){
        return service.getAll();
    }

    @PostMapping(value = {"","/"})
    public void ceate(@RequestBody ListPointsRequest request){
         service.addZone(request);
    }

    @GetMapping("/{id}")
    public Zone getById(@PathVariable Long id){
        return service.getById(id);
    }
}
