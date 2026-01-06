package model.enums;

public enum StatusPedido {
    PENDENTE("PENDENTE"),
    CONFIRMADO("CONFIRMADO"),
    ENVIADO("ENVIADO"),
    ENTREGUE("ENTREGUE"),
    CANCELADO("CANCELADO");

    StatusPedido(String status) {
    }
}
