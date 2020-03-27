package signinloginpagesdemo.simplesignin.areas.pizzas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import signinloginpagesdemo.simplesignin.areas.pizzas.domain.entities.pizzas.Paste;

import java.util.Set;

@Repository
public interface PasteRepository extends JpaRepository<Paste, Integer> {

    @Query("select p from Paste as p")
    Set<Paste> getAllPastes();

    Paste findByPasteType(String pasteType);
}
