package br.edu.exemplo;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private static final double LIMITE_DESCONTO = 500.0;
    private static final double TAXA_DESCONTO = 0.10;

    private final List<Produto> produtos = new ArrayList<>();

    public void adicionar(Produto produto) {
        produtos.add(produto);
    }

    public void remover(String nome) {
        produtos.removeIf(produto -> produto.getNome().equals(nome));
    }

    public double getTotal() {
        double subtotal = 0;
        for (Produto produto : produtos) {
            subtotal += produto.getPreco();
        }

        if (subtotal >= LIMITE_DESCONTO) {
            return subtotal * (1 - TAXA_DESCONTO);
        }
        return subtotal;
    }
}
