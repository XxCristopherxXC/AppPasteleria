package com.pasteleria.pedidos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    private String codigoProducto;
    private Integer cantidad;
    private Integer precioUnitario;

    public Long getIdDetalle() { return idDetalle; }
    public Pedido getPedido() { return pedido; }
    public String getCodigoProducto() { return codigoProducto; }
    public Integer getCantidad() { return cantidad; }
    public Integer getPrecioUnitario() { return precioUnitario; }

    public void setPedido(Pedido pedido) { this.pedido = pedido; }
    public void setCodigoProducto(String codigoProducto) { this.codigoProducto = codigoProducto; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public void setPrecioUnitario(Integer precioUnitario) { this.precioUnitario = precioUnitario; }
}

