package challenge_1.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class UpdatePasswordDto {
    private final String oldPassword;
    private final String newPassword;

    @JsonCreator
    public UpdatePasswordDto(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
