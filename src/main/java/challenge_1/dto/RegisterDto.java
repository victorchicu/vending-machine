package challenge_1.dto;

import challenge_1.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.EnumSet;

public class RegisterDto {
    private final String username;
    private final String password;
    private final EnumSet<RoleType> roles;

    @JsonCreator
    public RegisterDto(String username, String password, EnumSet<RoleType> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public EnumSet<RoleType> getRoles() {
        return roles;
    }
}
