package ma.gov.prefagadir.application.backend.services;

import ma.gov.prefagadir.application.backend.models.Privilege;
import ma.gov.prefagadir.application.backend.repository.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepository;


    public Privilege addPrivilege(Privilege p){
        privilegeRepository.save(p);
        return p;
    }

    public void updatePrivilege(Long id, Privilege p){
        Privilege pFromDb = privilegeRepository.findById(id).get();
        pFromDb.setName(p.getName());
        pFromDb.setLabelAr(p.getLabelAr());
        pFromDb.setLabelFr(p.getLabelFr());
        pFromDb.setDescription(p.getDescription());
        privilegeRepository.save(pFromDb);
    }

    public List<Privilege> getAllPrivileges(){
        return privilegeRepository.findAll();
    }

    public void deletePrivilege(Long id){
        privilegeRepository.deleteById(id);
    }

    public Privilege getPrivilege(Long id){
        return privilegeRepository.findById(id).get();
    }
}
