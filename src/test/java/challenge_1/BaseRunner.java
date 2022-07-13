package challenge_1;

import challenge_1.dto.RegisterDto;
import challenge_1.dto.UserDto;
import challenge_1.enums.RoleType;
import challenge_1.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.MongoDBContainer;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = BaseRunner.Initializer.class)
public class BaseRunner {
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    static {
        mongoDBContainer.start();
    }

    @Autowired
    private WebApplicationContext context;
    @Autowired
    protected UserService userService;
    @Autowired
    protected ObjectMapper objectMapper;


    protected MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    protected void resetDeposit() throws Exception {
        mvc.perform(put("/users/reset")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    protected void addDepositThenExpect(int amount, ResultMatcher matcher) throws Exception {
        mvc.perform(put("/users/deposit?amount=" + amount)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(matcher);
    }

    protected UserDto getMe() throws Exception {
        return objectMapper.readValue(
                mvc.perform(get("/users").accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString()
                ,
                UserDto.class
        );
    }

    protected UserDto createRandomUser() throws Exception {
        RegisterDto registerDto = new RegisterDto(UUID.randomUUID().toString(), UUID.randomUUID().toString(), EnumSet.of(RoleType.BUYER));
        String jsonString = objectMapper.writeValueAsString(registerDto);
        return objectMapper.readValue(
                mvc.perform(post("/users")
                                .content(jsonString)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString(),
                UserDto.class
        );
    }


    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            List<String> props = Arrays.asList("spring.data.mongodb.uri=" + mongoDBContainer.getReplicaSetUrl());
            TestPropertyValues.of(props).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
