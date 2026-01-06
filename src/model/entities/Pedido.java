package model.entities;

import model.enums.StatusPedido;

import java.util.Date;
import java.util.Objects;

public class Pedido {
    private Integer id;
    private Produto produto;
    private Cliente cliente;
    private Integer quantidade;
    private Double valorTotal;
    private Date dataPedido;
    private StatusPedido status;

    public Pedido(){

    }
    public Pedido(Integer id, Produto produto, Cliente cliente, Integer quantidade, Double valorTotal, Date dataPedido, StatusPedido status) {
        this.id = id;
        this.produto = produto;
        this.cliente = cliente;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.dataPedido = dataPedido;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", produto=" + produto +
                ", cliente=" + cliente +
                ", quantidade=" + quantidade +
                ", valorTotal=" + valorTotal +
                ", dataPedido=" + dataPedido +
                ", status=" + status +
                '}';
    }
}
