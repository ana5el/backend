package ma.gov.prefagadir.application.backend.controllers;

import ma.gov.prefagadir.application.backend.models.Citoyen;
import ma.gov.prefagadir.application.backend.payload.request.CrRequest;
import ma.gov.prefagadir.application.backend.repository.CitoyenRepository;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/api/print")
@CrossOrigin(origins = "*")

public class PrintController {

    @Autowired
    private CitoyenRepository repository;

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

       File file =  new ClassPathResource("jasper/cr.jrxml").getFile();
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,certificatparams, new JREmptyDataSource());
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline;filename=cr.pdf");
    }
}
