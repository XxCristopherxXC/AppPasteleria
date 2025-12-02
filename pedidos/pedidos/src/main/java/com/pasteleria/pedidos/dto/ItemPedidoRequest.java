package com.pasteleria.pedidos.dto;

public class ItemPedidoRequest {
    private String codigoProducto;
    private Integer cantidad;
    private Integer precioUnitario;

    public String getCodigoProducto() { return codigoProducto; }
    public Integer getCantidad() { return cantidad; }
    public Integer getPrecioUnitario() { return precioUnitario; }

    public void setCodigoProducto(String codigoProducto) { this.codigoProducto = codigoProducto; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public void setPrecioUnitario(Integer precioUnitario) { this.precioUnitario = precioUnitario; }
}

