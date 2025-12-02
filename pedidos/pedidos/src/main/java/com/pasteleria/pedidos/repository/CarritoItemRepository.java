package com.pasteleria.pedidos.repository;

import com.pasteleria.pedidos.model.CarritoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface CarritoItemRepository extends JpaRepository<CarritoItem, Long> {

    List<CarritoItem> findByIdCliente(Long idCliente);

    @Transactional  // ✅ Asegúrate de tener esta anotación
    void deleteByIdCliente(Long idCliente);
}