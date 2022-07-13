package challenge_1.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class User implements UserDetails {
    private String id;
    private String username;
    private String password;
    private List<Integer> deposit;
    private Collection<? extends GrantedAuthority> authorities;

    public User(String id, String username, String password, List<Integer> deposit, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.deposit = deposit;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public List<Integer> getDeposit() {
        return deposit;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public User topUpDeposit(Integer amount) {
        deposit.add(amount);
        return this;
    }

    public User resetDeposit() {
        deposit.clear();
        return this;
    }

    public int takeFromDeposit(Integer amount) {
        int sum = 0;
        deposit.sort(Comparator.comparing(Integer::intValue).reversed());
        Iterator<Integer> it = deposit.iterator();
        while (amount > sum && it.hasNext()) {
            sum += it.next();
            it.remove();
        }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(deposit, user.deposit) &&
                Objects.equals(authorities, user.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, deposit, authorities);
    }
}