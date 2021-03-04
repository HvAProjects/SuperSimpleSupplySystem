package nl.jed.supersimplesupplysystem.controllers;

import nl.jed.supersimplesupplysystem.dto.LoginRequest;
import nl.jed.supersimplesupplysystem.dto.SignUpRequest;
import nl.jed.supersimplesupplysystem.dto.SocialProvider;
import nl.jed.supersimplesupplysystem.testconfigurations.TestUserConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static nl.jed.supersimplesupplysystem.helpers.JSONWriter.asJsonString;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = TestUserConfiguration.class
)
class AuthControllerTest{
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .apply(springSecurity())
                .build();
    }
    @Test
    void authenticateUser() throws Exception {
        LoginRequest req = new LoginRequest();
        req.setEmail("user@company.com");
        req.setPassword("Password");

        mockMvc.perform(post("/api/auth/signin")
            .content(asJsonString(req))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk());
    }

    @Test
    void authenticateUserIncorrectPassword() throws Exception {
        LoginRequest req = new LoginRequest();
        req.setEmail("userr@company.com");
        req.setPassword("Password");

        mockMvc.perform(post("/api/auth/signin")
                .content(asJsonString(req))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest());
    }


    @Test
    void registerUser() throws Exception {
        SignUpRequest req = new SignUpRequest();
        req.setEmail("test@test.nl");
        req.setPassword("Password");
        req.setMatchingPassword("Password");
        req.setDisplayName("testUser");
        req.setSocialProvider(SocialProvider.LOCAL);

        mockMvc.perform(post("/api/auth/signup")
            .content(asJsonString(req))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk());

        mockMvc.perform(post("/api/auth/signup")
            .content(asJsonString(req))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isBadRequest());
    }
}