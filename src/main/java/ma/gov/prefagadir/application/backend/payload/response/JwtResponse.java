package ma.gov.prefagadir.application.backend.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JwtResponse {
     private String token;
     private String type;
     private String username;
     private List<String> authorities;
     private boolean authenticated;
}
