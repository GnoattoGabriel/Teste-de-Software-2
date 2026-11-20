# Conta Bancária — TDD

Projeto Maven com Java 17 e JUnit 5. Os métodos `depositar` e `sacar` vieram sem
implementação e foram desenvolvidos com os testes fornecidos como guia.

## Executar

```bash
mvn clean verify
```

Relatório de cobertura: `target/site/jacoco/index.html`.

## Ciclo TDD por comportamento

| Comportamento | Teste que especifica | RED (antes de implementar) | GREEN (implementação mínima) | REFACTOR |
|---|---|---|---|---|
| Saldo inicial | `ContaBancariaTest` + `ContaBancariaCasosBordaTest.contaDeveIniciarComSaldoZero` | já atendido pelo construtor | `saldo = 0` no construtor | — |
| Depositar | `deveDepositarValorNaConta` | falha: saldo continuava 0 | `saldo += valor` | — |
| Acumular depósitos | `deveAcumularDepositos` | falha | mesma implementação, sem código extra | — |
| Sacar | `deveSacarValorDaConta` | falha: saldo não diminuía | `saldo -= valor` | — |
| Saque maior que o saldo | `naoDevePermitirSaqueMaiorQueSaldo` | falha: saldo ficaria negativo | guarda `if (valor > saldo) return;` | — |
| Saque exatamente igual ao saldo | `devePermitirSaqueExatamenteIgualAoSaldo` | coerente com a guarda `>` | saldo resultante 0 | — |
| Saque sem depósito prévio | `naoDeveAlterarSaldoComSaqueSemDepositoPrevio` | coerente com a guarda | saldo permanece 0 | — |

Os quatro testes originais de `ContaBancariaTest` foram mantidos **exatamente
como entregues**. `ContaBancariaCasosBordaTest.java` foi acrescentado para
tornar explícitos os casos de fronteira.

## Resultado

- **RED:** `mvn test` → 7 testes, 4 falhas (`evidencias/01-red.txt`).
- **GREEN:** `mvn clean verify` → 7 testes, 0 falhas (`evidencias/02-green.txt`).
- **Cobertura JaCoCo:** 10/10 linhas, 2/2 branches, 4/4 métodos — 100%.
