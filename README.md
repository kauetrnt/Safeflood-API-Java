# SafeFlood API

API REST para o sistema SafeFlood, uma plataforma de monitoramento e alerta de enchentes.

## Tecnologias Utilizadas

- Java 21
- Quarkus Framework
- Hibernate ORM com Panache
- Oracle Database
- Docker
- Maven

## Estrutura do Projeto

O projeto segue uma arquitetura em camadas:

- **Resource**: Endpoints REST da API
- **Service**: Lógica de negócio
- **Model**: Entidades JPA
- **Exception**: Tratamento de Exceções da API

## Endpoints da API

### Usuários (`/usuarios`)
- `GET /usuarios`: Lista todos os usuários
- `GET /usuarios/{id}`: Busca usuário por ID
- `GET /usuarios/cpf/{cpf}`: Busca usuário por CPF
- `GET /usuarios/email/{email}`: Busca usuário por email
- `POST /usuarios`: Cria novo usuário
- `PUT /usuarios/{id}`: Atualiza usuário
- `DELETE /usuarios/{id}`: Remove usuário

### Alertas (`/alertas`)
- `GET /alertas`: Lista todos os alertas
- `GET /alertas/{id}`: Busca alerta por ID
- `GET /alertas/usuario/{usuarioId}`: Lista alertas por usuário
- `POST /alertas`: Cria novo alerta
- `PUT /alertas/{id}`: Atualiza alerta
- `DELETE /alertas/{id}`: Remove alerta

### Áreas de Risco (`/areas-risco`)
- `GET /areas-risco`: Lista todas as áreas de risco
- `GET /areas-risco/{id}`: Busca área por ID
- `POST /areas-risco`: Cria nova área de risco
- `PUT /areas-risco/{id}`: Atualiza área de risco
- `DELETE /areas-risco/{id}`: Remove área de risco

### Rotas de Fuga (`/rotas-fuga`)
- `GET /rotas-fuga`: Lista todas as rotas
- `GET /rotas-fuga/{id}`: Busca rota por ID
- `GET /rotas-fuga/area-risco/{areaRiscoId}`: Lista rotas por área de risco
- `POST /rotas-fuga`: Cria nova rota
- `PUT /rotas-fuga/{id}`: Atualiza rota
- `DELETE /rotas-fuga/{id}`: Remove rota

### Eventos (`/eventos`)
- `GET /eventos`: Lista todos os eventos
- `GET /eventos/{id}`: Busca evento por ID
- `POST /eventos`: Cria novo evento
- `PUT /eventos/{id}`: Atualiza evento
- `DELETE /eventos/{id}`: Remove evento

### Notificações (`/notificacoes`)
- `GET /notificacoes`: Lista todas as notificações
- `GET /notificacoes/{id}`: Busca notificação por ID
- `GET /notificacoes/tipo/{tipo}`: Lista notificações por tipo
- `POST /notificacoes`: Cria nova notificação
- `PUT /notificacoes/{id}`: Atualiza notificação
- `DELETE /notificacoes/{id}`: Remove notificação

## Configuração do Ambiente

### Pré-requisitos
- Java 21
- Maven
- Docker (opcional)
- Oracle Database

### Variáveis de Ambiente
```properties
# Database Configuration
QUARKUS_DATASOURCE_JDBC_URL=jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl
QUARKUS_DATASOURCE_USERNAME=seu_usuario
QUARKUS_DATASOURCE_PASSWORD=sua_senha

# HTTP Configuration
QUARKUS_HTTP_PORT=8080
```

## Executando a Aplicação

### Modo Desenvolvimento
```bash
./mvnw quarkus:dev
```

### Build e Execução
```bash
./mvnw package
java -jar target/quarkus-app/quarkus-run.jar
```

### Docker
```bash
docker build -t safeflood-api .
docker run -p 8080:8080 safeflood-api
```

## Deploy

O projeto está configurado para deploy no Render. O arquivo `render.yaml` contém as configurações necessárias.

### Variáveis de Ambiente no Render
- `QUARKUS_DATASOURCE_JDBC_URL`
- `QUARKUS_DATASOURCE_USERNAME`
- `QUARKUS_DATASOURCE_PASSWORD`

## CORS

A API está configurada para aceitar requisições do frontend em `http://localhost:3000`. Para produção, atualize a configuração CORS no `application.properties`:

```properties
quarkus.http.cors.origins=https://seu-frontend.com
```

## Contribuição

1. Faça o fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request
