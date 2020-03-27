package signinloginpagesdemo.simplesignin.areas.pizzas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import signinloginpagesdemo.simplesignin.areas.pizzas.domain.entities.pizzas.Products;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {

    @Query("select p from Products as p")
    List<Products> findAllProducts();

    Products findByProductType(String productType);
}
