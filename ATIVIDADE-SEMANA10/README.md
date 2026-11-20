# ATIVIDADE-SEMANA10 — TDD nos quatro projetos

Atividade da disciplina de Teste de Software: aplicar **TDD** nos quatro
projetos disponíveis em `aulas/SEMANA10` do repositório
`JoaoChoma/teste_software_2026`, até a implementação e cobertura total das
funcionalidades descritas nos requisitos.

## O que significa "cobertura total" nesta entrega

Os testes cobrem **100% das funcionalidades pedidas**, incluindo os casos de
fronteira citados nos enunciados. Assim, os testes funcionam como uma
especificação executável: a implementação pode ser removida e recriada apenas
com base neles. Como verificação adicional, todos os projetos também atingiram
**100% de cobertura de linhas, instruções, branches e métodos** no JaCoCo.

## Estrutura

```
ATIVIDADE-SEMANA10/
├── README.md                    (este arquivo)
├── bubblesort-tdd/
├── carrinho-compras-tdd/
├── conta-bancaria-tdd-maven/
└── estoque-tdd/
```

Cada projeto mantém os arquivos originais (`README.md`, `REQUISITOS.md`,
`pom.xml`, fontes e testes fornecidos). Os testes originais **não foram
alterados**; quando necessário, casos adicionais foram escritos em arquivos de
teste novos (`*CasosBordaTest.java`).

## Como executar

Requer JDK 17 ou superior e Maven 3.9 ou superior. Validado com JDK 25.0.4.1 e
Maven 3.9.9.

```bash
cd ATIVIDADE-SEMANA10/bubblesort-tdd
mvn clean verify
```

Repita para `carrinho-compras-tdd`, `conta-bancaria-tdd-maven` e `estoque-tdd`.
O relatório de cobertura de cada projeto fica em
`target/site/jacoco/index.html`.

## Resultados consolidados

| Projeto | Testes RED (antes) | Testes GREEN (depois) | Cobertura JaCoCo |
|---|---|---|---|
| `bubblesort-tdd` | 6 testes, 3 falhas | 6/6 OK | 100% linhas, 100% branches, 100% métodos |
| `carrinho-compras-tdd` | 10 testes, 1 falha e 9 erros | 10/10 OK | 100% linhas, 100% branches, 100% métodos |
| `conta-bancaria-tdd-maven` | 7 testes, 4 falhas | 7/7 OK | 100% linhas, 100% branches, 100% métodos |
| `estoque-tdd` | 11 testes, 6 falhas | 11/11 OK | 100% linhas, 100% branches, 100% métodos |
| **Total** | **34 testes, 23 falhas/erros** | **34/34 OK** | **100% em todas as métricas** |

## Evidências do ciclo RED → GREEN → REFACTOR

Cada projeto contém uma pasta `evidencias/` com as saídas **reais** do Maven:

- `01-red.txt` — execução de `mvn test` com os testes escritos e a
  implementação ainda ausente/incompleta;
- `02-green.txt` — execução de `mvn clean verify` após a implementação mínima
  de cada comportamento.

O detalhamento requisito a requisito (teste → falha → implementação → refactor)
está no `README.md` de cada projeto.

## Decisões de implementação

- **Preço negativo (carrinho, requisito 5):** o enunciado pede para definir o
  comportamento. Adotou-se lançar `IllegalArgumentException` no construtor de
  `Produto`, impedindo a existência de um produto inválido. Teste com
  `assertThrows` em `CarrinhoTest`. Preço zero é permitido.
- **Remoção no carrinho:** `remover(String nome)`, conforme o exemplo do
  enunciado ("Remover: Mouse").
- **Desconto:** 10% quando o subtotal é **maior ou igual** a R$ 500,00,
  explicitado com testes nas fronteiras 499,99 / 500,00 / 500,01.
- **Conta bancária:** saque maior que o saldo é ignorado, sem alterar o saldo.
- **Estoque:** entradas com quantidade menor ou igual a zero e retiradas com
  quantidade menor ou igual a zero ou maior que o disponível são ignoradas.
- **Bubble sort:** ordenação em lugar, com saída antecipada quando uma passagem
  não realiza trocas.

## Observações

- Os arquivos `target/` não são versionados (ver `.gitignore` na raiz).
- O plugin JaCoCo 0.8.16 foi adicionado aos quatro `pom.xml` apenas para medir e
  publicar a cobertura; nenhuma regra de teste fornecida foi alterada.
