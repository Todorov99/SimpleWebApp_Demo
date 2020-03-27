package signinloginpagesdemo.simplesignin.areas.pizzas.services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import signinloginpagesdemo.simplesignin.areas.pizzas.exceptions.ProductsNotFound;
import signinloginpagesdemo.simplesignin.areas.pizzas.domain.models.view.ProductsViewModel;
import signinloginpagesdemo.simplesignin.areas.pizzas.repositories.ProductRepository;
import signinloginpagesdemo.simplesignin.areas.pizzas.services.ProductService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    @PreAuthorize(value = "hasAnyRole('ADMIN', 'EMPLOYEE')")
    public List<ProductsViewModel> getAllProducts() {

        List<ProductsViewModel> allProducts = new ArrayList<>();

        this.productRepository.findAllProducts()
                .forEach(product -> {
                    ProductsViewModel productView = this.modelMapper.map(product, ProductsViewModel.class);
                    allProducts.add(productView);
                });

        if(allProducts.size() == 0){
            throw new ProductsNotFound("No products found!");
        }

        return allProducts;
    }
}
