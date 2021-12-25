package ma.gov.prefagadir.application.backend.controllers;

import ma.gov.prefagadir.application.backend.models.Citoyen;
import ma.gov.prefagadir.application.backend.services.CitoyenService;
import ma.gov.prefagadir.application.backend.utils.CitoyenExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/citoyen")
@CrossOrigin(origins = "*")
public class CitoyenController {

    @Autowired
    private CitoyenService citoyenService;

    @GetMapping(value = {"/",""})
    public List<Citoyen> getAll(){
        return citoyenService.getAll();
    }

    @GetMapping("/{cin}")
    public Citoyen getByCin(@PathVariable String cin){
        return citoyenService.getByCin(cin);
    }

    @PostMapping(value = {"/",""})
    public Citoyen addCitoyen(@RequestBody Citoyen citoyen){
        return citoyenService.insert(citoyen);
    }

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=export_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Citoyen> listCitoyens = citoyenService.getAll();

        CitoyenExcelExporter excelExporter = new CitoyenExcelExporter(listCitoyens);

        excelExporter.export(response);
    }
}
