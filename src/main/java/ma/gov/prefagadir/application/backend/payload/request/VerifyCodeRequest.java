package ma.gov.prefagadir.application.backend.payload.request;

import lombok.Data;

@Data
public class VerifyCodeRequest {
    private String username;
    private String code;
}
