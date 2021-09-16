package ma.gov.prefagadir.application.backend.payload.request;

import lombok.Data;
import ma.gov.prefagadir.application.backend.models.Logement;
import ma.gov.prefagadir.application.backend.models.Point;

import java.util.List;

@Data
public class AddLogementRequest {

    private String type;
    private List<Point> points;
    private String rue;
    private String quartier;
    private int numero;
    private double superficie;
    private int nbAppts;
    private String nomAr;
    private String nomFr;

}
