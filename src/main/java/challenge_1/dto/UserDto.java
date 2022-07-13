package challenge_1.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class UserDto {
    private final String username;
    private final List<Integer> deposit;

    @JsonCreator
    public UserDto(String username, List<Integer> deposit) {
        this.username = username;
        this.deposit = deposit;
    }

    public String getUsername() {
        return username;
    }

    public List<Integer> getDeposit() {
        return deposit;
    }
}
