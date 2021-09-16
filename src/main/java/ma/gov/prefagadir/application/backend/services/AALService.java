package ma.gov.prefagadir.application.backend.services;

import ma.gov.prefagadir.application.backend.models.AAL;
import ma.gov.prefagadir.application.backend.models.Point;
import ma.gov.prefagadir.application.backend.models.TypeAAL;
import ma.gov.prefagadir.application.backend.models.Zone;
import ma.gov.prefagadir.application.backend.payload.request.AddAALRequest;
import ma.gov.prefagadir.application.backend.repository.AALRepository;
import ma.gov.prefagadir.application.backend.repository.PointRepository;
import ma.gov.prefagadir.application.backend.repository.TypeAALRepository;
import ma.gov.prefagadir.application.backend.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AALService {
    @Autowired
    private AALRepository aalRepository;

    @Autowired
    private TypeAALRepository typeAALRepository;

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    public List<AAL> getAll(){
        return aalRepository.findAll();
    }

    public  List<TypeAAL> getTypes(){
        return typeAALRepository.findAll();
    }

    public AAL updateAal(Long id, AAL aal){
        AAL aalFromDb = aalRepository.findById(id).get();
        aalFromDb.setTypeAAL(aal.getTypeAAL());
        aalFromDb.setTel(aal.getTel());
        aalFromDb.setLabelFr(aal.getLabelFr());
        aalFromDb.setLabelAr(aal.getLabelAr());
        aal.setZone(aal.getZone());
        aalRepository.save(aalFromDb);
        return  aalFromDb;
    }

    public void delete(Long id){
        aalRepository.deleteById(id);
    }

    public AAL getAAL(Long id){
        return aalRepository.findById(id).get();
    }

    public AAL create(AddAALRequest request){
        Zone zone = new Zone();
        request.getPoints().forEach(point -> {
            if(pointRepository.existsByLatLng(point.getLat(), point.getLng())){
            }else{
                Point p = pointRepository.save(point);
            }
        });
        Zone zoneGeo = zoneRepository.save(zone);
        AAL aal  = new AAL(request.getLibelleAr(), request.getLibelleFr(), request.getTel(), request.getTypeAAL(), request.getSup(), zoneGeo);
        return  aalRepository.save(aal);

    }
}
