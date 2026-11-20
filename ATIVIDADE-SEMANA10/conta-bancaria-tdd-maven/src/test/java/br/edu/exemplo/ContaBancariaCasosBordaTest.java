package br.edu.exemplo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Casos de borda que complementam os testes fornecidos em ContaBancariaTest.
 */
class ContaBancariaCasosBordaTest {

    @Test
    void contaDeveIniciarComSaldoZero() {
        ContaBancaria conta = new ContaBancaria();

        assertEquals(0, conta.getSaldo(), 0.001);
    }

    @Test
    void devePermitirSaqueExatamenteIgualAoSaldo() {
        ContaBancaria conta = new ContaBancaria();
        conta.depositar(100);

        conta.sacar(100);

        assertEquals(0, conta.getSaldo(), 0.001);
    }

    @Test
    void naoDeveAlterarSaldoComSaqueSemDepositoPrevio() {
        ContaBancaria conta = new ContaBancaria();

        conta.sacar(50);

        assertEquals(0, conta.getSaldo(), 0.001);
    }
}
