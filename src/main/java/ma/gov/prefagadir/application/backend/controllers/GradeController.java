package ma.gov.prefagadir.application.backend.controllers;

import ma.gov.prefagadir.application.backend.models.Grade;
import ma.gov.prefagadir.application.backend.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
@CrossOrigin(origins = "*")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping(value = {"", "/"})
    public List<Grade> getAllGrades(){
        return gradeService.getAllGrades();
    }

    @GetMapping("/{id}")
    public Grade getGrade(Long id){
        return  gradeService.getGrade(id);
    }

    @DeleteMapping("/{id}")
    public void deleteGrade(@PathVariable Long id){
        gradeService.deleteGrade(id);
    }

    @PutMapping("/{id}")
    public void updateGrade(@PathVariable Long id, @RequestBody Grade grade){
        gradeService.updateGrade(id,grade);
    }

    @PostMapping(value = {"","/"})
    public void addGrade(@RequestBody Grade grade){
        gradeService.addGrade(grade);
    }
}
