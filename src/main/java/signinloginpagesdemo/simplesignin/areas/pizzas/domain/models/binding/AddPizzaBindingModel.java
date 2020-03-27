package signinloginpagesdemo.simplesignin.areas.pizzas.domain.models.binding;

import signinloginpagesdemo.simplesignin.areas.pizzas.domain.entities.pizzas.Size;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class AddPizzaBindingModel {

    @NotBlank(message = "Въведете вид пица")
    private String pizzaName;

    @DecimalMin(value = "1", message = "Цената на пицата трябва да бъде по-висока от 0.0 лв")
    @NotNull(message = "Въведете цена")
    private BigDecimal price;

//    @Pattern(regexp = "[A-Za-z0-9 _.,!\"'$]*")
    @NotBlank(message = "Въдете кратко описание")
    private String description;

    private Size size;

    private String pasteType;

    private String[] products;

    public AddPizzaBindingModel() {
    }

    public AddPizzaBindingModel(String pizzaName, BigDecimal price, String description, Size size, String pasteType, String[] products) {
        this.pizzaName = pizzaName;
        this.price = price;
        this.description = description;
        this.size = size;
        this.pasteType = pasteType;
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

    public String getPasteType() {
        return pasteType;
    }

    public void setPasteType(String pasteType) {
        this.pasteType = pasteType;
    }

    public String[] getProducts() {
        return products;
    }

    public void setProducts(String[] products) {
        this.products = products;
    }
}
