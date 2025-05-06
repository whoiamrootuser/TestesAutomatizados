package dev.whoiamrootuser.atividade_03_funcionarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import dev.whoiamrootuser.atividade_03_funcionarios.domain.Funcionario;

public class FuncionarioTest {
    
    
    @Test
    void testConstrutorValido() {
        
        Funcionario f = new Funcionario("João", 40, 100.0);
        assertEquals(4000.0, f.calcularPagamento());
    }

    @Test
    void testConstrutorHorasAcimaLimite() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Funcionario("João", 45, 100.0);
        });
        assertEquals("O número de horas trabalhadas por funcionários próprios deve ser entre 20 e 40.", ex.getMessage());
    }
    
    @Test
    void testConstrutorHorasAbaixoMinimo() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Funcionario("João", 15, 100.0);
        });
        assertEquals("O número de horas trabalhadas por funcionários próprios deve ser entre 20 e 40.", ex.getMessage());
    }

    @Test
    void testConstrutorValorHoraInvalido() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Funcionario("João", 40, 50.0); // 50 abaixo de 4% do salário mínimo
        });
        assertEquals("O valor da hora deve ser entre 4% e 10% do salário mínimo.", ex.getMessage());
    }
    
    @Test
    void testConstrutorPagamentoAbaixoDoMinimo() {
        // Ex: 20 horas * 70.0 = 1400.0 < 1518.00
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            Funcionario f = new Funcionario("João", 20, 70.0);
            f.calcularPagamento();
        });
        assertEquals("O pagamento deve ser maior ou igual ao salário mínimo de R$ 1518.00.", ex.getMessage());
    }
    
    @Test
    void testSetterHorasInvalidas() {
        Funcionario f = new Funcionario("João", 40, 100.0);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            f.setHorasTrabalhadas(45);
        });
        assertEquals("O número de horas trabalhadas por funcionários próprios deve ser entre 20 e 40.", ex.getMessage());
    }
    
    @Test
    void testSetterValorHoraInvalido() {
        Funcionario f = new Funcionario("João", 40, 100.0);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            f.setValorHora(50.0);
        });
        assertEquals("O valor da hora deve ser entre 4% e 10% do salário mínimo.", ex.getMessage());
    }
}