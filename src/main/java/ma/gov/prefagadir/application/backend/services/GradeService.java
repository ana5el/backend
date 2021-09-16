package ma.gov.prefagadir.application.backend.services;

import ma.gov.prefagadir.application.backend.models.Grade;
import ma.gov.prefagadir.application.backend.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {
    
    @Autowired
    private GradeRepository gradeRepository;
    

    public Grade addGrade(Grade grade){
        gradeRepository.save(grade);
        return  grade;
    }

    public Grade getGrade(Long id){
        return gradeRepository.findById(id).get();
    }

    public List<Grade> getAllGrades(){
        return gradeRepository.findAll();
    }

    public void deleteGrade(Long id){
        gradeRepository.deleteById(id);
    }

    public void updateGrade(Long id, Grade grade){
        Grade gradeFromDb = gradeRepository.findById(id).get();
        gradeFromDb.setLabelAr(grade.getLabelAr());
        gradeFromDb.setLabelFr(grade.getLabelFr());
        gradeRepository.save(gradeFromDb);
    }
}
