package com.gabriel.projeto_spring_jpa_v1;

import com.gabriel.projeto_spring_jpa_v1.model.Cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.gabriel.projeto_spring_jpa_v1.repository.ClienteRepository;
import com.gabriel.projeto_spring_jpa_v1.service.ClienteService;



@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {
    
    // classe que vai ser mockada, criado um objeto fake
    @Mock
    private ClienteRepository repository;
    // classe que aonde vai ser feito o teste
    @InjectMocks
    private ClienteService clienteService;
    private Cliente cliente;
    
    @BeforeEach
    void setUp(){
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setApelido("M");
        cliente.setNome("Maria");
        cliente.setEmail("maria@gmail");
        cliente.setTelefone("5199999");
        cliente.setSenha("123");
    }

    @Test 
    void validarLogin_quandoCredenciaisCorretas(){
        when(repository.findByEmailAndSenha("maria@gmail","123")).thenReturn(Optional.of(cliente));
        Cliente resultado = clienteService.validarLogin("maria@gmail", "123");
        assertEquals(resultado,cliente);
    }
}
