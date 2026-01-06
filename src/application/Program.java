package application;

import model.dao.ClienteDao;
import model.dao.DaoFactory;
import model.dao.PedidoDao;
import model.dao.ProdutoDao;
import model.entities.Cliente;
import model.entities.Pedido;
import model.entities.Produto;
import model.enums.StatusPedido;

import java.util.Date;

public class Program {
    public static void main(String[] args) {
        ClienteDao clienteDao = DaoFactory.createClienteDao();
        ProdutoDao produtoDao = DaoFactory.createProdutoDao();
        PedidoDao pedidoDao = DaoFactory.createPedidoDao();

        Cliente clienteInserir = new Cliente(8, "Otniel Marques", "otniww131ewlm1353@gmail.com",
                "99933999", "995399999", new Date());
        Produto produto = new Produto(5,"Macarrão","Produto alimenticio", 7.99, 100, new Date());
        Pedido pedido = new Pedido(11, produto, clienteInserir, 55, 24.90, new Date(),
                StatusPedido.CANCELADO);

        System.out.println(" ======== INSERE UM CLIENTE ========");
        clienteDao.inserir(clienteInserir);
        System.out.println("ID do cliente inserido: " + clienteInserir.getId());
        System.out.println(clienteInserir);


        System.out.println(" ======== ALTERA UM CLIENTE ========");
        clienteInserir.setCpf("99097912");
        clienteInserir.setTelefone("3412355123");
        clienteInserir.setEmail("otniel@gmail.com");
        System.out.println(clienteInserir);

        System.out.println(" ======== DELETA UM CLIENTE ========");
        clienteDao.deletar(5);

        System.out.println(" ======== BUSCA TODOS OS CLIENTES ========");
        System.out.println(clienteDao.buscarTodos());

        System.out.println("\n\n\n ======== BUSCA CLIENTE DE ID ESPECIFICO ========");
        System.out.println(clienteDao.buscarPorId(3));

        System.out.println("\n\n\n ======== VERIFICA SE EMAIL CLIENTE EXISTE ========");
        System.out.println(clienteDao.emailExiste("joao.silva@email.com"));

        System.out.println("\n\n\n ======== VERIFICA SE CPF CLIENTE EXISTE ========");
        System.out.println(clienteDao.cpfExiste("3456789012"));

        System.out.println("\n\n\n ======== BUSCA CLIENTE DE CPF ESPECIFICO ========");
        System.out.println(clienteDao.buscarPorCpf("34567890123"));




        System.out.println(" ======== INSERE PRODUTO ========");
        produtoDao.inserir(produto);
        System.out.println("ID do produto inserido: " + produto.getId());

        System.out.println(" ======== ATUALIZA PRODUTO ========");
        Produto produtoAtualiza = new Produto(1, "Notebook Samsung","Notebook Samsung Inspiron " +
                "15, 8GB RAM, 256GB SSD", 4000.00, 99, new Date());
        produtoDao.atualizar(produtoAtualiza);

        System.out.println(" ======== DELETA PRODUTO ========");
        produtoDao.deletar(7);

        System.out.println(" ======== BUSCA TODOS OS PRODUTOS ========");
        System.out.println(produtoDao.buscarTodos());

        System.out.println("\n\n\n ======== BUSCA PRODUTO DE ID ESPECIFICO ========");
        System.out.println(produtoDao.buscarPorId(5));

        System.out.println("\n\n\n ======== BUSCA PRODUTO DE NOME ESPECIFICO ========");
        System.out.println(produtoDao.buscarPorNome("Mouse Logitech"));

        System.out.println("\n\n\n ======== BUSCA PRODUTOS DISPONIVEIS ========");
        System.out.println(produtoDao.buscarDisponiveis());

        System.out.println("\n\n\n ======== ATUALIZA ESTOQUE ========");
        produtoDao.atualizarEstoque(1, 6);

        System.out.println("\n\n\n ======== DIMINUI ESTOQUE ========");
        produtoDao.diminuirEstoque(1, 2);

        System.out.println("\n\n\n ======== AUMENTA ESTOQUE ========");
        produtoDao.aumentarEstoque(1, 4445);

        System.out.println("\n\n\n ======== CONSULTA ESTOQUE ========");
        System.out.println(produtoDao.consultarEstoque(1));






        System.out.println("\n\n\n ======== INSERE UM PEDIDO ========");
        pedidoDao.inserir(pedido);

        System.out.println("\n\n\n ======== ATUALIZA UM PEDIDO ========");
        pedido.setValorTotal(34112.33);
        pedidoDao.atualizar(pedido);

        System.out.println("\n\n\n ======== DELETA UM PEDIDO ========");
        pedidoDao.deletar(7);

        System.out.println("\n\n\n ======== BUSCA TODOS OS PEDIDOS ========");
        System.out.println(pedidoDao.buscarTodos());

        System.out.println("\n\n\n ======== BUSCA PEDIDO POR ID ========");
        System.out.println(pedidoDao.buscarPorId(4));

        System.out.println("\n\n\n ======== BUSCA PEDIDO POR ID DE CLIENTE ========");
        System.out.println(pedidoDao.buscarPorCliente(3));

        System.out.println("\n\n\n ======== BUSCA PEDIDO POR ID DE PRODUTO ========");
        System.out.println(pedidoDao.buscarPorProduto(2));

        System.out.println("\n\n\n ======== BUSCA PEDIDO POR PERÍODO ========");
        java.sql.Date dataInicio = java.sql.Date.valueOf("2026-01-06");
        java.sql.Date dataFim = java.sql.Date.valueOf("2026-01-07");
        System.out.println(pedidoDao.buscarPorPeriodo(dataInicio, dataFim));

        System.out.println("\n\n\n ======== BUSCA PEDIDO POR STATUS ========");
        System.out.println(pedidoDao.buscarPorStatus(StatusPedido.ENTREGUE));

        System.out.println("\n\n\n ======== ATUALIZA STATUS PEDIDO ========");
        pedidoDao.atualizarStatus(4, StatusPedido.ENTREGUE);
        System.out.println(pedidoDao.buscarPorId(4));
    }
}
