package ma.gov.prefagadir.application.backend.services;

import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;

@Service
public class CertificatResidenceService {

    public void create(){
        HashMap certificatparams = new HashMap<String, Object>();
        certificatparams.put("prenom", "test test");
        try {
            File file = ResourceUtils.getFile("classpath:cr.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, certificatparams, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "D:/jasperoutput/test"+new Date().getTime()+".pdf");
            System.out.println("Done--------------");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
