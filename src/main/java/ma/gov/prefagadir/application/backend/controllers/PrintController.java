package ma.gov.prefagadir.application.backend.controllers;

import dev.samstevens.totp.exceptions.QrGenerationException;
import ma.gov.prefagadir.application.backend.models.Citoyen;
import ma.gov.prefagadir.application.backend.models.User;
import ma.gov.prefagadir.application.backend.payload.request.CrRequest;
import ma.gov.prefagadir.application.backend.repository.CitoyenRepository;
import ma.gov.prefagadir.application.backend.services.UserService;
import ma.gov.prefagadir.application.backend.utils.TotpUtils;
import net.sf.jasperreports.engine.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

@RestController
@RequestMapping("/api/print")
@CrossOrigin(origins = "*")

public class PrintController {

    @Autowired
    private CitoyenRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private TotpUtils totpUtils;

    @PostMapping("/cr")
    public void printCr(@RequestBody CrRequest crRequest, HttpServletResponse response) throws IOException, JRException {

        Citoyen citoyen = repository.getById(crRequest.getCin());
        HashMap certificatparams = new HashMap<String, Object>();

        certificatparams.put("prenomfr", citoyen.getPrenomFr());
        certificatparams.put("nomfr", citoyen.getNomFr());
        certificatparams.put("prenomar", citoyen.getPrenomAr());
        certificatparams.put("nomar", citoyen.getNomAr());
        certificatparams.put("cin", citoyen.getCin());
        certificatparams.put("profession", citoyen.getProfession());
        certificatparams.put("situation", citoyen.getSituationFamiliale());
        certificatparams.put("adrFr",crRequest.getAdrFr());
        certificatparams.put("situation",citoyen.getSituationFamiliale());
        certificatparams.put("profession",citoyen.getProfession().getLabelFr());
        certificatparams.put("nomPereAr",citoyen.getNomPereAr());
        certificatparams.put("nomPereFr", citoyen.getNomPereFr());
        certificatparams.put("nomMereFr", citoyen.getNomMereFr());
        certificatparams.put("nomMereAr", citoyen.getNomMereAr());
        certificatparams.put("datenaissance", citoyen.getDateNaissance());


        File file =  new ClassPathResource("jasper/cr.jrxml").getFile();
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,certificatparams, new JREmptyDataSource());
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline;filename=cr.pdf");
    }

    @PostMapping("/userQrCode/{id}")
    public void printUserQrCode(@PathVariable Long id, HttpServletResponse response) throws IOException, JRException, QrGenerationException {
        User user = userService.getUser(id);
        String data = totpUtils.getUriFromImage(user.getSecret());
        String base64Image = data.split(",")[1];
        InputStream stream = new ByteArrayInputStream(Base64.decodeBase64(user.getSecret().getBytes()));
        HashMap params = new HashMap<String, Object>();
        params.put("username", user.getUsername());
        params.put("password", "prefaio@2021");
        params.put("aal", user.getAgentAutorite().getAal().getLabelFr());
        params.put("fullName", new String(user.getAgentAutorite().getNom() + " " + user.getAgentAutorite().getPrenom()).toUpperCase());
        params.put("cin", user.getAgentAutorite().getCin());
        params.put("qrcode", base64Image);
        File file = new ClassPathResource("jasper/user_qr_code.jrxml").getFile();
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,params, new JREmptyDataSource());
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline;filename=cr.pdf");
    }
}
