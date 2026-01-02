package model.dao;

import model.entities.Pedido;
import model.enums.StatusPedido;

import java.time.LocalDate;
import java.util.List;

public interface PedidoDao {
    void inserir(Pedido pedido);
    void atualizar(Pedido pedido);
    void deletar(Integer id);
    Pedido buscarPorId(Integer id);
    List<Pedido> buscarTodos();

    // Consultas por Cliente
    List<Pedido> buscarPorCliente(Integer idCliente);

    // Consultas por Produto
    List<Pedido> buscarPorProduto(Integer idProduto);

    // Consultas por Status
    List<Pedido> buscarPorStatus(StatusPedido status);

    // Consultas por Data
    List<Pedido> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim);

    // Atualização de Status
    void atualizarStatus(Integer id, StatusPedido status);

}
