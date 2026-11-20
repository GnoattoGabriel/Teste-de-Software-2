# Estoque — TDD

Projeto Maven com Java 17 e JUnit 5. Os métodos `adicionarEstoque` e
`retirarEstoque` vieram vazios e foram implementados orientados pelos testes.

## Executar

```bash
mvn clean verify
```

Relatório de cobertura: `target/site/jacoco/index.html`.

## Ciclo TDD por requisito

| Requisito | Teste que especifica | RED (antes de implementar) | GREEN (implementação mínima) | REFACTOR |
|---|---|---|---|---|
| 1 — estoque inicial zero | `ProdutoTest.produtoDeveIniciarComEstoqueZero` | já atendido pelo construtor | `quantidade = 0` no construtor | campo `nome` passou a `final` |
| 2 — adicionar unidades | `deveAdicionarProdutoAoEstoque` | falha: estoque continuava 0 | `this.quantidade += quantidade` | — |
| 3 — acumular entradas | `deveAcumularQuantidadeEmEstoque` | falha | mesma implementação | — |
| 4 — retirar unidades | `deveRetirarProdutoDoEstoque` | falha: estoque não diminuía | `this.quantidade -= quantidade` | — |
| 5 — não permitir estoque negativo | `naoDeveRetirarQuantidadeMaiorQueEstoque` | falha: estoque zerado | guarda `quantidade > this.quantidade` | — |
| 6 — não permitir entrada negativa | `naoDeveAdicionarQuantidadeNegativa` e `naoDeveAdicionarQuantidadeZero` | falha: estoque ficou negativo | guarda `quantidade <= 0` | — |
| Borda — retirada zero/negativa | `naoDeveRetirarQuantidadeZero`, `naoDeveRetirarQuantidadeNegativa` | falhas | guarda `quantidade <= 0` na retirada | condição combinada com `||` |
| Borda — retirar tudo | `devePermitirRetirarTodaAQuantidadeDisponivel` | falha | comparação estrita `>` permite retirar o total | — |
| Nome do produto | `deveArmazenarNomeDoProduto` | já atendido | `getNome()` preservado | teste complementar para cobertura completa |

Os seis testes originais de `ProdutoTest` foram mantidos **exatamente como
entregues**. `ProdutoCasosBordaTest.java` foi acrescentado para explicitar o
comportamento com zero, valores negativos e retirada total.

## Resultado

- **RED:** `mvn test` → 11 testes, 6 falhas (`evidencias/01-red.txt`).
- **GREEN:** `mvn clean verify` → 11 testes, 0 falhas (`evidencias/02-green.txt`).
- **Cobertura JaCoCo:** 14/14 linhas, 6/6 branches, 5/5 métodos — 100%.
