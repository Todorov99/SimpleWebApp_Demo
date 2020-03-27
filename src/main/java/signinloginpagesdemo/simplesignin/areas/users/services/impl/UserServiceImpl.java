package signinloginpagesdemo.simplesignin.areas.users.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import signinloginpagesdemo.simplesignin.areas.users.domain.entitites.users.Role;
import signinloginpagesdemo.simplesignin.areas.users.domain.entitites.users.User;
import signinloginpagesdemo.simplesignin.exceptions.InvalidEntity;
import signinloginpagesdemo.simplesignin.areas.users.domain.models.binding.RegisterUserBinding;
import signinloginpagesdemo.simplesignin.areas.users.repositories.RoleRepository;
import signinloginpagesdemo.simplesignin.areas.users.repositories.UserRepository;
import signinloginpagesdemo.simplesignin.areas.users.services.UserService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    @Transactional
    public void signIn(RegisterUserBinding registerUserBinding) {


        if(isUsedUsername(registerUserBinding.getUsername())){
            throw new InvalidEntity("Username is not free");
        }

        User user = this.modelMapper.map(registerUserBinding, User.class);

        String encryptedPassword = this.bCryptPasswordEncoder.encode(registerUserBinding.getPassword());
        user.setPassword(encryptedPassword);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
//
//        if(userRepository.count() == 0){
//            user.setAuthorities(roles);
//        }else {
//            Set<Role> userRole = new HashSet<>();
//
//            roles
//                    .forEach(role -> {
//                        if(role.getAuthority().equals("ROLE_USER")){
//                            userRole.add(role);
//                        }
//                    });
//
//            user.setAuthorities(userRole);
//        }

        user.setAuthorities(setUserRoles());
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        this.userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with username" + username));
    }

    private Set<Role> setUserRoles(){

        Set<Role> roles = this.roleRepository.roles();

        Set<Role> userRoles = new HashSet<>();

        if(this.userRepository.count() == 0){
           return roles;
        }else {
            roles
                    .forEach(role -> {
                        if(role.getAuthority().equals("ROLE_USER")){
                            userRoles.add(role);
                        }
                    });

        }

        return userRoles;
    }

    private boolean isUsedUsername(String username){

        User user = this.userRepository.findByUsername(username).orElse(null);
        return user != null;
    }

}
