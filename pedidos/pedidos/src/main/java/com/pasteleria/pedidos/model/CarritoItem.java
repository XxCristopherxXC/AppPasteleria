package com.pasteleria.pedidos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "carrito_item")
public class CarritoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idCliente;
    private Long idProducto;
    private int cantidad;
    private String tamano;

    public CarritoItem() {}

    public Long getId() { return id; }
    public Long getIdCliente() { return idCliente; }
    public Long getIdProducto() { return idProducto; }
    public int getCantidad() { return cantidad; }
    public String getTamano() { return tamano; }

    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }
    public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public void setTamano(String tamano) { this.tamano = tamano; }
}
