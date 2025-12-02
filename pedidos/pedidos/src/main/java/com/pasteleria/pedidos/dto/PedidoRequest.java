package com.pasteleria.pedidos.dto;

import java.util.List;

public class PedidoRequest {

    private Long idCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private String fecha;
    private Integer total;
    private List<ItemPedidoRequest> items;

    public Long getIdCliente() { return idCliente; }
    public String getNombreCliente() { return nombreCliente; }
    public String getApellidoCliente() { return apellidoCliente; }
    public String getFecha() { return fecha; }
    public Integer getTotal() { return total; }
    public List<ItemPedidoRequest> getItems() { return items; }

    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public void setApellidoCliente(String apellidoCliente) { this.apellidoCliente = apellidoCliente; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public void setTotal(Integer total) { this.total = total; }
    public void setItems(List<ItemPedidoRequest> items) { this.items = items; }
}
