package signinloginpagesdemo.simplesignin.areas.pizzas.domain.entities.pizzas;

import signinloginpagesdemo.simplesignin.entities.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "pastes")
public class Paste extends BaseEntity {

    @Column(name = "paste_type")
    private String pasteType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paste")
    private Set<Pizza> pizzas;

    public Paste() {
    }

    public String getPasteType() {
        return pasteType;
    }

    public void setPasteType(String pasteType) {
        this.pasteType = pasteType;
    }
}

