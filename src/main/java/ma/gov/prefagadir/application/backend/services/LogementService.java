package ma.gov.prefagadir.application.backend.services;

import ma.gov.prefagadir.application.backend.models.*;
import ma.gov.prefagadir.application.backend.payload.request.AddLogementNZoneRequest;
import ma.gov.prefagadir.application.backend.payload.request.AddLogementRequest;
import ma.gov.prefagadir.application.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class LogementService {

    @Autowired
    private LogementRepository logementRepository;

    @Autowired
    private MaisonRepository maisonRepository;

    @Autowired
    private VillaRepository villaRepository;

    @Autowired
    private  ImmebleRepository immebleRepository;

    @Autowired
    private TypeLogementRepository typeLogementRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private ZonePointRepository zonePointRepository;

    public List<TypeLogement> getAllTypes(){
        return typeLogementRepository.findAll();
    }

    public List<Logement> getAllLogements(){
        return logementRepository.findAll();
    }

    public Logement addLogement(AddLogementRequest request){
        Zone zone = zoneRepository.save(new Zone());
        int len = request.getPoints().size();
        IntStream.range(0, len).forEach(i -> {
            Point p = request.getPoints().get(i);
            ZonePoint zp = new ZonePoint();
            zp.setOrder(i);
            zp.setZone(zone);
            if(pointRepository.existsByLatLng(p.getLat(), p.getLng())){
                zp.setPoint(pointRepository.findByLatLng(p.getLat(), p.getLng()));
            }else {
                zp.setPoint(pointRepository.save(p));
            }
			zonePointRepository.save(zp);

        });

        if(request.getType().equals("maison")){
                Maison maison = new Maison();
                maison.setNumero(request.getNumero());
                maison.setQuartier(request.getQuartier());
                maison.setRue(request.getRue());
                //maison.setSuperficie(request.getSuperficie());
                maison.setZone(zone);
               return  maisonRepository.save(maison);
        }
        else if(request.getType().equals("villa")){
            Villa villa = new Villa();
            villa.setNumero(request.getNumero());
            villa.setQuartier(request.getQuartier());
            villa.setRue(request.getRue());
            villa.setZone(zone);
            //villa.setSuperficie(request.getSuperficie());
            return villaRepository.save(villa);
        }
        else if(request.getType().equals("immeuble")){
            Immeuble immeuble = new Immeuble();
            immeuble.setNumero(request.getNumero());
            immeuble.setQuartier(request.getQuartier());
            immeuble.setRue(request.getRue());
            immeuble.setNbAppts(request.getNbAppts());
            immeuble.setNomAr(request.getNomAr());
            immeuble.setNomFr(request.getNomFr());
            immeuble.setZone(zone);
            return immebleRepository.save(immeuble);
        }
        return null;
    }

    /*public Logement addLogement(AddLogementNZoneRequest request){
        Zone zone = zoneRepository.save(new Zone());
        int length = request.getPoints().size();
        IntStream.range(0,length).forEach(i -> {
            Point p = request.getPoints().get(i);
            ZonePoint zp = new ZonePoint();
            zp.setOrder(i);
            zp.setZone(zone);
            if(pointRepository.existsByLatLng(p.getLat(), p.getLng())){
                zp.setPoint(pointRepository.findByLatLng(p.getLat(), p.getLng()));
            }else{
                Point point = pointRepository.save(p);
                zp.setPoint(point);
            }
            zonePointRepository.save(zp);
        });
         return logementRepository.save(new Logement(request.));

    }
*/
}
