package signinloginpagesdemo.simplesignin.areas.pizzas.services;

import signinloginpagesdemo.simplesignin.areas.pizzas.domain.models.view.PasteViewModel;

import java.util.List;

public interface PasteService {

    List<PasteViewModel> getAllTypesPaste();
}
