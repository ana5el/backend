package ma.gov.prefagadir.application.backend.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import ma.gov.prefagadir.application.backend.models.AAL;
import ma.gov.prefagadir.application.backend.models.Point;
import ma.gov.prefagadir.application.backend.models.TypeAAL;

import java.util.List;

@Data
@NoArgsConstructor
public class AddAALRequest {

    private String libelleAr;
    private String libelleFr;
    private String tel;
    private TypeAAL typeAAL;
    private AAL sup;
    private List<Point> points;
}
