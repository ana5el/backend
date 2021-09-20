package ma.gov.prefagadir.application.backend.services;

import ma.gov.prefagadir.application.backend.models.*;
import ma.gov.prefagadir.application.backend.payload.request.AddUserRequest;
import ma.gov.prefagadir.application.backend.repository.ProfileRepository;
import ma.gov.prefagadir.application.backend.repository.UserRepository;
import ma.gov.prefagadir.application.backend.utils.TotpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private TotpUtils totpUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AALService aalService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private  AgentAutotiteService agentAutotiteService;

    public User addUser(final AddUserRequest request){
        /*
        if(userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("User already exist");
        }
        Profile profile = profileRepository.findByName(request.getProfile()).get();
        User user = new User(
                request.getUsername(),
                encoder.encode(request.getPassword()),
                totpUtils.generateSecret(),
                request.isActive(),
                profile
        );
        userRepository.save(user);
        return user;

         */

        if(userRepository.existsByUsername(request.getLogin())){
            throw new RuntimeException("User already exist");
        }

        Profile profile = profileRepository.getById(request.getProfileId());
        AAL aal = aalService.getAAL(request.getAalId());
        Grade grade = gradeService.getGrade(request.getGradeId());
        AgentAutorite agentAutorite = agentAutotiteService.save(new AgentAutorite(request.getCin(), request.getNom(), request.getPrenom(), request.getTel(), grade, aal));
        return userRepository.save(new User(request.getLogin(), encoder.encode(request.getPassword()), totpUtils.generateSecret(), true, profile, agentAutorite));

    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public void updateUser(Long id, User user){
        User userFromDb = userRepository.findById(id).get();
        userFromDb.setUsername(user.getUsername());
        userFromDb.setPassword(user.getPassword());
        userFromDb.setActive(user.isActive());
        userRepository.save(userFromDb);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUser(Long id){
        return userRepository.findById(id).get();
    }

    public User getUserByUsername(String username)
    {
        return userRepository.findByUsername(username).get();
    }



}
