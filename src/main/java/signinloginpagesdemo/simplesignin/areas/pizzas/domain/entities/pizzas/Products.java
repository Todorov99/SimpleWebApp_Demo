package signinloginpagesdemo.simplesignin.areas.pizzas.domain.entities.pizzas;

import signinloginpagesdemo.simplesignin.entities.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "products")
public class Products extends BaseEntity {

    @Column(name = "product_type")
    private String productType;

    @ManyToMany(mappedBy = "products")
    private Set<Pizza> pizzas;

    public Products() {
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Set<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(Set<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}
