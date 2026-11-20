package br.edu.exemplo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Casos de borda que complementam os testes fornecidos em ProdutoTest.
 * O requisito 6 exige impedir entradas "negativas ou iguais a zero" e o
 * requisito 5 exige impedir retiradas maiores que o estoque; estes testes
 * explicitam tambem o comportamento para zero e valores negativos na retirada.
 */
class ProdutoCasosBordaTest {

    @Test
    void deveArmazenarNomeDoProduto() {
        Produto produto = new Produto("Teclado");

        assertEquals("Teclado", produto.getNome());
    }

    @Test
    void naoDeveAdicionarQuantidadeZero() {
        Produto produto = new Produto("Teclado");

        produto.adicionarEstoque(0);

        assertEquals(0, produto.getQuantidade());
    }

    @Test
    void naoDeveRetirarQuantidadeZero() {
        Produto produto = new Produto("Teclado");
        produto.adicionarEstoque(10);

        produto.retirarEstoque(0);

        assertEquals(10, produto.getQuantidade());
    }

    @Test
    void naoDeveRetirarQuantidadeNegativa() {
        Produto produto = new Produto("Teclado");
        produto.adicionarEstoque(10);

        produto.retirarEstoque(-5);

        assertEquals(10, produto.getQuantidade());
    }

    @Test
    void devePermitirRetirarTodaAQuantidadeDisponivel() {
        Produto produto = new Produto("Teclado");
        produto.adicionarEstoque(10);

        produto.retirarEstoque(10);

        assertEquals(0, produto.getQuantidade());
    }
}
