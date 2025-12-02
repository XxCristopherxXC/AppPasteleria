package com.pasteleria.pedidos.repository;

import com.pasteleria.pedidos.model.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> { }
