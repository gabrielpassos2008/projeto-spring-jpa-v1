package com.gabriel.projeto_spring_jpa_v1;

import com.gabriel.projeto_spring_jpa_v1.model.Cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
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
    private Cliente clienteMaria;

    // Objeto de teste com dados fixos para simular um cliente
    @BeforeEach
    void setUp() {
        clienteMaria = new Cliente();
        clienteMaria.setId(1L);
        clienteMaria.setApelido("Maria");
        clienteMaria.setNome("Maria");
        clienteMaria.setEmail("maria@gmail.com");
        clienteMaria.setTelefone("51995451888");
        clienteMaria.setSenha("12345678");
    }

    @Test
    void validarLogin_quandoCredenciaisCorretasMock() {
        when(repository.findByEmailAndSenha("maria@gmail.com", "12345678")).thenReturn(Optional.of(clienteMaria));
        Cliente resultado = clienteService.validarLogin("maria@gmail.com", "12345678");
        assertEquals(resultado, clienteMaria);

    }

    @Test
    void validarEmailDisponivel_quandoEmailJaExistenteMock() {
        when(repository.findByEmail("qualquer@gmail.com")).thenReturn(Optional.empty()); // retornar um objeto vazio
        boolean resultado = clienteService.validarEmailDisponivel("qualquer@gmail.com");
        assertTrue(resultado);
    }

    @Test
    void validarEmailDisponivel_quandoEmailNaoExistenteMock() {
        when(repository.findByEmail("maria@gmail.com")).thenReturn(Optional.of(clienteMaria)); // retornar um objeto existente                                                                                                    
        boolean resultado = clienteService.validarEmailDisponivel("maria@gmail.com");
        assertFalse(resultado);
    }

    @Test
    void validarCadastrarCLiente_quandoNaoHouverErrosDeCampoMock() {
        when(repository.save(clienteMaria)).thenReturn(null);

        List<String> resultadoExperado = clienteService.cadastrarCLiente(clienteMaria);
        assertNull(resultadoExperado);
    }

    @Test
    void validarCadastrarCliente_quandoHouverErrosDeCampoMock() {
            clienteMaria.setEmail(""); // força erro

            List<String> resultado = clienteService.cadastrarCLiente(clienteMaria);

            assertNotNull(resultado);
            assertFalse(resultado.isEmpty());
        }
}
