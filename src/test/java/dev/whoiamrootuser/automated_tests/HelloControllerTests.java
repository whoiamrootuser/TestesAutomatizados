package dev.whoiamrootuser.automated_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HelloControllerTests {
    @Test
    void testHello() {
        //Arrange
        HelloController helloController = new HelloController();
        String expected = "Hello World";
        
        //Act
        String actual = helloController.hello();

        //Assert
        assertEquals(expected, actual);
    }
}
