package ma.gov.prefagadir.application.backend.services;

import ma.gov.prefagadir.application.backend.models.Citoyen;
import ma.gov.prefagadir.application.backend.repository.CitoyenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitoyenService {
    @Autowired
    private CitoyenRepository citoyenRepository;

    public Citoyen insert(Citoyen citoyen){
        return citoyenRepository.save(citoyen);
    }

    public List<Citoyen> getAll(){
        return citoyenRepository.findAll();
    }

    public Citoyen getByCin(String cin){
        return citoyenRepository.getById(cin);
    }
}
