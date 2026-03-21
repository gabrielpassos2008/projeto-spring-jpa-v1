package com.gabriel.projeto_spring_jpa_v1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gabriel.projeto_spring_jpa_v1.model.Cliente;
import com.gabriel.projeto_spring_jpa_v1.model.Divida;
import com.gabriel.projeto_spring_jpa_v1.repository.ClienteRepository;
import com.gabriel.projeto_spring_jpa_v1.repository.DividaRepository;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(ClienteRepository clienteRepository,DividaRepository dividaRepository) {
        return args -> {

            // Só executa se o banco estiver vazio
            if (clienteRepository.count() == 0) {

                // Criando clientes
                Cliente cliente1 = new Cliente();
                cliente1.setNome("João");
                clienteRepository.save(cliente1);

                Cliente cliente2 = new Cliente();
                cliente2.setNome("Maria");
                clienteRepository.save(cliente2);

                // Criando dívida para o cliente 1
                Divida divida = new Divida();
                divida.setCliente(cliente1);
                divida.setValor(500);
                divida.setStatus("PENDENTE");

                dividaRepository.save(divida);
            }
        };
    }
}
