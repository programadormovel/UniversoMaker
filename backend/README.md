# Universo Maker - Backend API

## Tecnologias
- Spring Boot 3.2.0
- Spring Security 6
- Spring Data JPA
- JWT (jsonwebtoken 0.12.3)
- Swagger/OpenAPI 3
- SQL Server 2019

## Configuração

1. **Banco de dados**: Execute o script `../database/create_database.sql`
2. **Configurar conexão**: Ajuste `application.yml` com suas credenciais do SQL Server
3. **Executar**: `mvn spring-boot:run`

## Endpoints Principais

### Autenticação
- `POST /api/auth/login` - Login com email/senha

### Alunos
- `GET /api/alunos` - Listar alunos
- `POST /api/alunos` - Cadastrar aluno
- `GET /api/alunos/{id}` - Buscar aluno
- `PUT /api/alunos/{id}` - Atualizar aluno

### PEI
- `GET /api/pei/aluno/{alunoId}` - PEIs do aluno
- `POST /api/pei` - Criar PEI
- `PUT /api/pei/{id}/aprovar` - Aprovar PEI

## Documentação
Swagger UI: http://localhost:8080/swagger-ui.html

## Usuário Padrão
- Email: admin@universomaker.com
- Senha: admin123