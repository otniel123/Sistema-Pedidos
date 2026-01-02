package model.dao;

import model.entities.Produto;

import java.util.List;

public interface ProdutoDao {
    // CRUD Básico
    void inserir(Produto produto);
    void atualizar(Produto produto);
    void deletar(Integer id);
    Produto buscarPorId(Integer id);
    List<Produto> buscarTodos();

    // Consultas Específicas
    List<Produto> buscarPorNome(String nome);
    List<Produto> buscarDisponiveis(); // estoque > 0

    // Controle de Estoque
    void atualizarEstoque(Integer id, Integer novaQuantidade);
    void diminuirEstoque(Integer id, Integer quantidade);
    void aumentarEstoque(Integer id, Integer quantidade);
    Integer consultarEstoque(Integer id);
}
