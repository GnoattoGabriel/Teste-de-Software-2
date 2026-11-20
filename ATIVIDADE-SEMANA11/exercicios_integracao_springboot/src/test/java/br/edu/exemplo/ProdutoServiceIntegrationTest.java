package br.edu.exemplo;

import br.edu.exemplo.model.Produto;
import br.edu.exemplo.repository.ProdutoRepository;
import br.edu.exemplo.service.ProdutoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Exercicio 1 - integracao real entre ProdutoService, ProdutoRepository e H2.
 * Nao ha mocks: o contexto Spring e o banco em memoria sao os reais.
 */
@SpringBootTest
@Transactional
class ProdutoServiceIntegrationTest {

    @Autowired
    ProdutoService service;

    @Autowired
    ProdutoRepository repository;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    void deveCadastrarERecuperarProduto() {
        Produto salvo = service.cadastrar("Teclado", 150.0);

        assertNotNull(salvo.getId());

        // Forca o SQL pendente e limpa o cache de primeiro nivel do JPA,
        // garantindo que findById() leia efetivamente o registro do banco.
        repository.flush();
        entityManager.clear();

        Produto encontrado = repository.findById(salvo.getId()).orElseThrow();
        assertEquals("Teclado", encontrado.getNome());
        assertEquals(150.0, encontrado.getPreco(), 0.001);
    }

    @Test
    void naoDevePersistirProdutoComNomeEmBranco() {
        long quantidadeAntes = repository.count();

        assertThrows(IllegalArgumentException.class,
                () -> service.cadastrar(" ", 80.0));

        assertEquals(quantidadeAntes, repository.count());
    }

    @Test
    void deveAtualizarPrecoPersistido() {
        Produto salvo = service.cadastrar("Mouse", 80.0);
        Long id = salvo.getId();

        Produto atualizado = service.atualizarPreco(id, 95.0);

        assertEquals(95.0, atualizado.getPreco(), 0.001);

        repository.flush();
        entityManager.clear();

        assertEquals(95.0, repository.findById(id).orElseThrow().getPreco(), 0.001);
    }
}
