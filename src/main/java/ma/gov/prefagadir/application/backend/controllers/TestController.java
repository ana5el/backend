package ma.gov.prefagadir.application.backend.controllers;

import ma.gov.prefagadir.application.backend.models.Citoyen;
import ma.gov.prefagadir.application.backend.models.Point;
import ma.gov.prefagadir.application.backend.payload.request.AddUserRequest;
import ma.gov.prefagadir.application.backend.payload.request.CrRequest;
import ma.gov.prefagadir.application.backend.payload.response.MessageResponse;
import ma.gov.prefagadir.application.backend.repository.CitoyenRepository;
import ma.gov.prefagadir.application.backend.repository.PointRepository;
import ma.gov.prefagadir.application.backend.repository.ProfileRepository;
import ma.gov.prefagadir.application.backend.services.CertificatResidenceService;
import ma.gov.prefagadir.application.backend.services.UserService;
import net.sf.jasperreports.engine.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*")
public class TestController {

    private static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private CitoyenRepository citoyenRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private CertificatResidenceService certificatResidenceService;

    @PostMapping("/adduser")
    public ResponseEntity<?> addUser(@Valid @RequestBody AddUserRequest request){
        userService.addUser(request);
        return ResponseEntity.ok(new MessageResponse("User add successfully"));
    }
    @GetMapping("/all")
    public String allAccess() {

        return "Public Content.";
    }

    @GetMapping("/caid")
    @PreAuthorize("hasRole('CAID') or hasRole('ADMIN') or hasRole('SUPERADMIN')")
    public String userAccess() {

        return "caid Content.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPERADMIN')")
    public String adminAccess() {

        return "Admin Board.";
    }

    @PostMapping("/points")
    public ResponseEntity<?> addPoint(@RequestBody Point point){
        Point p  = pointRepository.save(point);
        pointRepository.flush();
        return ResponseEntity.ok(p.getId());
    }

    @GetMapping("/points")
    public List<Point> getAllPoints(){
        return pointRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/cr")
    public void create(@RequestBody CrRequest crRequest, HttpServletResponse httpServletResponse) throws JRException, IOException {
      /*  Citoyen citoyen = citoyenRepository.getById(cin);
        HashMap certificatparams = new HashMap<String, Object>();

        certificatparams.put("prenomfr", citoyen.getPrenomFr());
        certificatparams.put("nomfr", citoyen.getNomFr());
        certificatparams.put("prenomar", citoyen.getPrenomAr());
        certificatparams.put("nomar", citoyen.getNomAr());
        certificatparams.put("cin", citoyen.getCin());
        certificatparams.put("profession", citoyen.getProfession());
        certificatparams.put("situation", citoyen.getSituationFamiliale());

        try {
            //File file = ResourceUtils.getFile("classpath:cr.jrxml");
            File file = new ClassPathResource("jasper/cr.jrxml").getFile();
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, certificatparams, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "/Users/elmrabtianas/Documents/dev/jasper/"+new Date().getTime()+".pdf");
            LOGGER.info("{}", "Done");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        byte[] bytes = certificatResidenceService.generateCertificate(crRequest.getCin());
        ByteArrayOutputStream out = new ByteArrayOutputStream(bytes.length);
        out.write(bytes, 0, bytes.length);

        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.addHeader("Content-Disposition","inline; filename="+crRequest.getCin()+".pdf");

        OutputStream os;
        try{
            os  = httpServletResponse.getOutputStream();
            out.writeTo(os);
            os.flush();
            os.close();
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

}
