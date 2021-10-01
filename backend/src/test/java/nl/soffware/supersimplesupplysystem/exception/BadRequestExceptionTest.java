package nl.soffware.supersimplesupplysystem.exception;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BadRequestExceptionTest {

    private BadRequestException badRequestExceptionUnderTest1;
    private BadRequestException badRequestExceptionUnderTest2;
    private final Exception exception = new Exception("message");

    @BeforeEach
    void setUp() {
        badRequestExceptionUnderTest1 = new BadRequestException("message", exception);
        badRequestExceptionUnderTest2 = new BadRequestException("message");
    }

    @Test
    void testMessage() {
        assertEquals("message", badRequestExceptionUnderTest1.getMessage());
        assertEquals("message", badRequestExceptionUnderTest2.getMessage());
    }

    @Test
    void testException() {
        assertEquals(exception, badRequestExceptionUnderTest1.getCause());
    }
}
