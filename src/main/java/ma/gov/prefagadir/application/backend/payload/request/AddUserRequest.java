package ma.gov.prefagadir.application.backend.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddUserRequest {

    /*
    private String username;
    private String password;
    private String profile;
    private boolean active;
     */

    private String login;
    private String password;
    private Long profileId ;
    private Long gradeId;
    private String nom;
    private String prenom;
    private String cin;
    private String tel;
    private Long aalId;
}
