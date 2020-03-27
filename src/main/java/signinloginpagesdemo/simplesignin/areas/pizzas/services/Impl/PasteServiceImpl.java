package signinloginpagesdemo.simplesignin.areas.pizzas.services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import signinloginpagesdemo.simplesignin.areas.pizzas.exceptions.PastesNotFound;
import signinloginpagesdemo.simplesignin.areas.pizzas.domain.models.view.PasteViewModel;
import signinloginpagesdemo.simplesignin.areas.pizzas.repositories.PasteRepository;
import signinloginpagesdemo.simplesignin.areas.pizzas.services.PasteService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PasteServiceImpl implements PasteService {

    private final PasteRepository pasteRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PasteServiceImpl(PasteRepository pasteRepository, ModelMapper modelMapper) {
        this.pasteRepository = pasteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public List<PasteViewModel> getAllTypesPaste() {
        List<PasteViewModel> allPastes = new ArrayList<>();

        this.pasteRepository.getAllPastes()
                .forEach(paste -> {
                    PasteViewModel pasteViewModel = this.modelMapper.map(paste, PasteViewModel.class);
                    allPastes.add(pasteViewModel);
                });

        if(allPastes.size() == 0){
            throw new PastesNotFound("No pastes found!");
        }
        return allPastes;
    }
}
