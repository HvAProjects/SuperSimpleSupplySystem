package nl.jed.supersimplesupplysystem.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserControllerTest extends ControllerTest{

    @Autowired
    private UserController controller;

    @Test
    void getCurrentUser() {
    }

    @Test
    void getContent() {
        controller.getContent();
    }

    @Test
    void getUserContent() {
        controller.getUserContent();
    }

    @Test
    void getAdminContent() {
        controller.getAdminContent();
    }

    @Test
    void getModeratorContent() {
        controller.getModeratorContent();
    }
}