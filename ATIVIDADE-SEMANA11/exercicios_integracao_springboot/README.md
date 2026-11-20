# Exercícios — Testes de integração com Spring Boot e JUnit 5

Projeto-base da SEMANA11 com os cinco casos de teste preenchidos. Tecnologias:
Java 17, Maven, Spring Boot 3.3.13, Spring Data JPA, H2, JUnit 5, MockMvc e
JaCoCo.

## Casos de teste

### Exercício 1 — `ProdutoServiceIntegrationTest` (`@SpringBootTest`, `@Transactional`)

| Caso | Método | O que é verificado |
|---|---|---|
| CT01 | `deveCadastrarERecuperarProduto` | `cadastrar("Teclado", 150.0)` gera ID; `flush()` + `entityManager.clear()`; `findById()` retorna nome e preço persistentes |
| CT02 | `naoDevePersistirProdutoComNomeEmBranco` | `assertThrows(IllegalArgumentException)` para nome `" "`; `repository.count()` não muda |
| CT03 | `deveAtualizarPrecoPersistido` | `atualizarPreco(id, 95.0)`; após limpar o cache JPA, `findById()` retorna `95.0` |

### Exercício 2 — `ProdutoControllerIntegrationTest` (`@SpringBootTest`, `@AutoConfigureMockMvc`, `@Transactional`)

| Caso | Método | O que é verificado |
|---|---|---|
| CT04 | `deveCadastrarProdutoPelaApi` | `POST /produtos` com `{"nome":"Mouse","preco":80.0}` → HTTP 200, `$.id` numérico, `$.nome` = Mouse, `$.preco` = 80.0 |
| CT05 | `deveBuscarProdutoPelaApi` | POST para cadastrar, extração do ID com `ObjectMapper`, `GET /produtos/{id}` → HTTP 200 e JSON correspondente |

## Decisões de implementação

- **Sem mocks:** `ProdutoService`, `ProdutoRepository`, H2 e o contexto Spring
  são reais em todos os casos.
- **Leitura efetiva do banco:** `repository.flush()` envia o SQL pendente e
  `entityManager.clear()` descarta o cache de primeiro nível antes de
  `findById()`, evitando que a asserção seja satisfeita por um objeto em
  memória.
- **CT02:** a contagem de registros é capturada antes da operação inválida e
  comparada depois, garantindo que a validação impediu a persistência.
- **CT05:** o ID é extraído do corpo do POST com `JsonNode`, sem valores fixos.
- **Isolamento:** `@Transactional` reverte as alterações ao final de cada
  teste; o banco H2 é em memória com `ddl-auto=create-drop`.

## Evidências

Pasta `evidencias/`:

- `01-todos-todo.txt` — execução original, com os métodos ainda marcados como
  `TODO` (5 testes passavam sem asserções).
- `02-mutacao-red.txt` — validação de nome removida temporariamente:
  CT02 falhou, demonstrando que o teste detecta o defeito. Código restaurado.
- `03-green.txt` — execução final: 5 testes, 0 falhas, BUILD SUCCESS.

## Executar

```bash
mvn clean verify
```

Use JDK 17 (`JAVA_HOME` apontando para o JDK 17). Relatório de cobertura em
`target/site/jacoco/index.html`: 100% das linhas das classes da aplicação.
