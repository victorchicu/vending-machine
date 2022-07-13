package challenge_1.controllers;

import challenge_1.BaseRunner;
import challenge_1.domain.User;
import challenge_1.domain.roles.BuyerRole;
import challenge_1.domain.roles.SellerRole;
import challenge_1.dto.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerIT extends BaseRunner {
    @Override
    public void setup() {
        super.setup();
        userService.save(
                new User("62cd7fea4e2fa0613d907fda", "buyer", "12345678!",
                        Collections.emptyList(),
                        Arrays.asList(new BuyerRole())
                )
        );
        userService.save(
                new User("62cd7ff3c5f6f847b9966af7", "seller", "12345678!",
                        Collections.emptyList(),
                        Arrays.asList(new SellerRole())
                )
        );
    }

    @Test
    public void should_create_user() throws Exception {
        createRandomUser();
    }

    @Test
    @WithMockUser(username = "62cd7fea4e2fa0613d907fda", password = "12345678!", authorities = "ROLE_BUYER")
    public void should_add_valid_deposit() throws Exception {
        addDepositThenExpect(10, status().isOk());
        UserDto actualUser = getMe();
        Assert.assertEquals(10, actualUser.getDeposit().stream().mapToInt(Integer::intValue).sum());
    }

    @Test
    @WithMockUser(username = "62cd7fea4e2fa0613d907fda", password = "12345678!", authorities = "ROLE_BUYER")
    public void should_add_deposit_amount_not_multiple_of_5_then_expect_bad_request() throws Exception {
        for (int amount : Arrays.asList(-1, 0, 1, 101)) {
            addDepositThenExpect(amount, status().isBadRequest());
        }
    }

    @Test
    @WithMockUser(username = "62cd7fea4e2fa0613d907fda", password = "12345678!", authorities = "ROLE_BUYER")
    public void should_reset_deposit_then_expect_ok() throws Exception {
        addDepositThenExpect(10, status().isOk());
        resetDeposit();
        UserDto actualUser = getMe();
        Assert.assertTrue(actualUser.getDeposit().isEmpty());
    }

    @Test
    @WithMockUser(username = "62cd7ff3c5f6f847b9966af7", password = "12345678!", authorities = "ROLE_SELLER")
    public void should_add_deposit_by_a_seller_then_expect_forbidden() throws Exception {
        addDepositThenExpect(10, status().isForbidden());
    }
}
