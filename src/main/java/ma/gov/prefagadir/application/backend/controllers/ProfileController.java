package ma.gov.prefagadir.application.backend.controllers;

import ma.gov.prefagadir.application.backend.models.Profile;
import ma.gov.prefagadir.application.backend.payload.response.MessageResponse;
import ma.gov.prefagadir.application.backend.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
@CrossOrigin(origins = "*")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping(value = {"","/"})
    private List<Profile> getAllProfiles(){
        return profileService.getAllProfiles();

    }

    @GetMapping("/{id}")
    private Profile getProfile(@PathVariable Long id){
        return profileService.getProfile(id);
    }

    @DeleteMapping("/{id}")
    private void deleteProfile(@PathVariable Long id){
        profileService.deleteProfile(id);
    }

    @PostMapping(value = {"","/"})
    private ResponseEntity<?> addProfile(@RequestBody Profile p){
        profileService.addProfile(p);
        return  ResponseEntity.ok(new MessageResponse("Profile add successfully"));
    }

    @PutMapping("/{id}")
    private Profile updateProfile(@PathVariable Long id,@RequestBody Profile p){
        profileService.updateProfile(id, p);
        return p;
    }


}
