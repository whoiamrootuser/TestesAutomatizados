package dev.whoiamrootuser.automated_tests.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CalculadoraTest {

    @Test
    void testDefaultConstructor() {
        // Arrange & Act
        Calculadora calculadora = new Calculadora();

        // Assert
        assertEquals(0, calculadora.getMemoria());
    }

    @Test
    void testParameterizedConstructor() {
        // Arrange
        int initialMemory = 50;

        // Act
        Calculadora calculadora = new Calculadora(initialMemory);

        // Assert
        assertEquals(initialMemory, calculadora.getMemoria());
    }

    @Test
    void testZerarMemoria() {
        // Arrange
        Calculadora calculadora = new Calculadora(100);

        // Act
        calculadora.zerarMemoria();

        // Assert
        assertEquals(0, calculadora.getMemoria());
    }

    @Test
    void testSomar() {
        // Arrange
        Calculadora calculadora = new Calculadora(10);
        int valor = 5;

        // Act
        calculadora.somar(valor);

        // Assert
        assertEquals(15, calculadora.getMemoria());
    }


    @Test
    void testSomarNumeroNegativo() {
        // Arrange
        Calculadora calculadora = new Calculadora(10);
        int valor = -5;

        // Act
        calculadora.somar(valor);

        // Assert
        assertEquals(5, calculadora.getMemoria());
    }

    @Test
    void testSubtrair() {
        // Arrange
        Calculadora calculadora = new Calculadora(10);
        int valor = 3;

        // Act
        calculadora.subtrair(valor);

        // Assert
        assertEquals(7, calculadora.getMemoria());
    }

    @Test
    void testMultiplicar() {
        // Arrange
        Calculadora calculadora = new Calculadora(10);
        int valor = 2;

        // Act
        calculadora.multiplicar(valor);

        // Assert
        assertEquals(20, calculadora.getMemoria());
    }

    @Test
    void testDividirHappyPath() throws Exception {
        // Arrange
        Calculadora calculadora = new Calculadora(10);
        int valor = 2;

        // Act
        calculadora.dividir(valor);

        // Assert
        assertEquals(5, calculadora.getMemoria());
    }

    @Test
    void testDividirByZero() {
        // Arrange
        Calculadora calculadora = new Calculadora(10);
        int valor = 0;

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> calculadora.dividir(valor));
        assertEquals("Divisão por zero!!!", exception.getMessage());
    }

    @Test
    void testExponenciarHappyPath() throws Exception {
        // Arrange
        Calculadora calculadora = new Calculadora(2);
        int valor = 3;

        // Act
        calculadora.exponenciar(valor);

        // Assert
        assertEquals(16, calculadora.getMemoria()); // 2^8 = 256
    }

    @Test
    void testExponenciarInvalidExponent() {
        // Arrange
        Calculadora calculadora = new Calculadora(2);
        int valor = 11;

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> calculadora.exponenciar(valor));
        assertEquals("Expoente incorreto, valor máximo é 10.", exception.getMessage());
    }
}
