package com.gabriel.projeto_spring_jpa_v1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gabriel.projeto_spring_jpa_v1.model.Cliente;
import com.gabriel.projeto_spring_jpa_v1.model.Operador;
import com.gabriel.projeto_spring_jpa_v1.repository.ClienteRepository;
import com.gabriel.projeto_spring_jpa_v1.repository.OperadorRepository;
@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(OperadorRepository operadorRepository,
                                   ClienteRepository clienteRepository) {

        return args -> {

            //  verifica se já existe operador
            if (operadorRepository.count() == 0) {

                Operador operador = new Operador();
                operador.setEmail("op@email.com");
                operador.setNome("O Operador");
                operador.setSenha("abcd");
                operador.setTelefone("123321");

                operadorRepository.save(operador);

                // cria cliente vinculado ao operador
                Cliente cliente = new Cliente();
                cliente.setEmail("cliente@email.com");
                cliente.setNome("O cliente");
                cliente.setApelido("Cliente"); 
                cliente.setSenha("1234");
                cliente.setTelefone("987456");
                cliente.setOperador(operador);

                clienteRepository.save(cliente);

                System.out.println("pronto");
            }
        };
    }
}

