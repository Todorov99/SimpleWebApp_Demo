package signinloginpagesdemo.simplesignin.areas.pizzas.domain.models.view;

import signinloginpagesdemo.simplesignin.areas.pizzas.domain.entities.pizzas.Size;

import java.math.BigDecimal;
import java.util.Set;

public class AllPizzasViewModel {

    private String pizzaName;

    private BigDecimal price;

    private String description;

    private Size size;

    private PasteViewModel paste;

    private Set<ProductsViewModel> products;

    public AllPizzasViewModel() {
    }

    public AllPizzasViewModel(String pizzaName, BigDecimal price, String description, Size size, PasteViewModel paste, Set<ProductsViewModel> products) {
        this.pizzaName = pizzaName;
        this.price = price;
        this.description = description;
        this.size = size;
        this.paste = paste;
        this.products = products;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public PasteViewModel getPaste() {
        return paste;
    }

    public void setPaste(PasteViewModel paste) {
        this.paste = paste;
    }

    public Set<ProductsViewModel> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductsViewModel> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "AllPizzasViewModel{" +
                "pizzaName='" + pizzaName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", size=" + size +
                ", paste=" + paste +
                ", products=" + products +
                '}';
    }
}
