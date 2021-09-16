package ma.gov.prefagadir.application.backend.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import ma.gov.prefagadir.application.backend.models.Point;

import java.util.List;

@Data
@NoArgsConstructor
public class ListPointsRequest {

    private List<Point> points;
}
