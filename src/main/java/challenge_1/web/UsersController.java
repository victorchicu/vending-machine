package challenge_1.web;

import challenge_1.domain.User;
import challenge_1.dto.RegisterDto;
import challenge_1.dto.UserDto;
import challenge_1.exceptions.UserNotFoundException;
import challenge_1.services.UserService;
import challenge_1.web.validators.MultipleOf5;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.security.Principal;

@Validated
@RestController
@RequestMapping(value = "/users")
public class UsersController {
    private final UserService userService;
    private final ConversionService conversionService;

    public UsersController(UserService userService, ConversionService conversionService) {
        this.userService = userService;
        this.conversionService = conversionService;
    }

    @PostMapping
    public UserDto register(@RequestBody RegisterDto registerDto) {
        User user = toUser(registerDto);
        user = userService.save(user);
        return toUserDto(user);
    }

    @PreAuthorize("hasAnyRole('BUYER')")
    @PutMapping("/deposit")
    public void addDeposit(
            Principal principal,
            @Valid
            @NotNull(message = "Amount is null or not valid")
            @Min(value = 5, message = "Amount must be at least 5")
            @Max(value = 100, message = "Amount must not exceed 100")
            @MultipleOf5(message = "Amount must be multiple of 5")
            @RequestParam Integer amount
    ) {
        userService.update(principal, user -> user.topUpDeposit(amount));
    }

    @PreAuthorize("hasAnyRole('BUYER')")
    @PutMapping("/reset")
    public void resetDeposit(Principal principal) {
        userService.update(principal, User::resetDeposit);
    }

    @GetMapping
    public UserDto getMe(Principal principal) {
        return userService.findById(principal.getName())
                .map(this::toUserDto)
                .orElseThrow(UserNotFoundException::new);
    }

    private User toUser(RegisterDto source) {
        return conversionService.convert(source, User.class);
    }

    private UserDto toUserDto(User user) {
        return conversionService.convert(user, UserDto.class);
    }
}