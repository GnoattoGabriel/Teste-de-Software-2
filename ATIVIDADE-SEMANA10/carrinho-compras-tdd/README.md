# Carrinho de Compras — TDD

Projeto Maven com Java 17 e JUnit 5. Diferente dos outros projetos, este não
vinha com testes prontos: `CarrinhoTest.java` foi escrito do zero, um teste por
requisito, antes de implementar `Carrinho`.

## Executar

```bash
mvn clean verify
```

Relatório de cobertura: `target/site/jacoco/index.html`.

## Ciclo TDD por requisito

| Requisito | Teste que especifica | RED (antes de implementar) | GREEN (implementação mínima) | REFACTOR |
|---|---|---|---|---|
| 1 — carrinho vazio | `carrinhoNovoDeveTerTotalZero` | `UnsupportedOperationException` | lista `ArrayList` vazia; subtotal 0 | — |
| 2 — adicionar um produto | `deveAdicionarProduto` | `UnsupportedOperationException` | `produtos.add(produto)` | — |
| 3 — adicionar vários | `deveAdicionarVariosProdutos` | `UnsupportedOperationException` | laço que soma `getPreco()` | extração da variável `subtotal` |
| 4 — remover produto | `deveRemoverProduto` | `UnsupportedOperationException` | `removeIf` usando o nome do produto | — |
| 5 — preço negativo | `naoDevePermitirProdutoComPrecoNegativo` | sem validação | `IllegalArgumentException` no construtor de `Produto` | — |
| 5b — preço zero | `devePermitirProdutoComPrecoZero` | sem validação | comparação `< 0` (zero é válido) | — |
| 6 — desconto de 10% | `deveAplicarDescontoDeDezPorCentoAcimaDeQuinhentos`, `naoDeveAplicarDescontoAbaixoDeQuinhentos`, `deveAplicarDescontoExatamenteEmQuinhentos`, `deveAplicarDescontoAcimaDeQuinhentos` | falha: total sem desconto | `subtotal >= 500` → `subtotal * 0,9` | constantes `LIMITE_DESCONTO` e `TAXA_DESCONTO` |

### Decisão sobre o requisito 5

O enunciado pede para definir o comportamento diante de preço negativo e sugere
discussão com o professor. Adotou-se **lançar `IllegalArgumentException`** já no
construtor de `Produto`, pois assim nenhum produto inválido pode existir. O teste
usa `assertThrows`, e a decisão está registrada no `README.md` da atividade.

### Fronteiras do desconto

| Subtotal | Total esperado |
|---|---|
| R$ 499,99 | R$ 499,99 (sem desconto) |
| R$ 500,00 | R$ 450,00 (10%) |
| R$ 500,01 | R$ 450,009 (10%) |
| R$ 600,00 | R$ 540,00 (10%) |

## Resultado

- **RED:** `mvn test` → 10 testes, 1 falha e 9 erros (`evidencias/01-red.txt`).
- **GREEN:** `mvn clean verify` → 10 testes, 0 falhas (`evidencias/02-green.txt`).
- **Cobertura JaCoCo:** 21/21 linhas, 6/6 branches, 8/8 métodos — 100%.
