package signinloginpagesdemo.simplesignin.areas.pizzas.services;

import signinloginpagesdemo.simplesignin.areas.pizzas.domain.models.binding.AddPizzaBindingModel;
import signinloginpagesdemo.simplesignin.areas.pizzas.domain.models.view.AllPizzasViewModel;

import java.util.Set;

public interface PizzaService {

    void addPizza(AddPizzaBindingModel addPizzaBindingModel);

    Set<AllPizzasViewModel> getAllPizzas();

}
