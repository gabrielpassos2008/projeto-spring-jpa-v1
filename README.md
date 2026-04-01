#  Sistema de Caderninho Digital

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

O sistema utiliza o banco de dados **H2**, um banco relacional leve e embarcado.

* Execução local (sem necessidade de instalação)
* Ideal para desenvolvimento e testes
* Gerenciado automaticamente pelo Spring Boot

---

##  Pré-requisitos

Antes de executar o projeto, você precisa ter instalado:

###  Java (JDK)

* Instale o **JDK 17 ou superior**
* Verifique a instalação:

```bash
java -version
```

---

###  VS Code + Extensões

Instale o **Visual Studio Code** e as seguintes extensões:

* **Extension Pack for Java**
* **Spring Boot Extension Pack**

---

##  Como o sistema funciona

O sistema permite que o operador:

1. Pesquise um cliente através de uma barra de busca
2. Selecione o cliente desejado
3. Visualize o valor total da dívida
4. Escolha entre:

   *  Adicionar valor à dívida
   *  Registrar pagamento
5. O sistema atualiza automaticamente o saldo do cliente

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

### 1. Clone o repositório

```bash
git clone https://github.com/gabrielpassos2008/projeto-spring-jpa-v1
```

---

### 2. Acesse a pasta do projeto

```bash
cd projeto-spring-jpa-v1
```

---

### 3. Execute a aplicação

```bash
./mvnw spring-boot:run
```

---

### 4. Acesse o sistema no navegador

Área do cliente:

```
http://localhost:8080/
```

Área do operador:

```
http://localhost:8080/adm
```

---

##  Dados para teste

Ao iniciar o sistema, o banco de dados é automaticamente populado (caso esteja vazio).

###  Operador

* Email: **[op@email.com](mailto:op@email.com)**
* Senha: **abcd**

###  Cliente

* Email: **[cliente@email.com](mailto:cliente@email.com)**
* Senha: **1234**

---

##  Acesso ao Banco de Dados (H2)

Acesse o console do banco:

```
http://localhost:8080/h2-console
```

### Configuração:

* **JDBC URL:**

```
jdbc:h2:file:./data/bancoJPA
```

* **User:**

```
sa
```

* **Password:**
  (deixe em branco)

---

##  Observações importantes

* O banco de dados é criado automaticamente na pasta do projeto (`/data`)
* Caso o banco esteja vazio, os dados iniciais serão inseridos automaticamente
* Não é necessário instalar nenhum banco externo

---

##  Melhorias futuras

*  Versão mobile (aplicativo)
*  Notificações de vencimento
*  Dashboard com gráficos
*  Controle de múltiplos operadores

---

##  Autor

Gabriel

---

## 📄 Licença

Este projeto é de uso acadêmico.

