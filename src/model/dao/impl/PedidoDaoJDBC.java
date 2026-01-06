package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.PedidoDao;
import model.entities.Cliente;
import model.entities.Pedido;
import model.entities.Produto;
import model.enums.StatusPedido;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoDaoJDBC implements PedidoDao {

    Connection conn;

    public PedidoDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void inserir(Pedido pedido) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO pedidos (id_cliente, id_produto, quantidade, valor_total, status) " +
                                            "VALUES (?, ?, ?, ?, ?)");
            st.setInt(1, pedido.getCliente().getId());
            st.setInt(2, pedido.getProduto().getId());
            st.setInt(3, pedido.getQuantidade());
            st.setDouble(4, pedido.getValorTotal());
            st.setString(5, pedido.getStatus().toString());

            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void atualizar(Pedido pedido) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("update pedidos " +
                                            "set id_cliente = ?, id_produto = ?, quantidade = ?, " +
                                            "valor_total = ?, data_pedido = ?, status = ? " +
                                            "where id_pedido = ?");
            st.setInt(1, pedido.getCliente().getId());
            st.setInt(2, pedido.getProduto().getId());
            st.setInt(3, pedido.getQuantidade());
            st.setDouble(4, pedido.getValorTotal());
            st.setDate(5, new java.sql.Date(pedido.getDataPedido().getTime()));
            st.setString(6,pedido.getStatus().toString());
            st.setInt(7, pedido.getId());

            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void deletar(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("delete from pedidos " +
                                            "where id_pedido = ?");
            st.setInt(1, id);
            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Pedido buscarPorId(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("select * from pedidos" +
                                        " where id_pedido = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                Pedido pedido = instanciarPedido(rs);
                return pedido;
            }
            return null;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Pedido> buscarTodos() {
        PreparedStatement st = null;
        List<Pedido> list = new ArrayList<>();

        try {
            st = conn.prepareStatement("select * from pedidos");
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                Pedido pedido = new Pedido();
                Cliente clientePedido = buscarClientePedido(rs);
                Produto produtoPedido = buscarProdutoPedido(rs);
                StatusPedido status = buscarStatusPedido(rs);

                pedido.setId(rs.getInt("id_pedido"));
                pedido.setCliente(clientePedido);
                pedido.setProduto(produtoPedido);
                pedido.setQuantidade(rs.getInt("quantidade"));
                pedido.setValorTotal(rs.getDouble("valor_total"));
                pedido.setDataPedido(rs.getDate("data_pedido"));
                pedido.setStatus(status);

                list.add(pedido);
            }
            return list;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Pedido> buscarPorCliente(Integer idCliente) {
        PreparedStatement st = null;
        List<Pedido> list = new ArrayList<>();
        try {
            st = conn.prepareStatement("select * from pedidos " +
                                        "where id_cliente = ?");
            st.setInt(1, idCliente);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Pedido pedido = instanciarPedido(rs);
                list.add(pedido);
            }
            return list;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Pedido> buscarPorProduto(Integer idProduto) {
        PreparedStatement st = null;
        List<Pedido> list = new ArrayList<>();
        try {
            st = conn.prepareStatement("select * from pedidos " +
                                        "where id_produto = ?");
            st.setInt(1, idProduto);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                Pedido pedido = instanciarPedido(rs);
                list.add(pedido);
            }
            return list;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Pedido> buscarPorStatus(StatusPedido status) {
        PreparedStatement st = null;
        List<Pedido> list = new ArrayList<>();

        try {
            st = conn.prepareStatement("select * from pedidos " +
                                        "where status = ?");
            st.setString(1, status.toString());
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                Pedido pedido = instanciarPedido(rs);
                list.add(pedido);
            }
            return list;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Pedido> buscarPorPeriodo(java.sql.Date dataInicio, java.sql.Date dataFim) {
        PreparedStatement st = null;
        List<Pedido> list = new ArrayList<>();
        try {
            st = conn.prepareStatement("select * from pedidos " +
                    "where data_pedido between ? and ?");
            st.setDate(1, dataInicio);
            st.setDate(2, dataFim);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                Pedido pedido = instanciarPedido(rs);
                list.add(pedido);
            }
            return list;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }


    @Override
    public void atualizarStatus(Integer id, StatusPedido status) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("update pedidos " +
                                        "set status = ? " +
                                        "where id_pedido = ?");
            st.setString(1, status.toString());
            st.setInt(2, id);
            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    public Pedido instanciarPedido(ResultSet rs){
        Pedido pedido = new Pedido();
        Cliente clientePedido = buscarClientePedido(rs);
        Produto produtoPedido = buscarProdutoPedido(rs);
        StatusPedido statusPedido = buscarStatusPedido(rs);
        try {
            pedido.setId(rs.getInt("id_pedido"));
            pedido.setCliente(clientePedido);
            pedido.setProduto(produtoPedido);
            pedido.setQuantidade(rs.getInt("quantidade"));
            pedido.setValorTotal(rs.getDouble("valor_total"));
            pedido.setDataPedido(rs.getDate("data_pedido"));
            pedido.setStatus(statusPedido);

            return pedido;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    public Cliente buscarClientePedido(ResultSet rs){
        Cliente cliente = new Cliente();
        Integer idCliente;
        try {
            idCliente = rs.getInt("id_cliente");

            PreparedStatement st = conn.prepareStatement("select * from clientes where id_cliente" +
                    " = ?");
            st.setInt(1, idCliente);
            ResultSet rsCliente = st.executeQuery();

            if (rsCliente.next()){
                cliente.setId(rsCliente.getInt("id_cliente"));
                cliente.setNome(rsCliente.getString("nome"));
                cliente.setEmail(rsCliente.getString("email"));
                cliente.setTelefone(rsCliente.getString("telefone"));
                cliente.setCpf(rsCliente.getString("cpf"));
                cliente.setDataCadastro(rsCliente.getDate("data_cadastro"));
                return cliente;
            }
            return null;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    public Produto buscarProdutoPedido(ResultSet rs){
        Produto produto = new Produto();

        try {
            Integer idProduto = rs.getInt("id_produto");
            PreparedStatement st = conn.prepareStatement("select * from produtos " +
                                                        "where id_produto = ?");
            st.setInt(1, idProduto);

            ResultSet rsProduto = st.executeQuery();

            if (rsProduto.next()){
                produto.setId(rsProduto.getInt("id_produto"));
                produto.setNome(rsProduto.getString("nome"));
                produto.setDescricao(rsProduto.getString("descricao"));
                produto.setPreco(rsProduto.getDouble("preco"));
                produto.setEstoque(rsProduto.getInt("estoque"));
                produto.setDataCadastro(rsProduto.getDate("data_cadastro"));

                return produto;
            }
            return null;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    public StatusPedido buscarStatusPedido(ResultSet rs){
        try {
            String status = rs.getString("status");

            return switch (status) {
                case "PENDENTE" -> StatusPedido.PENDENTE;
                case "CONFIRMADO" -> StatusPedido.CONFIRMADO;
                case "ENVIADO" -> StatusPedido.ENVIADO;
                case "ENTREGUE" -> StatusPedido.ENTREGUE;
                case "CANCELADO" -> StatusPedido.CANCELADO;
                default -> StatusPedido.PENDENTE;
            };

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }
}
