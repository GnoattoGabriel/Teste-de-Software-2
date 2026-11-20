# ATIVIDADE-SEMANA11 — Testes de integração com Spring Boot

Atividade da disciplina de Teste de Software: completar os testes de
integração do projeto-base `exercicios_integracao_springboot`, disponível em
`aulas/SEMANA11` do repositório `JoaoChoma/teste_software_2026`.

**Regra da atividade:** não utilizar mocks para substituir `ProdutoRepository`
ou `ProdutoService`. Os testes devem exercitar o contexto Spring real e o banco
H2 em memória.

## Estrutura

```
ATIVIDADE-SEMANA11/
└── exercicios_integracao_springboot/
    ├── README.md
    ├── pom.xml
    ├── src/main/...
    └── src/test/java/br/edu/exemplo/
        ├── ProdutoServiceIntegrationTest.java   (CT01, CT02, CT03)
        └── ProdutoControllerIntegrationTest.java (CT04, CT05)
```

## Casos implementados

| Caso | Teste | Integração exercitada | Verificação |
|---|---|---|---|
| CT01 | `ProdutoServiceIntegrationTest.deveCadastrarERecuperarProduto` | Service → Repository → H2 | ID gerado; após `flush()` + `entityManager.clear()`, `findById()` retorna nome e preço corretos |
| CT02 | `naoDevePersistirProdutoComNomeEmBranco` | validação no Service + contagem no Repository | `IllegalArgumentException`; `repository.count()` inalterado |
| CT03 (desafio) | `deveAtualizarPrecoPersistido` | atualização → persistência → releitura do H2 | preço `95.0` após nova leitura do banco |
| CT04 | `ProdutoControllerIntegrationTest.deveCadastrarProdutoPelaApi` | `POST /produtos` → Controller → Service → Repository → H2 | HTTP 200; JSON com ID numérico, nome `Mouse`, preço `80.0` |
| CT05 | `deveBuscarProdutoPelaApi` | `POST` + `GET /produtos/{id}` | HTTP 200; JSON da consulta corresponde ao produto cadastrado |

## Como executar

Use JDK 17 (o projeto declara `java.version=17`) e Maven 3.9+:

```bash
cd ATIVIDADE-SEMANA11/exercicios_integracao_springboot
mvn clean verify
```

Validado com **JDK 17.0.x e Maven 3.9.9**. Relatório JaCoCo:
`target/site/jacoco/index.html`.

## Resultado e evidências

- **5/5 testes passando**; **100% de cobertura de linhas** nas classes da
  aplicação (`Aplicacao`, `Produto`, `ProdutoService`, `ProdutoController`,
  `ProdutoRequest`).
- `evidencias/01-todos-todo.txt` — suíte original: os 5 métodos continham apenas
  `TODO` e passavam sem verificar nada.
- `evidencias/02-mutacao-red.txt` — com a validação de nome removida
  temporariamente, CT02 falhou com `Expected java.lang.IllegalArgumentException
  to be thrown, but nothing was thrown`. O código foi restaurado em seguida.
- `evidencias/03-green.txt` — execução final com `mvn clean verify`: 5 testes,
  0 falhas.

A mutação temporária tem finalidade didática: demonstra que os testes
preenchidos realmente detectam um defeito, já que o projeto-base vinha com a
aplicação funcional e não havia uma etapa RED natural.

## Respostas às perguntas de discussão

1. **Por que `findById()` é uma evidência diferente do objeto retornado pelo
   serviço?** O objeto devolvido por `cadastrar()` pode ser apenas a instância
   em memória que foi passada ao JPA. Depois de `flush()` e
   `entityManager.clear()`, o cache de primeiro nível é descartado e
   `findById()` executa uma consulta real ao banco. Isso prova que a
   persistência funcionou, não apenas que o método retornou um objeto.

2. **O que `@Transactional` faz ao final de cada teste?** O Spring inicia uma
   transação antes do teste e a reverte ao terminar (rollback). Assim, os
   registros criados por um teste não vazam para os outros, garantindo
   isolamento e independência de ordem. Como MockMvc executa na mesma thread,
   as requisições participam dessa transação.

3. **Quais componentes são exercitados nos testes HTTP?** Controller, Service,
   Repository e H2, todos reais. Não há `@MockBean` nem substituição de
   dependências.

4. **Por que MockMvc não inicia um servidor HTTP real?** Ele simula o ciclo de
   requisição/resposta do Spring MVC diretamente sobre o contexto da aplicação,
   sem abrir porta nem fazer chamada de rede. É mais rápido e mantém o teste
   na mesma transação.

5. **Diferença para um teste unitário do controller com dependências
   simuladas?** No teste unitário, o Service seria um mock e apenas a lógica do
   controller seria avaliada. Aqui, qualquer falha no Service, no Repository ou
   no mapeamento JPA aparece no resultado da requisição — é uma evidência de
   integração de verdade.

## Isolamento

- H2 em memória com `ddl-auto=create-drop` e `DB_CLOSE_DELAY=-1`.
- `@Transactional` com rollback automático em todos os métodos de teste.
- A suíte foi executada várias vezes, inclusive com ordem alterada, sem
  dependência entre os casos.
