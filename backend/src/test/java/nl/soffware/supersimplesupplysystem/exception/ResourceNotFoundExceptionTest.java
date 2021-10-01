package nl.soffware.supersimplesupplysystem.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResourceNotFoundExceptionTest {

    private ResourceNotFoundException resourceNotFoundExceptionUnderTest;
private final String resourceName = "resourceName";
    private final String fieldName = "fieldName";
    private final String fieldValue = "fieldValue";

    @BeforeEach
    void setUp() {
        resourceNotFoundExceptionUnderTest = new ResourceNotFoundException(resourceName, fieldName, fieldValue);
    }

    @Test
    void testConstructor() {
        assertEquals(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue), resourceNotFoundExceptionUnderTest.getMessage());
        assertEquals(resourceName, resourceNotFoundExceptionUnderTest.getResourceName());
        assertEquals(fieldName, resourceNotFoundExceptionUnderTest.getFieldName());
        assertEquals(fieldValue, resourceNotFoundExceptionUnderTest.getFieldValue());
    }
}
