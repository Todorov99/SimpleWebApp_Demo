package signinloginpagesdemo.simplesignin.controllers;

import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HomeController extends BaseController{

    @GetMapping("/")
    public ModelAndView homePage(ModelAndView modelAndView){
        return super.view("home");
    }

}
