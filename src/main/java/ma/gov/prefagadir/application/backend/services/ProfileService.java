package ma.gov.prefagadir.application.backend.services;

import ma.gov.prefagadir.application.backend.models.Privilege;
import ma.gov.prefagadir.application.backend.models.Profile;
import ma.gov.prefagadir.application.backend.repository.PrivilegeRepository;
import ma.gov.prefagadir.application.backend.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    public void deletePrivilege(Long idPro, Long idPri){
        Profile profile = profileRepository.findById(idPro).get();
        profile.getPrivileges().removeIf(privilege -> privilege.getId().equals(idPri));
        profileRepository.save(profile);
    }

    public void addPrivilege(Long idP, Long idPri){
        Profile profile = profileRepository.findById(idP).get();
        profile.getPrivileges().add(privilegeRepository.findById(idPri).get());
        profileRepository.save(profile);
    }

    public Profile getProfile(Long id){
        return profileRepository.findById(id).get();
    }

    public Profile addProfile(Profile profile){
        profileRepository.save(profile);
        return profile;
    }

    public void deleteProfile(Long id){
        profileRepository.deleteById(id);
    }

    public void updateProfile(Long id, Profile profile){
        Profile profileFromDb = profileRepository.getById(id);
        profileFromDb.setLabelAr(profile.getLabelAr());
        profileFromDb.setLabelFr(profile.getLabelFr());
        profileFromDb.setName(profile.getName());
        profileFromDb.setPrivileges(profile.getPrivileges());
        profileRepository.save(profileFromDb);
    }

    public List<Profile> getAllProfiles(){
        return profileRepository.findAll();
    }

    public Set<Privilege> showPrivilegeProfile(Long id){
        return profileRepository.findById(id).get().getPrivileges();
    }

}
