package ma.gov.prefagadir.application.backend.utils;

import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrDataFactory;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static dev.samstevens.totp.util.Utils.getDataUriForImage;

@Service
public class TotpUtils {

    public static final Logger logger = LoggerFactory.getLogger(TotpUtils.class);

    @Autowired
    private SecretGenerator secretGenerator;

    @Autowired
    private QrGenerator qrGenerator;

    @Autowired
    private QrDataFactory qrDataFactory;

    @Autowired
    private CodeVerifier codeVerifier;

    //todo generateSecret()
    public String generateSecret(){
        return secretGenerator.generate();
    }

    //todo getUriForImage(String secret)
    public String getUriFromImage(String secret) throws QrGenerationException {
        /* QrData data = qrDataFactory.newBuilder()
                .secret(secret)
                .issuer("Application pref. Agadir-Ida-Ou-Tanane")
                .build();
*/
        QrData data = qrDataFactory.newBuilder().label("Prefecture AIO").secret(secret).issuer("SI").build();
        return getDataUriForImage(
                qrGenerator.generate(data),
                qrGenerator.getImageMimeType()
        );
    }

    //todo verifyCode(String code, String secret)
    public boolean verifyCode(String code, String secret){
        return codeVerifier.isValidCode(secret, code);
    }



}
