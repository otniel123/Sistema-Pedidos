package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.ProdutoDao;
import model.entities.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProdutoDaoJDBC implements ProdutoDao {

    private Connection conn;

    public ProdutoDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void inserir(Produto produto) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("insert into produtos " +
                                        "(nome, descricao, preco, estoque, data_cadastro)" +
                                        "values(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, produto.getNome());
            st.setString(2, produto.getDescricao());
            st.setDouble(3, produto.getPreco());
            st.setInt(4, produto.getEstoque());
            st.setDate(5, new Date(produto.getDataCadastro().getTime()));

            Integer rowsAffected = st.executeUpdate();

            if (rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();

                if (rs.next()){
                    produto.setId(rs.getInt(1));
                }
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void atualizar(Produto produto) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("update produtos " +
                                        "set nome = ?, descricao = ?, preco = ?, estoque = ?, " +
                                        "data_cadastro = ? " +
                                        "where id_produto = ?");

            st.setString(1, produto.getNome());
            st.setString(2, produto.getDescricao());
            st.setDouble(3, produto.getPreco());
            st.setInt(4, produto.getEstoque());
            st.setDate(5, new java.sql.Date(produto.getDataCadastro().getTime()));
            st.setInt(6, produto.getId());

            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void deletar(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("delete from produtos where id_produto = ?");
            st.setInt(1, id);

            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Produto buscarPorId(Integer id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("select * from produtos" +
                                        " where id_produto = ?");

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()){
                Produto produto = instanciarProduto(rs);
                return produto;
            }else {
                return null;
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Produto> buscarTodos() {
        PreparedStatement st = null;
        List<Produto> list = new ArrayList<>();

        try {
            st = conn.prepareStatement("select * from produtos");
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                Produto produto = instanciarProduto(rs);
                list.add(produto);
            }
            return list;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Produto> buscarPorNome(String nome) {
        PreparedStatement st = null;
        List<Produto> list = new ArrayList<>();

        try {
            st = conn.prepareStatement("Select * from produtos");
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                if (Objects.equals(rs.getString("nome"), nome)){
                    Produto produto = instanciarProduto(rs);

                    list.add(produto);
                }
            }
            return list;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Produto> buscarDisponiveis() {
        PreparedStatement st = null;
        List<Produto> list = new ArrayList<>();
        try {
            st = conn.prepareStatement("select * from produtos");

            ResultSet rs = st.executeQuery();
            while (rs.next()){
                if (rs.getInt("estoque") > 0){
                    Produto produto = instanciarProduto(rs);

                    list.add(produto);
                }
            }
            return list;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void atualizarEstoque(Integer id, Integer novaQuantidade) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("update produtos " +
                                        "set estoque = ? " +
                                        "where id_produto = ?");
            st.setInt(1, novaQuantidade);
            st.setInt(2, id);

            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void diminuirEstoque(Integer id, Integer quantidade) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("update produtos " +
                                        "set estoque = estoque - ? " +
                                        "where id_produto = ?");
            st.setInt(1, quantidade);
            st.setInt(2, id);
            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void aumentarEstoque(Integer id, Integer quantidade) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("update produtos " +
                                        "set estoque = estoque + ? " +
                                        "where id_produto = ?");
            st.setInt(1, quantidade);
            st.setInt(2, id);
            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Integer consultarEstoque(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("select * " +
                                        "from produtos " +
                                        "where id_produto = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                return rs.getInt("estoque");
            }else {
                return null;
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    public Produto instanciarProduto(ResultSet rs){
        try {
            Produto produto = new Produto();
            produto.setId(rs.getInt("id_produto"));
            produto.setNome(rs.getString("nome"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setEstoque(rs.getInt("estoque"));
            produto.setDataCadastro(rs.getDate("data_cadastro"));

            return produto;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }
}
