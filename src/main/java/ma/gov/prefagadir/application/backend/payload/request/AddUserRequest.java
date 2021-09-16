package ma.gov.prefagadir.application.backend.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddUserRequest {

    private String username;
    private String password;
    private String profile;
    private boolean active;

}
