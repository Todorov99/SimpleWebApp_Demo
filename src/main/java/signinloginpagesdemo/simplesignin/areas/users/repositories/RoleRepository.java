package signinloginpagesdemo.simplesignin.areas.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import signinloginpagesdemo.simplesignin.areas.users.domain.entitites.users.Role;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("select r from Role as r")
    Set<Role> roles();
}
