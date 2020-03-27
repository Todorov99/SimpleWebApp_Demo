package signinloginpagesdemo.simplesignin.areas.users.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import signinloginpagesdemo.simplesignin.areas.users.domain.models.binding.RegisterUserBinding;

public interface UserService extends UserDetailsService {

    void signIn(RegisterUserBinding registerUserBinding);
}
