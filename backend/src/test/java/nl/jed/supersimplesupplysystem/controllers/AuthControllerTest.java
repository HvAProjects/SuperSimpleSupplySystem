package nl.jed.supersimplesupplysystem.controllers;

import nl.jed.supersimplesupplysystem.dto.LoginRequest;
import nl.jed.supersimplesupplysystem.dto.SignUpRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static nl.jed.supersimplesupplysystem.helpers.JSONWriter.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthControllerTest extends ControllerTest{

    @Test
    void authenticateUser() throws Exception {
        LoginRequest req = new LoginRequest();
        req.setEmail("test@test.nl");
        req.setPassword("Password");

        mvc.perform(post("/api/auth/signin")
            .content(asJsonString(req))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk());
    }


    @Test
    void registerUser() throws Exception {
        SignUpRequest req = new SignUpRequest();
        req.setEmail("test@test.nl");
        req.setPassword("Password");

        mvc.perform(post("/api/auth/signup")
            .content(asJsonString(req))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk());

        mvc.perform(post("/api/auth/signup")
            .content(asJsonString(req))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isBadRequest());
    }
}