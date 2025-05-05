package com.jhenriquejrc;

import org.junit.Test;

class FuncionarioTerceirizadoTest {

    // Test valid creation: nome, horas, valorPorHora e despesaAdicional
    @Test
    void testFuncionarioTerceirizadoCreationValid() {
        // Base: 30h x 100 = 3000, bônus: 500 * 1.1 = 550, total = 3550
        FuncionarioTerceirizado func = new FuncionarioTerceirizado("Bob", 30, 100, 500);
        assertEquals("Bob", func.getNome());
        assertEquals(30, func.getHorasTrabalhadas());
        assertEquals(100, func.getValorPorHora());
        assertEquals(500, func.getDespesaAdicional());
        assertEquals(3550, func.calcularPagamento());
    }
    
    // Test invalid despesaAdicional maior que R$1000.00
    @Test
    void testFuncionarioTerceirizadoCreationDespesaAdicionalInvalid() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new FuncionarioTerceirizado("Bob", 30, 100, 1500);
        });
        assertEquals("A despesa adicional não pode ultrapassar R$ 1000.00", ex.getMessage());
    }
    
    // Test invalid horas trabalhadas (e.g. menos de 20)
    @Test
    void testFuncionarioTerceirizadoCreationInvalidHoras() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new FuncionarioTerceirizado("Bob", 19, 100, 500);
        });
        assertEquals("A carga horária deve ser entre 20 e 40 horas", ex.getMessage());
    }
    
    // Test setter for despesaAdicional with a valid update
    @Test
    void testFuncionarioTerceirizadoSetDespesaAdicionalValid() {
        FuncionarioTerceirizado func = new FuncionarioTerceirizado("Bob", 30, 100, 500);
        func.setDespesaAdicional(800);
        assertEquals(800, func.getDespesaAdicional());
    }
    
    // Test setter for despesaAdicional with an invalid value
    @Test
    void testFuncionarioTerceirizadoSetDespesaAdicionalInvalid() {
        FuncionarioTerceirizado func = new FuncionarioTerceirizado("Bob", 30, 100, 500);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            func.setDespesaAdicional(1500);
        });
        assertEquals("A despesa adicional não pode ultrapassar R$ 1000.00", ex.getMessage());
    }
    
    // Test invalid valorPorHora update from parent class in funcionario terceirizado
    @Test
    void testFuncionarioTerceirizadoSetValorPorHoraInvalid() {
        FuncionarioTerceirizado func = new FuncionarioTerceirizado("Bob", 30, 100, 500);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            func.setValorPorHora(50);
        });
        assertEquals("O valor por hora deve ser entre 4% e 10% do salário mínimo", ex.getMessage());
    }
}