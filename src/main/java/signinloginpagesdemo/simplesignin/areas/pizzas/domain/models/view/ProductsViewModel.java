package signinloginpagesdemo.simplesignin.areas.pizzas.domain.models.view;

public class ProductsViewModel {

    private String productType;

    public ProductsViewModel() {
    }

    public ProductsViewModel(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "ProductsViewModel{" +
                "productType='" + productType + '\'' +
                '}';
    }
}
