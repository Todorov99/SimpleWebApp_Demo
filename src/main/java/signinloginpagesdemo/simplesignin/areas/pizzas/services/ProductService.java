package signinloginpagesdemo.simplesignin.areas.pizzas.services;

import signinloginpagesdemo.simplesignin.areas.pizzas.domain.models.view.ProductsViewModel;

import java.util.List;

public interface ProductService {

    List<ProductsViewModel> getAllProducts();
}
