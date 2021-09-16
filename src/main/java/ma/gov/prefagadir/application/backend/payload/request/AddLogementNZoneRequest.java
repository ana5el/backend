package ma.gov.prefagadir.application.backend.payload.request;

import lombok.*;
import ma.gov.prefagadir.application.backend.models.Point;
import ma.gov.prefagadir.application.backend.models.Quartier;
import ma.gov.prefagadir.application.backend.models.TypeLogement;

import java.util.List;

@Data
@NoArgsConstructor
public class AddLogementNZoneRequest {

    private String info;
    private Quartier quartier;
    private String rue;
    private double superficie;
    private TypeLogement type;
    private List<Point> points;
}
