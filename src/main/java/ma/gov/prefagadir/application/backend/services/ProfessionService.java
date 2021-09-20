package ma.gov.prefagadir.application.backend.services;

import ma.gov.prefagadir.application.backend.models.Profession;
import ma.gov.prefagadir.application.backend.repository.ProfessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionService {
    @Autowired
    private ProfessionRepository professionRepository;


    public List<Profession> getAll(){
        return professionRepository.findAll();
    }

    public Profession getById(Long id){
        return professionRepository.getById(id);
    }
}
