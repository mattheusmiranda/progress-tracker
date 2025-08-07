# Progress Tracker
## 📌 Visão Geral
Projeto estruturado em **arquitetura hexagonal multi-módulo** com Kotlin e Spring Boot, focado em modularidade, desacoplamento e facilidade de manutenção. A aplicação interage via Kafka e API's, oferece observabilidade com métricas e usa o banco MySql para desenvolvimento e H2 para armazenamento em memória, utilizado em testes integrados.

---

## 🚀 Tecnologias Utilizadas

- Kotlin (JDK 21)
- Spring Boot 3.5.x
- Gradle (Kotlin DSL)
- Banco de dados: MySql e H2 com migrations Flyway
- Kafka (publicação e consumo)
- Observabilidade: Micrometer, Prometheus, Grafana
- Docker para containerização e Kafka local

---

## 📂 Estrutura do Projeto
```
progress-tracker/
├── build.gradle.kts  # Configuração raiz
├── settings.gradle.kts  # Define os submódulos
├── core/ # Domínio puro: entidades, portas (interfaces), regras de negócio
│   ├── build.gradle.kts
│   ├── src/main/kotlin/com/school/core/
│   └── (entidades, regras de negócio, portas)
├── infrastructure/ # Implementações técnicas: repositórios, JPA, Kafka, adaptadores
│   ├── build.gradle.kts
│   ├── src/main/kotlin/com/school/infrastructure/
│   └── (repositórios, adaptação de tecnologia, banco de dados)
└──  entrypoint/ # APIs REST, modelos de requisição/resposta, mapeadores, controllers
│    ├── build.gradle.kts
│    ├── src/main/kotlin/com/school/entrypoint/
│    └── (APIS, Consumidores e Consumidores, avros)
└── bootstrap/ # Classe principal SpringBoot, configuração de escaneamento e beans
     ├── build.gradle.kts
     └── src/main/kotlin/com/school/bootstrap/
```
---
## 🔹 Descrição dos Módulos

### 1️⃣ Core (`core/`)
- Contém **modelos de domínio** e **interfaces (ports)** que definem contratos para adaptadores.
- Não depende de frameworks ou bibliotecas externas, garantindo pureza do domínio.

### 2️⃣ Infrastructure (`infrastructure/`)
- Implementa os **adapters técnicos** (e.g., `JpaRepository`, mapeadores, Kafka producers/consumers).
- Anotações Spring (`@Repository`, `@Service`) e configurações específicas de banco e mensageria.
- Depende do `core` para usar os contratos e modelos.

### 3️⃣ Entrypoint (`entrypoint/`)
- Disponibiliza as APIs REST (`@RestController`), modelos de entrada/saída, validações e mapeamentos.
- Responsável pela camada de interface com o usuário ou clientes externos.
- Depende do `core` e do `infrastructure` para interagir com a lógica e persistência.

### 4️⃣ Bootstrap (`bootstrap/`)
- Ponto de entrada da aplicação, com a classe `@SpringBootApplication`.
- Configura o scan base de pacotes (`scanBasePackages`), habilita `@EnableJpaRepositories` e `@EntityScan`.
- Deve depender dos demais módulos para inicializar toda a aplicação corretamente.
- Nenhum outro módulo deve depender do `bootstrap`.

---

## 🛠️ Como Rodar o Projeto
1. Clone o repositório:
   ```sh
   git clone git@github.com:mattheusmiranda/progress-tracker.git
   cd progress-tracker
   ```
2. Compile e construa o projeto:
   ```sh
   ./gradlew build
   ```
3. Suba os containers do docker:
   ```sh
    cd docker
    docker-compose up -d
   ```
4. Execute o projeto via Spring Boot:
   ```sh
   ./gradlew :bootstrap:bootRun
   ```
Obs: Caso hajá alguma duvida sobre comandos do docker, na pasta docker#comands tem alguns comandos utilizados durante o desenvolvimento que podem ser úteis.

---

## 📊 Observabilidade
- Métricas expostas via Micrometer no endpoint `/actuator/prometheus`.
- Visualização recomendada com Prometheus e Grafana para monitoramento.

## 📩 Mensageria Kafka
- Utilize `docker-compose` para levantar Kafka e Zookeeper localmente.
- Eventos são publicados e consumidos via tópicos configurados no módulo `infrastructure`.

## 🎯 Benefícios da Arquitetura Hexagonal
- **Baixo acoplamento** entre domínio e infraestrutura.
- Facilita testes, manutenção e evolução independente das tecnologias.
- Protege regras de negócio contra mudanças externas.
- Modularização clara ajuda na escalabilidade do projeto.
