package ma.gov.prefagadir.application.backend.services;

import ma.gov.prefagadir.application.backend.models.Point;
import ma.gov.prefagadir.application.backend.models.Zone;
import ma.gov.prefagadir.application.backend.models.ZonePoint;
import ma.gov.prefagadir.application.backend.payload.request.ListPointsRequest;
import ma.gov.prefagadir.application.backend.repository.PointRepository;
import ma.gov.prefagadir.application.backend.repository.ZoneRepository;
import ma.gov.prefagadir.application.backend.repository.ZonePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private ZonePointRepository zonePointRepository;


    public List<Zone> getAll(){
        return  zoneRepository.findAll();
    }

    public void addZone(ListPointsRequest request){
        Zone zone = zoneRepository.save(new Zone());
        int length = request.getPoints().size();
        IntStream.range(0,length).forEach(index ->{
            Point point = request.getPoints().get(index);
            ZonePoint zp = new ZonePoint();
            zp.setOrder(index);
            zp.setZone(zone);
            if(pointRepository.existsByLatLng(point.getLat(), point.getLng())){
                zp.setPoint(pointRepository.findByLatLng(point.getLat(), point.getLng()));
            }else{
                Point p = pointRepository.save(point);
                zp.setPoint(p);
            }
            ZonePoint zonePoint = zonePointRepository.save(zp);
            System.out.println("NEW ZONE POINT  : " + zonePoint.getId());

        });
    }

    public Zone getById(Long id){
        return zoneRepository.findById(id).get();
    }


    public void delete(Long id){
        zoneRepository.deleteById(id);
    }

}
