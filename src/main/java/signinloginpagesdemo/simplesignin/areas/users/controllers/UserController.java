package signinloginpagesdemo.simplesignin.areas.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import signinloginpagesdemo.simplesignin.controllers.BaseController;
import signinloginpagesdemo.simplesignin.exceptions.InvalidEntity;
import signinloginpagesdemo.simplesignin.areas.users.domain.models.binding.RegisterUserBinding;
import signinloginpagesdemo.simplesignin.areas.users.services.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/logIn")
    @PreAuthorize(value = "isAnonymous()")
    public ModelAndView getLogInPage(ModelAndView modelAndView){

        return super.view("logIn");
    }

    @GetMapping("/signIn")
    public ModelAndView getSignInPage(@ModelAttribute RegisterUserBinding registerUserBinding){
        return super.view("signIn");
    }

    @PostMapping("/signIn")
    public ModelAndView signIn(@Valid @ModelAttribute RegisterUserBinding registerUserBinding,
                               BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return super.view("signIn");
        }

        this.userService.signIn(registerUserBinding);
        return super.redirect("/logIn");
    }

    @GetMapping("/homeLogIn")
    public String getHomeLogInPage(Principal principal, ModelAndView modelAndView){
        modelAndView.addObject("user", principal);
        System.out.println(principal.getName());
        return "homeLogIn";
    }

    @ExceptionHandler(InvalidEntity.class)
    public String exceptionPage(){
        return "error/access-denied";
    }
}
