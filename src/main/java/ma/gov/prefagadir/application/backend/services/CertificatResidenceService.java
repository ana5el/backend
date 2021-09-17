package ma.gov.prefagadir.application.backend.services;

import ma.gov.prefagadir.application.backend.models.Citoyen;
import ma.gov.prefagadir.application.backend.repository.CitoyenRepository;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Date;
import java.util.HashMap;

@Service
public class CertificatResidenceService {

    @Autowired
    private CitoyenRepository citoyenRepository;

    public void create(){
        HashMap certificatparams = new HashMap<String, Object>();
        certificatparams.put("prenom", "test test");
        try {
            File file = ResourceUtils.getFile("classpath:cr.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, certificatparams, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "/Users/elmrabtianas/Documents/dev/jesper"+new Date().getTime()+".pdf");
            System.out.println("Done--------------");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public byte[] generateCertificate(String cin) throws IOException, JRException {
        Citoyen citoyen = citoyenRepository.getById(cin);
        HashMap params = new HashMap<String, Object>();
        params.put("prenomfr",citoyen.getPrenomFr());
        params.put("nomfr", citoyen.getNomFr());
        params.put("prenomar", citoyen.getPrenomAr());
        params.put("nomar", citoyen.getNomAr());
        params.put("cin", citoyen.getCin());
        params.put("profession", citoyen.getProfession());
        params.put("situation", citoyen.getSituationFamiliale());

        File file = new ClassPathResource("jasper/cr.jrxml").getFile();
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
        ByteArrayOutputStream byteArrayInputStream = getByteArrayOutputStream(jasperPrint);
        return byteArrayInputStream.toByteArray();
    }

    protected ByteArrayOutputStream getByteArrayOutputStream(JasperPrint jasperPrint) throws JRException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);
        return byteArrayOutputStream;
    }
}
