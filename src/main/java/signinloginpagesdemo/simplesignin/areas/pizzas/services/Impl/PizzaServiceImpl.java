package signinloginpagesdemo.simplesignin.areas.pizzas.services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import signinloginpagesdemo.simplesignin.areas.pizzas.domain.entities.pizzas.Paste;
import signinloginpagesdemo.simplesignin.areas.pizzas.domain.entities.pizzas.Pizza;
import signinloginpagesdemo.simplesignin.areas.pizzas.domain.entities.pizzas.Products;
import signinloginpagesdemo.simplesignin.areas.pizzas.exceptions.DuplicatedPizzas;
import signinloginpagesdemo.simplesignin.exceptions.InvalidEntity;
import signinloginpagesdemo.simplesignin.areas.pizzas.domain.models.binding.AddPizzaBindingModel;
import signinloginpagesdemo.simplesignin.areas.pizzas.domain.models.view.AllPizzasViewModel;
import signinloginpagesdemo.simplesignin.areas.pizzas.repositories.PasteRepository;
import signinloginpagesdemo.simplesignin.areas.pizzas.repositories.PizzaRepository;
import signinloginpagesdemo.simplesignin.areas.pizzas.repositories.ProductRepository;
import signinloginpagesdemo.simplesignin.areas.pizzas.services.PizzaService;
import signinloginpagesdemo.simplesignin.validator.ValidatorUtil;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PizzaServiceImpl implements PizzaService {

    private final ModelMapper modelMapper;
    private final PizzaRepository pizzaRepository;
    private final ProductRepository productRepository;
    private final PasteRepository pasteRepository;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public PizzaServiceImpl(ModelMapper modelMapper, PizzaRepository pizzaRepository, ProductRepository productRepository, PasteRepository pasteRepository, ValidatorUtil validatorUtil) {
        this.modelMapper = modelMapper;
        this.pizzaRepository = pizzaRepository;
        this.productRepository = productRepository;
        this.pasteRepository = pasteRepository;
        this.validatorUtil = validatorUtil;
    }

    @Override
    @PreAuthorize(value = "hasAnyRole('ADMIN', 'EMPLOYEE')")
    public void addPizza(AddPizzaBindingModel addPizzaBindingModel) {

        if(checkForDuplicatedPizza(addPizzaBindingModel.getPizzaName(), addPizzaBindingModel.getPasteType())){
            throw new DuplicatedPizzas("You've already have this kind of pizza");
        }

        Pizza pizza = validateBindingModel(addPizzaBindingModel);

        pizza.setProducts(addPizzaProducts(addPizzaBindingModel));

        Paste paste = this.pasteRepository.findByPasteType(addPizzaBindingModel.getPasteType());

        pizza.setPaste(paste);

        this.pizzaRepository.saveAndFlush(pizza);

    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Set<AllPizzasViewModel> getAllPizzas() {

        return this.pizzaRepository.findAll()
                .stream()
                .map(pizza -> this.modelMapper.map(pizza, AllPizzasViewModel.class))
                .collect(Collectors.toSet());

    }

    private Set<Products> addPizzaProducts(AddPizzaBindingModel addPizzaBindingModel){
        String[] productList = addPizzaBindingModel.getProducts();

        Set<Products> products = new HashSet<>();

        for (String product : productList) {

            Products product1 = this.productRepository.findByProductType(product);

            products.add(product1);
        }

        return products;
    }


    private Pizza validateBindingModel(AddPizzaBindingModel addPizzaBindingModel){

        if(!this.validatorUtil.isValid(addPizzaBindingModel)){
            this.validatorUtil
                    .violation(addPizzaBindingModel)
                    .forEach(violation -> {
                        throw new InvalidEntity(violation.getMessage());
                    });
        }

        return this.modelMapper.map(addPizzaBindingModel, Pizza.class);
    }

    private boolean checkForDuplicatedPizza(String pizzaName, String pasteType){
        Pizza dbPizza = this.pizzaRepository.findByPizzaNameAndPasteName(pizzaName,
                pasteType).orElse(null);

        return dbPizza != null;

    }
}
