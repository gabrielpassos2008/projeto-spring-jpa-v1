# Sistema de Caderninho Digital

Sistema web desenvolvido para auxiliar no controle de dívidas de clientes em estabelecimentos comerciais, substituindo o tradicional "caderninho" físico por uma solução digital simples e eficiente.

---

##  Funcionalidades

*  Autenticação de operador
*  Cadastro de clientes
*  Registro de dívidas
*  Adição de valores à dívida
*  Registro de pagamentos (abatimento)
*  Visualização do saldo devedor
*  Busca dinâmica de clientes
*  Histórico de movimentações

---

##  Tecnologias Utilizadas

* **Java**
* **Spring Boot**
* **Spring Data JPA**
* **Hibernate**
* **Thymeleaf**
* **HTML / CSS**
* **H2 Database**

---

##  Banco de Dados

O sistema utiliza o banco de dados **H2**, que é um banco relacional leve e embarcado.

* Execução local (sem necessidade de instalação)
* Ideal para desenvolvimento e testes
* Gerenciado automaticamente pelo Spring Boot

---

##  Como o sistema funciona

O sistema permite que o operador:

1. Pesquise um cliente através de uma barra de busca;
2. Selecione o cliente desejado;
3. Visualize o valor total da dívida;
4. Escolha entre:

   *  Adicionar valor à dívida
   *  Registrar pagamento
5. O sistema atualiza automaticamente o saldo do cliente.

---

##  Estrutura do Projeto

```
src/
 ├── controller/
 ├── service/
 ├── repository/
 ├── model/
 └── templates/
```

---

##  Como executar o projeto

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/seu-repo.git
```

2. Acesse a pasta do projeto:

```bash
cd seu-repo
```

3. Execute a aplicação:

```bash
./mvnw spring-boot:run
```

4. Acesse no navegador:

```
http://localhost:8080
```

---

##  Melhorias futuras

*  Versão mobile (aplicativo)
*  Notificações de vencimento
*  Dashboard com gráficos
*  Controle de múltiplos operadores

---

## 👨‍💻 Autor

Gabriel

---

## 📄 Licença

Este projeto é de uso acadêmico.
