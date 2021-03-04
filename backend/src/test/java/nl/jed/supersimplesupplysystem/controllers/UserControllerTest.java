package nl.jed.supersimplesupplysystem.controllers;

import nl.jed.supersimplesupplysystem.testconfigurations.TestUserConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = TestUserConfiguration.class
)
class UserControllerTest{

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
    void getContent() throws Exception {
        mockMvc.perform(get("/api/all")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("user@company.com")
    void getUserContent() throws Exception {
        mockMvc.perform(get("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin@company.com")
    void getAdminContent() throws Exception {
        mockMvc.perform(get("/api/admin")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("mod@company.com")
    void getModeratorContent() throws Exception {
        mockMvc.perform(get("/api/mod")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("user@company.com")
    void testIncorrectPermissions() throws Exception {
        mockMvc.perform(get("/api/mod")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isForbidden());
    }
}