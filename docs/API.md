# NucleAR Quiz - API

API REST do **NucleAR Quiz**, projeto do Challenge FIAP 2026 (CTMSP / Amazul), turma 2TDSPR.

## Resumo da solução

A tecnologia nuclear ainda é muito associada a acidentes e armas. O NucleAR Quiz é uma plataforma de quizzes
com competições temáticas que mostra o lado pacífico e útil dessa tecnologia: medicina, agricultura, indústria
e geração de energia.

Esta API cuida das duas partes principais:

- **Perguntas:** banco de questões educativas, cada uma com uma explicação que ensina o usuário quando ele erra.
- **Competições:** o administrador cria torneios com tema, prazo, quantidade de questões (4 a 10) e prêmios do
  1º, 2º e 3º lugares. O tempo total é calculado automaticamente (30 segundos por questão).

## Tecnologias

- Java 17+
- Spring Boot 3.5
- Spring Data JPA
- Bean Validation
- H2 (banco em memória)
- springdoc-openapi (Swagger)
- Maven

## Estrutura do projeto

```
src/main/java/br/com/fiap/nuclearquiz
├── config        -> configuração do Swagger
├── controller    -> endpoints REST
├── dto           -> objetos de request e response
├── exception     -> tratamento global de erros
├── model         -> entidades JPA e enums
├── repository    -> acesso ao banco (Spring Data JPA)
└── service       -> regras de negócio
```

## Como rodar localmente

Pré-requisitos: Java 17 ou superior e Maven.

```bash
git clone <url-do-repositorio>
cd nuclear-quiz
mvn spring-boot:run
```

A aplicação sobe em `http://localhost:8080`. Ao iniciar, o banco já é populado com perguntas e competições de
exemplo (arquivo `src/main/resources/data.sql`).

Para rodar os testes:

```bash
mvn test
```

## Como acessar o Swagger

Com a aplicação rodando, abra no navegador:

```
http://localhost:8080/swagger-ui/index.html
```

Console do banco H2 (opcional): `http://localhost:8080/h2-console`
(JDBC URL `jdbc:h2:mem:nuclearquiz`, usuário `sa`, senha vazia).

## Principais endpoints

| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/api/perguntas` | Cria uma pergunta |
| GET | `/api/perguntas` | Lista perguntas (filtro opcional `?tema=MEDICINA`) |
| GET | `/api/perguntas/{id}` | Busca por id |
| GET | `/api/perguntas/busca?termo=...` | Busca pelo texto do enunciado |
| PUT | `/api/perguntas/{id}` | Atualiza uma pergunta |
| DELETE | `/api/perguntas/{id}` | Remove uma pergunta |
| POST | `/api/competicoes` | Cria uma competição |
| GET | `/api/competicoes` | Lista todas as competições |
| GET | `/api/competicoes/em-andamento` | Lista as competições ainda abertas |
| GET | `/api/competicoes/{id}` | Busca por id |
| GET | `/api/competicoes/busca?termo=...` | Busca pelo nome |
| PUT | `/api/competicoes/{id}` | Atualiza uma competição |
| DELETE | `/api/competicoes/{id}` | Remove uma competição |

Temas válidos: `MEDICINA`, `AGRICULTURA`, `INDUSTRIA`, `GERACAO_DE_ENERGIA`.
Respostas corretas válidas: `A`, `B`, `C`, `D`.

## Exemplos de payload para testar

Cole os JSONs abaixo no Swagger (botão **Try it out**) ou em qualquer cliente HTTP. Os ids `1`, `2`... das
URLs existem por causa dos dados iniciais (`data.sql`).

### Perguntas

**POST `/api/perguntas`** - cria uma pergunta (retorna `201 Created`)

```json
{
  "enunciado": "Qual é o papel da água em uma usina nuclear do tipo PWR?",
  "alternativaA": "Resfriar o reator e transportar o calor para gerar vapor",
  "alternativaB": "Servir de combustível para a fissão",
  "alternativaC": "Produzir radiação para os exames médicos",
  "alternativaD": "Armazenar a eletricidade gerada",
  "respostaCorreta": "A",
  "explicacao": "Nesse tipo de reator a água circula pelo núcleo, retira o calor da fissão e ajuda a produzir o vapor que move as turbinas. Ela não é combustível.",
  "tema": "GERACAO_DE_ENERGIA"
}
```

**PUT `/api/perguntas/1`** - atualiza a pergunta de id 1 (todos os campos são enviados novamente)

```json
{
  "enunciado": "Na medicina nuclear, para que servem os radiofármacos?",
  "alternativaA": "Para gerar eletricidade dentro dos hospitais",
  "alternativaB": "Para diagnosticar e tratar doenças, como em exames de imagem e terapias",
  "alternativaC": "Para substituir as vacinas",
  "alternativaD": "Para esterilizar o ar das salas de cirurgia",
  "respostaCorreta": "B",
  "explicacao": "Radiofármacos são medicamentos com pequenas quantidades de material radioativo, usados em doses controladas para ver o funcionamento dos órgãos e tratar doenças.",
  "tema": "MEDICINA"
}
```

**GET e DELETE** (sem corpo):

| Ação | Requisição |
|------|------------|
| Listar todas | `GET /api/perguntas` |
| Filtrar por tema | `GET /api/perguntas?tema=AGRICULTURA` |
| Buscar por id | `GET /api/perguntas/1` |
| Buscar por texto | `GET /api/perguntas/busca?termo=radioterapia` |
| Remover | `DELETE /api/perguntas/1` (retorna `204 No Content`) |

### Competições

**POST `/api/competicoes`** - cria uma competição (retorna `201 Created`)

A `dataEncerramento` precisa estar no futuro e `quantidadeQuestoes` deve ficar entre 4 e 10.
O campo `tempoTotalSegundos` não é enviado: a API calcula (30 segundos por questão).

```json
{
  "nome": "Indústria Nuclear em Ação",
  "descricao": "Descubra como a tecnologia nuclear ajuda a inspecionar peças e a controlar a qualidade na indústria.",
  "tema": "INDUSTRIA",
  "dataEncerramento": "2026-12-31T23:59:00",
  "quantidadeQuestoes": 8,
  "premioPrimeiroLugar": "Boné Amazul",
  "premioSegundoLugar": "Bottom Amazul",
  "premioTerceiroLugar": "Adesivo Amazul"
}
```

**PUT `/api/competicoes/1`** - atualiza a competição de id 1

```json
{
  "nome": "Desafio Nuclear na Medicina - Edição Especial",
  "descricao": "Descubra como a tecnologia nuclear ajuda a diagnosticar e tratar doenças.",
  "tema": "MEDICINA",
  "dataEncerramento": "2027-01-31T18:00:00",
  "quantidadeQuestoes": 10,
  "premioPrimeiroLugar": "Boné Amazul",
  "premioSegundoLugar": "Bottom Amazul",
  "premioTerceiroLugar": "Adesivo Amazul"
}
```

**GET e DELETE** (sem corpo):

| Ação | Requisição |
|------|------------|
| Listar todas | `GET /api/competicoes` |
| Em andamento | `GET /api/competicoes/em-andamento` |
| Buscar por id | `GET /api/competicoes/1` |
| Buscar por texto | `GET /api/competicoes/busca?termo=nuclear` |
| Remover | `DELETE /api/competicoes/1` (retorna `204 No Content`) |

### Testando os erros

**POST `/api/competicoes`** com dados inválidos (retorna `400 Bad Request` com a lista de campos):

```json
{
  "nome": "",
  "descricao": "Teste",
  "tema": "INDUSTRIA",
  "dataEncerramento": "2020-01-01T10:00:00",
  "quantidadeQuestoes": 2,
  "premioPrimeiroLugar": "Boné Amazul",
  "premioSegundoLugar": "Bottom Amazul",
  "premioTerceiroLugar": "Adesivo Amazul"
}
```

**GET `/api/perguntas/9999`** retorna `404 Not Found` (pergunta inexistente).

**POST `/api/perguntas`** com `"tema": "XYZ"` retorna `400 Bad Request` (valor de tema inválido).

## Exemplo de erro padronizado

```json
{
  "timestamp": "2026-10-23T20:30:00",
  "status": 400,
  "erro": "Bad Request",
  "mensagem": "Há campos inválidos na requisição",
  "caminho": "/api/competicoes",
  "campos": [
    { "campo": "nome", "mensagem": "O nome é obrigatório" }
  ]
}
```