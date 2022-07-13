package challenge_1.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Document(collection = "users")
public class UserEntity {
    @Id
    private String id;
    @Indexed(name = "username_1_idx", unique = true)
    private String username;
    private String password;
    private List<Integer> deposit;
    private Collection<? extends GrantedAuthority> roles;

    public String getId() {
        return id;
    }

    public UserEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<Integer> getDeposit() {
        return deposit;
    }

    public UserEntity setDeposit(List<Integer> deposit) {
        this.deposit = deposit;
        return this;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public UserEntity setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
        return this;
    }
}