package model.dao;

import model.entities.Cliente;

import java.util.List;

public interface ClienteDao {
    // CRUD Básico
    void inserir(Cliente cliente);
    void atualizar(Cliente cliente);
    void deletar(Integer id);
    Cliente buscarPorId(Integer id);
    List<Cliente> buscarTodos();

    // Consultas Específicas
    Cliente buscarPorEmail(String email);
    Cliente buscarPorCpf(String cpf);

    // Validações
    boolean emailExiste(String email);
    boolean cpfExiste(String cpf);
}
