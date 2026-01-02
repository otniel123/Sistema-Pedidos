package application;

import model.dao.ClienteDao;
import model.dao.DaoFactory;
import model.entities.Cliente;

import java.util.Date;

public class Program {
    public static void main(String[] args) {
        ClienteDao clienteDao = DaoFactory.createClienteDao();

        Cliente clienteInserir = new Cliente(null, "Otniel Marques", "otniww131ewlm1353@gmail.com",
                "99933999", "995399999", new Date());

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

        System.out.println("\n\n\n ======== VERIFICA SE EMAIL EXISTE ========");
        System.out.println(clienteDao.emailExiste("joao.silva@email.com"));

        System.out.println("\n\n\n ======== VERIFICA SE CPF EXISTE ========");
        System.out.println(clienteDao.cpfExiste("3456789012"));

        System.out.println("\n\n\n ======== BUSCA CLIENTE DE CPF ESPECIFICO ========");
        System.out.println(clienteDao.buscarPorCpf("34567890123"));
    }
}
