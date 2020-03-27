package signinloginpagesdemo.simplesignin.areas.pizzas.domain.models.view;

public class PasteViewModel {

    private String pasteType;

    public PasteViewModel() {
    }

    public PasteViewModel(String pasteType) {
        this.pasteType = pasteType;
    }

    public String getPasteType() {
        return pasteType;
    }

    public void setPasteType(String pasteType) {
        this.pasteType = pasteType;
    }

    @Override
    public String toString() {
        return "PasteViewModel{" +
                "pasteType='" + pasteType + '\'' +
                '}';
    }
}
