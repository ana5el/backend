package ma.gov.prefagadir.application.backend.controllers;

import ma.gov.prefagadir.application.backend.models.Privilege;
import ma.gov.prefagadir.application.backend.services.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/privileges")
@CrossOrigin(origins = "*")
public class PrivilegeController {

    @Autowired
    private PrivilegeService privilegeService;

    @GetMapping("/{id}")
    public Privilege getPrivilege(@PathVariable Long id){
        return privilegeService.getPrivilege(id);
    }

    @GetMapping(value = {"","/"})
    public List<Privilege> getAllPrivileges(){
        return  privilegeService.getAllPrivileges();
    }

    @PostMapping(value = {"","/"})
    public void addPrivilege(@RequestBody Privilege privilege){
        privilegeService.addPrivilege(privilege);
    }

    @DeleteMapping("/{id}")
    public void deletePrivilege(@PathVariable Long id){
        privilegeService.deletePrivilege(id);
    }

    @PutMapping("/{id}")
    public void updatePrivilege(@PathVariable Long id, @RequestBody Privilege privilege){
         privilegeService.updatePrivilege(id, privilege);
    }
}
