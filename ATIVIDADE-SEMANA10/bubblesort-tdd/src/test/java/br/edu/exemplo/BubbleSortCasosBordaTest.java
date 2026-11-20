package br.edu.exemplo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Casos de borda que complementam os testes fornecidos em BubbleSortTest.
 * Foram escritos antes da implementacao de ordenar(), no ciclo RED -> GREEN.
 */
class BubbleSortCasosBordaTest {

    @Test
    void deveManterVetorVazio() {
        BubbleSort bubbleSort = new BubbleSort(new int[0]);

        bubbleSort.ordenar();

        assertArrayEquals(new int[0], bubbleSort.getNumeros());
    }

    @Test
    void deveManterVetorComUmUnicoElemento() {
        BubbleSort bubbleSort = new BubbleSort(new int[]{7});

        bubbleSort.ordenar();

        assertArrayEquals(new int[]{7}, bubbleSort.getNumeros());
    }

    @Test
    void deveOrdenarValoresNegativos() {
        BubbleSort bubbleSort = new BubbleSort(new int[]{0, -1, 5, -3});

        bubbleSort.ordenar();

        assertArrayEquals(new int[]{-3, -1, 0, 5}, bubbleSort.getNumeros());
    }
}
