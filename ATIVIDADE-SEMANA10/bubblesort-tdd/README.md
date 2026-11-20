# Bubble Sort — TDD

Projeto Maven com Java 17 e JUnit 5. A implementação de `ordenar()` foi
propositalmente omitida no material original e foi construída orientada pelos
testes.

## Executar

```bash
mvn clean verify
```

Relatório de cobertura: `target/site/jacoco/index.html`.

## Ciclo TDD por requisito

| Requisito | Teste que especifica | RED (antes de implementar) | GREEN (implementação mínima) | REFACTOR |
|---|---|---|---|---|
| 1 — ordenar vetor desordenado | `BubbleSortTest.deveOrdenarVetorDesordenado` | falha: `[5,3,1,4,2]` permanecia desordenado | laços aninhados com troca de elementos adjacentes | — |
| 2 — manter vetor já ordenado | `BubbleSortTest.deveManterVetorJaOrdenado` | passava com o vetor inalterado; mantido como garantia | variável `trocou` e saída antecipada (`break`) | variável local `boolean trocou` |
| 3 — valores repetidos | `BubbleSortTest.deveOrdenarVetorComValoresRepetidos` | falha: repetidos não eram ordenados | comparação estrita `>` preserva todas as ocorrências | — |
| Casos de borda | `BubbleSortCasosBordaTest`: vetor vazio, um elemento e valores negativos | 1 falha (valores negativos) | limite `length - 1` evita acesso fora do vetor | campo `numeros` passou a `final` |

Os três testes originais de `BubbleSortTest` foram mantidos **exatamente como
entregues**. `BubbleSortCasosBordaTest.java` foi acrescentado para explicitar
casos que o enunciado permite mas os testes fornecidos não cobriam.

## Resultado

- **RED:** `mvn test` → 6 testes, 3 falhas (`evidencias/01-red.txt`).
- **GREEN:** `mvn clean verify` → 6 testes, 0 falhas (`evidencias/02-green.txt`).
- **Cobertura JaCoCo:** 15/15 linhas, 8/8 branches, 3/3 métodos — 100%.
