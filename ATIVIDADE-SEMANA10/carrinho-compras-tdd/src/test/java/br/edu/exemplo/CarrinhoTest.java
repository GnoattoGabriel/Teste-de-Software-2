package br.edu.exemplo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Testes escritos antes da implementacao de Carrinho, um por requisito de
 * REQUISITOS.md. Estes testes sao a especificacao executavel do carrinho.
 */
class CarrinhoTest {

    private static final double DELTA = 0.001;

    @Test
    void carrinhoNovoDeveTerTotalZero() {
        Carrinho carrinho = new Carrinho();

        assertEquals(0, carrinho.getTotal(), DELTA);
    }

    @Test
    void deveAdicionarProduto() {
        Carrinho carrinho = new Carrinho();

        carrinho.adicionar(new Produto("Teclado", 100));

        assertEquals(100, carrinho.getTotal(), DELTA);
    }

    @Test
    void deveAdicionarVariosProdutos() {
        Carrinho carrinho = new Carrinho();

        carrinho.adicionar(new Produto("Teclado", 100));
        carrinho.adicionar(new Produto("Mouse", 50));

        assertEquals(150, carrinho.getTotal(), DELTA);
    }

    @Test
    void deveRemoverProduto() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(new Produto("Teclado", 100));
        carrinho.adicionar(new Produto("Mouse", 50));

        carrinho.remover("Mouse");

        assertEquals(100, carrinho.getTotal(), DELTA);
    }

    @Test
    void naoDevePermitirProdutoComPrecoNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Produto("Teclado", -100));
    }

    @Test
    void devePermitirProdutoComPrecoZero() {
        Carrinho carrinho = new Carrinho();

        carrinho.adicionar(new Produto("Brinde", 0));

        assertEquals(0, carrinho.getTotal(), DELTA);
    }

    @Test
    void deveAplicarDescontoDeDezPorCentoAcimaDeQuinhentos() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(new Produto("Notebook", 600));

        assertEquals(540, carrinho.getTotal(), DELTA);
    }

    @Test
    void naoDeveAplicarDescontoAbaixoDeQuinhentos() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(new Produto("Teclado", 499.99));

        assertEquals(499.99, carrinho.getTotal(), DELTA);
    }

    @Test
    void deveAplicarDescontoExatamenteEmQuinhentos() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(new Produto("Teclado", 500));

        assertEquals(450, carrinho.getTotal(), DELTA);
    }

    @Test
    void deveAplicarDescontoAcimaDeQuinhentos() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(new Produto("Teclado", 500.01));

        assertEquals(450.009, carrinho.getTotal(), 0.0001);
    }
}
