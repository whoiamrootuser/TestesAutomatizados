package com.jhenriquejrc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FuncionarioTest {

    // Test valid creation: nome, horas, valorPorHora
    @Test
    void testFuncionarioCreationValid() {
        // 30h x 100 = 3000, which is between R$1518.00 and R$100000.00
        Funcionario func = new Funcionario("Alice", 30, 100);
        assertEquals("Alice", func.getNome());
        assertEquals(30, func.getHorasTrabalhadas());
        assertEquals(100, func.getValorPorHora());
        assertEquals(3000, func.calcularPagamento());
    }
    
    // Test invalid hours below 20
    @Test
    void testFuncionarioCreationInvalidHorasBaixo() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Funcionario("Alice", 19, 100);
        });
        assertEquals("A carga horária deve ser entre 20 e 40 horas", ex.getMessage());
    }
    
    // Test invalid hours above 40
    @Test
    void testFuncionarioCreationInvalidHorasAlto() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Funcionario("Alice", 41, 100);
        });
        assertEquals("A carga horária deve ser entre 20 e 40 horas", ex.getMessage());
    }
    
    // Test invalid hourly value below 4% do salário mínimo (4% of 1518 ≈ 60.72)
    @Test
    void testFuncionarioCreationInvalidValorPorHoraBaixo() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Funcionario("Alice", 30, 60); 
        });
        assertEquals("O valor por hora deve ser entre 4% e 10% do salário mínimo", ex.getMessage());
    }
    
    // Test invalid hourly value above 10% do salário mínimo (10% of 1518 ≈ 151.8)
    @Test
    void testFuncionarioCreationInvalidValorPorHoraAlto() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Funcionario("Alice", 30, 160);
        });
        assertEquals("O valor por hora deve ser entre 4% e 10% do salário mínimo", ex.getMessage());
    }
    
    // Test when the calculated pagamento (ex: 20h * 60.72) is below the salário mínimo
    @Test
    void testFuncionarioCreationPagamentoAbaixoSalarioMinimo() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Funcionario("Alice", 20, 60.72);
        });
        assertEquals("O pagamento deve ser maior ou igual ao salário mínimo de R$ 1518.00", ex.getMessage());
    }
    
    // Test when the calculated pagamento (ex: 40h * 3000) exceeds o teto de R$100000.00
    @Test
    void testFuncionarioCreationPagamentoAcimaTeto() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Funcionario("Alice", 40, 3000);
        });
        assertEquals("O pagamento não pode ultrapassar R$ 100000.00", ex.getMessage());
    }
    
    // Test setter for horasTrabalhadas with a valid value
    @Test
    void testFuncionarioSetHorasValid() {
        Funcionario func = new Funcionario("Alice", 30, 100);
        func.setHorasTrabalhadas(35);
        assertEquals(35, func.getHorasTrabalhadas());
    }
    
    // Test setter for horasTrabalhadas with an invalid value
    @Test
    void testFuncionarioSetHorasInvalid() {
        Funcionario func = new Funcionario("Alice", 30, 100);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            func.setHorasTrabalhadas(45);
        });
        assertEquals("A carga horária deve ser entre 20 e 40 horas", ex.getMessage());
    }
    
    // Test setter for valorPorHora with a valid value
    @Test
    void testFuncionarioSetValorPorHoraValid() {
        Funcionario func = new Funcionario("Alice", 30, 100);
        func.setValorPorHora(120);
        assertEquals(120, func.getValorPorHora());
    }
    
    // Test setter for valorPorHora with an invalid value
    @Test
    void testFuncionarioSetValorPorHoraInvalid() {
        Funcionario func = new Funcionario("Alice", 30, 100);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            func.setValorPorHora(50);
        });
        assertEquals("O valor por hora deve ser entre 4% e 10% do salário mínimo", ex.getMessage());
    }
}