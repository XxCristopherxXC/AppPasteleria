package com.pasteleria.pedidos.controller;

import com.pasteleria.pedidos.model.CarritoItem;
import com.pasteleria.pedidos.repository.CarritoItemRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RestController
@RequestMapping("/carrito")
@CrossOrigin(origins = "*")
public class CarritoController {

    private final CarritoItemRepository repo;

    public CarritoController(CarritoItemRepository repo) {
        this.repo = repo;
    }

    // Obtener ítems del carrito
    @GetMapping("/{idCliente}")
    public List<CarritoItem> obtenerCarrito(@PathVariable Long idCliente) {
        return repo.findByIdCliente(idCliente);
    }

    // Agregar o actualizar ítem del carrito
    @PostMapping
    public CarritoItem agregarItem(@RequestBody CarritoItem item) {
        return repo.save(item);
    }

    // Vaciar carrito completo
    @Transactional  // ✅ IMPORTANTE: Agregar esta anotación
    @DeleteMapping("/{idCliente}")
    public void vaciarCarrito(@PathVariable Long idCliente) {
        repo.deleteByIdCliente(idCliente);
    }

    // ELIMINAR SOLO 1 ITEM DEL CARRITO
    @DeleteMapping("/item/{idItem}")
    public void eliminarItem(@PathVariable Long idItem) {
        repo.deleteById(idItem);
    }
}