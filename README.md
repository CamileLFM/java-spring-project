# IFgram API com Spring Security

Este é um projetinho de API feito em **Spring Boot** com autenticação usando **Spring Security**.  
Ele serve como exemplo de como criar endpoints com login, cadastro de usuário e posts protegidos por token.

## Como rodar

1. Primeiro precisa ter o **Java 17** instalado.  
   Para ver a versão:
   ```
   java -version
   ```
   Se aparecer 17 ou mais, já está ok.

2. Precisa também do **Maven**. Para ver se tem:
   ```
   mvn -v
   ```

3. Depois que baixar este repositório, entra na pasta do projeto:
   ```
   cd ifgram-api-spring-security-v2
   ```

4. Para compilar e rodar tem duas opções:

   **Opção 1 (rodar o jar):**
   ```
   mvn clean package -DskipTests
   java -jar target/ifgram-secured-api-0.0.1-SNAPSHOT.jar
   ```

   **Opção 2 (mais simples):**
   ```
   mvn spring-boot:run
   ```

5. A aplicação vai rodar na porta **8080**.  
   Para testar se subiu, abre no navegador ou usa curl:
   ```
   http://localhost:8080/health
   ```
   Deve aparecer `{"status":"UP"}`.

## Como usar a API

### Criar usuário
```
POST http://localhost:8080/auth/signup
Content-Type: application/json

{
  "email": "camile@gmail.com",
  "password": "senha123",
  "telefone": "123456789"
}
```
Resposta: vai vir os dados do usuário e também um **token**.

### Fazer login
```
POST http://localhost:8080/auth/login
Content-Type: application/json

{
  "email": "camile@gmail.com",
  "password": "senha123"
}
```
Resposta: `{ "token": "...", "expiresIn": 3600 }`

### Usar o token nas rotas de posts
Depois de pegar o token, precisa mandar no header:
```
Authorization: Bearer SEU_TOKEN_AQUI
```

Exemplo para listar posts:
```
GET http://localhost:8080/posts
Authorization: Bearer SEU_TOKEN_AQUI
```

Exemplo para criar post:
```
POST http://localhost:8080/posts
Authorization: Bearer SEU_TOKEN_AQUI
Content-Type: application/json

{
  "titulo": "Meu primeiro post",
  "conteudo": "Hello IFgram!"
}
```

## Endpoints principais

- GET /health → só pra testar se está rodando  
- POST /auth/signup → cria usuário e já retorna token  
- POST /auth/login → faz login e retorna token  
- GET /posts → lista posts (precisa de token)  
- POST /posts → cria post (precisa de token)  
- GET /posts/{id} → pega um post específico  
- PUT /posts/{id} → atualiza tudo do post  
- PATCH /posts/{id} → atualiza só parte do post  
- DELETE /posts/{id} → deleta um post  
