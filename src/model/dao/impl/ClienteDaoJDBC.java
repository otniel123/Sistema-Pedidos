package model.dao.impl;

import db.DbException;
import model.dao.ClienteDao;
import model.entities.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoJDBC implements ClienteDao {

    private Connection conn;

    public ClienteDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void inserir(Cliente cliente) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("Insert into clientes " +
                                        "(nome, email, telefone, cpf, data_cadastro) " +
                                        "values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, cliente.getNome());
            st.setString(2, cliente.getEmail());
            st.setString(3, cliente.getTelefone());
            st.setString(4, cliente.getCpf());
            st.setDate(5, new java.sql.Date(cliente.getDataCadastro().getTime()));

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();

                if (rs.next()){
                    cliente.setId(rs.getInt(1));
                }
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void atualizar(Cliente cliente) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("update clientes" +
                                        " set nome = ?, email = ?, telefone = ?, cpf = ? , " +
                                        "data_cadastro = ? " +
                                        "where id_cliente = ?");
            st.setString(1, cliente.getNome());
            st.setString(2, cliente.getEmail());
            st.setString(3, cliente.getTelefone());
            st.setString(4, cliente.getCpf());
            st.setDate(5, new java.sql.Date(cliente.getDataCadastro().getTime()));
            st.setInt(6, cliente.getId());

            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void deletar(Integer id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("delete from clientes where id_cliente = ?");
            st.setInt(1, id);

            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Cliente buscarPorId(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("Select * from clientes" +
                                        " where id_cliente = ? ");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()){
                Cliente cliente = ClienteDaoJDBC.instanciarCliente(rs);
                return cliente;
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Cliente> buscarTodos() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Cliente> list = new ArrayList<>();

        try {
            st = conn.prepareStatement("select * from clientes");
            rs = st.executeQuery();

            if (!rs.isBeforeFirst()){
                return null;
            }
            while (rs.next()){
                Cliente cliente = ClienteDaoJDBC.instanciarCliente(rs);
                list.add(cliente);
            }
            return list;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Cliente buscarPorEmail(String email) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("Select * from clientes where email = ?");
            st.setString(1, email);

            ResultSet rs = st.executeQuery();

            if (rs.next()){
                Cliente cliente = instanciarCliente(rs);
                return cliente;
            }else {
                return null;
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Cliente buscarPorCpf(String cpf) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("Select * from clientes where cpf = ?");
            st.setString(1, cpf);

            ResultSet rs = st.executeQuery();

            if (rs.next()){
                Cliente cliente = instanciarCliente(rs);
                return cliente;
            }else {
                return null;
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public boolean emailExiste(String email) {

        try {
            PreparedStatement st = conn.prepareStatement("select * from clientes where email = ?");
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            if (rs.next() == false){
                return false;
            }else {
                return true;
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public boolean cpfExiste(String cpf) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("Select * from clientes where cpf = ?");
            st.setString(1, cpf);

            ResultSet rs = st.executeQuery();

            if (rs.next() == false){
                return false;
            }else {
                return true;
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }


    private static Cliente instanciarCliente(ResultSet rs){
        Cliente cliente = new Cliente();

        try {
            cliente.setId(rs.getInt("id_cliente"));
            cliente.setNome(rs.getString("nome"));
            cliente.setEmail(rs.getString("email"));;
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setDataCadastro(rs.getDate("data_cadastro"));

            return cliente;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
