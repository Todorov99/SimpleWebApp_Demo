package signinloginpagesdemo.simplesignin.areas.pizzas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import signinloginpagesdemo.simplesignin.areas.pizzas.domain.entities.pizzas.Pizza;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

    @Query("select p from Pizza as p join p.paste as pp where p.pizzaName = :pizzaName and pp.pasteType = :pasteType")
    Optional<Pizza> findByPizzaNameAndPasteName(@Param(value = "pizzaName") String pizzaName,
                                                @Param(value = "pasteType") String pasteType);

    @Query("select p.pizzaName, pp.pasteType, ppp.productType, p.description, p.price from Pizza as p join p.paste as pp join p.products as ppp")
    Set<String> getAllPizzas();
}
