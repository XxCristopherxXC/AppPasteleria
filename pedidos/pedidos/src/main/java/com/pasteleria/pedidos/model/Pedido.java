package com.pasteleria.pedidos.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    private int total;
    private Long idCliente;
    private String clienteNombre;
    private String clienteApellido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detalles = new ArrayList<>();

    public Long getIdPedido() { return idPedido; }
    public LocalDate getFecha() { return fecha; }
    public int getTotal() { return total; }
    public Long getIdCliente() { return idCliente; }
    public String getClienteNombre() { return clienteNombre; }
    public String getClienteApellido() { return clienteApellido; }
    public List<DetallePedido> getDetalles() { return detalles; }

    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setTotal(int total) { this.total = total; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }
    public void setClienteNombre(String n) { this.clienteNombre = n; }
    public void setClienteApellido(String a) { this.clienteApellido = a; }
    public void setDetalles(List<DetallePedido> detalles) { this.detalles = detalles; }
}

