package com.iftm.client.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.iftm.client.entities.Client;


@DataJpaTest
public class ClientRepositoryTest {

     @Autowired
    private ClientRepository clientRepository;

    /**
     * Deve a buscar todos os clientes que contenha nome informado
     * Entrada: nome do cliente
     * Saída: lista de clientes
     */
    @Test
    void testFindByName() {
        //Arrange
        String name = "jo";
        //Act & Assert
        var clients = clientRepository.searchByName(name);

        assertNotNull(clients);
        assertEquals(clients.size(), 2);
    }

    /**
     * Deve buscar um cliente pelo id correspondente. 
     * Entrada: nome do cliente
     * Saída: cliente
     */
    @Test
    void testFindOneByName() {
        //Arrange
        String name = "clarisse lispector";
        //Act
        var client = clientRepository.findByNameIgnoreCase(name);
        //Assert
        assertNotNull(client);
    }

    /**
     * Deve gerar uma exceção quando buscar um cliente usando um nome invalido.
     * Entrada: nome do cliente
     * Saída:  lista vazia
     */
    @Test
    void testFindOneByNameEmptyResult() { 
        //Arrange
        String name = "invalid name";

        //Act
        Optional<Client> client = clientRepository.findByNameIgnoreCase(name);
       
        //Assert
        assertEquals(client.isPresent(), false);
    }

    /**
     * Deve buscar um cliente pelo id correspondente. 
     * Entrada: Data de nascimento inicial e final
     * Saída: lista cliente
     */
    @Test
    void testFindClientByBirthDateBetween() {
        //Arrange
        Instant dataInicio = Instant.parse("1990-01-01T00:00:00Z");
        Instant dataTermino = Instant.parse("1998-01-01T00:00:00Z");

        //Act
        var clients = clientRepository.findClientByBirthDateBetween(dataInicio, dataTermino);

        //Assert
        assertNotNull(clients);
        assertEquals(clients.size(), 3);
    }
}
