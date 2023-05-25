package com.example.spsecurity.registration;

import com.example.spsecurity.appuser.AppUserRole;
import com.example.spsecurity.appuser.AppUserService;
import com.example.spsecurity.appuser.Appuser;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail =
                emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("Email bot valid");
        }
        return appUserService.signUpUser(
                new Appuser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                        )
        );
    }
}
