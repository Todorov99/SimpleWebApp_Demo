package signinloginpagesdemo.simplesignin.areas.pizzas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import signinloginpagesdemo.simplesignin.areas.pizzas.domain.entities.pizzas.Size;
import signinloginpagesdemo.simplesignin.areas.pizzas.exceptions.DuplicatedPizzas;
import signinloginpagesdemo.simplesignin.areas.pizzas.domain.models.binding.AddPizzaBindingModel;
import signinloginpagesdemo.simplesignin.areas.pizzas.domain.models.view.AllPizzasViewModel;
import signinloginpagesdemo.simplesignin.areas.pizzas.services.PasteService;
import signinloginpagesdemo.simplesignin.areas.pizzas.services.PizzaService;
import signinloginpagesdemo.simplesignin.areas.pizzas.services.ProductService;
import signinloginpagesdemo.simplesignin.controllers.BaseController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class PizzaController extends BaseController {

    private final ProductService productService;
    private final PasteService pasteService;
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(ProductService productService, PasteService pasteService, PizzaService pizzaService) {
        this.productService = productService;
        this.pasteService = pasteService;
        this.pizzaService = pizzaService;
    }

    @ModelAttribute(value = "pizzaSizes")
    public List<String> getPizzaSizes(){
        List<String> pizzaSizes = new ArrayList<>();

        Size[] sizes = Size.values();

        for (Size size : sizes) {
            pizzaSizes.add(size.toString());
        }

        return pizzaSizes;
    }

    @ModelAttribute(value = "allProducts")
    public List<String> getAllProducts(){

        return this.productService.getAllProducts()
                .stream()
                .map(products -> String.format("%s",products.getProductType()))
                .collect(Collectors.toList());
    }

    @ModelAttribute(value = "allPastes")
    public List<String> getAllPastes() {
        return this.pasteService.getAllTypesPaste()
                .stream()
                .map(paste -> String.format("%s",paste.getPasteType()))
                .collect(Collectors.toList());
    }

    @GetMapping("/addPizza")
    public String addPizzaPage(@ModelAttribute AddPizzaBindingModel addPizzaBindingModel){
        return "addPizza";
    }

    @PostMapping("/addPizza")
    public ModelAndView addPizza(@Valid @ModelAttribute AddPizzaBindingModel addPizzaBindingModel,
                                 BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return super.view("addPizza");
        }

        this.pizzaService.addPizza(addPizzaBindingModel);
        return super.redirect("homeLogIn");
    }

    @ExceptionHandler(DuplicatedPizzas.class)
    public String duplicatedPizza(){
        return "error/duplicatedPizza";
    }

    // da se opravi printiranto na vsichki producti
    @GetMapping("/pizza-menu")
    public ModelAndView getPizzaMenuPage(ModelAndView modelAndView, @ModelAttribute AllPizzasViewModel allPizzasViewModel){

        Set<AllPizzasViewModel> allPizzas = this.pizzaService.getAllPizzas();
      modelAndView.addObject("allPizzas", allPizzas);

        for (AllPizzasViewModel pizza : this.pizzaService.getAllPizzas()) {
            System.out.println(pizza);
        }
        return super.view("pizza-menu");
    }


}
