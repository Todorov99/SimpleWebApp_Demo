package signinloginpagesdemo.simplesignin.areas.users.domain.entitites.users;

import org.springframework.security.core.GrantedAuthority;
import signinloginpagesdemo.simplesignin.entities.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity implements GrantedAuthority {

    @Column(name = "authority")
    private String authority;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "authorities")
    private Set<User> user;

    public Role() {
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }
}
