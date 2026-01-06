package application;

import model.dao.ClienteDao;
import model.dao.DaoFactory;
import model.dao.ProdutoDao;
import model.entities.Cliente;
import model.entities.Produto;

import java.util.Date;

public class Program {
    public static void main(String[] args) {
        ClienteDao clienteDao = DaoFactory.createClienteDao();
        ProdutoDao produtoDao = DaoFactory.createProdutoDao();

        Cliente clienteInserir = new Cliente(null, "Otniel Marques", "otniww131ewlm1353@gmail.com",
                "99933999", "995399999", new Date());
/*
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


*/

        System.out.println(" ======== INSERE PRODUTO ========");
        Produto produto = new Produto(null,"Macarrão","Produto alimenticio", 7.99, 100, new Date());
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

    }
}
