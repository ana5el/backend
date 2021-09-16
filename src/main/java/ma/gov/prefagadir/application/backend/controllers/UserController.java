package ma.gov.prefagadir.application.backend.controllers;

import dev.samstevens.totp.exceptions.QrGenerationException;
import ma.gov.prefagadir.application.backend.models.User;
import ma.gov.prefagadir.application.backend.payload.request.AddUserRequest;
import ma.gov.prefagadir.application.backend.payload.response.MessageResponse;
import ma.gov.prefagadir.application.backend.repository.UserRepository;
import ma.gov.prefagadir.application.backend.services.UserService;
import ma.gov.prefagadir.application.backend.utils.TotpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TotpUtils totpUtils;

    @GetMapping("/qr/{id}")
    public String generateQr(@PathVariable Long id) throws QrGenerationException {
        User user = userRepository.findById(id).get();
        return totpUtils.getUriFromImage(user.getSecret());
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<?> addUser(@Valid @RequestBody AddUserRequest request){
        userService.addUser(request);
        return ResponseEntity.ok(new MessageResponse("User add successfully"));
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user){
        userService.updateUser(id,user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }
    
    @GetMapping("/findbyusername/{username}")
    public  User getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @GetMapping(value = {"","/"})
    public List<User> getAllUsers(){
        return userService.getUsers();
    }
}
