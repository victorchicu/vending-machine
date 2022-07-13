package challenge_1.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class LoginDto {
    private final String username;
    private final String password;

    @JsonCreator
    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
