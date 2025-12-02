package com.pasteleria.pedidos.service;

import com.pasteleria.pedidos.model.Pedido;
import com.pasteleria.pedidos.model.DetallePedido;
import com.pasteleria.pedidos.dto.ItemPedidoRequest;
import com.pasteleria.pedidos.dto.PedidoRequest;
import com.pasteleria.pedidos.repository.PedidoRepository;
import com.pasteleria.pedidos.repository.DetallePedidoRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedido crearDesdeRequest(PedidoRequest request) {

        Pedido pedido = new Pedido();
        pedido.setIdCliente(request.getIdCliente());
        pedido.setClienteNombre(request.getNombreCliente());
        pedido.setClienteApellido(request.getApellidoCliente());

        LocalDate fechaPedido = LocalDate.parse(request.getFecha());
        pedido.setFecha(fechaPedido);

        List<DetallePedido> detalles = new ArrayList<>();

        for (ItemPedidoRequest ir : request.getItems()) {
            DetallePedido det = new DetallePedido();
            det.setCodigoProducto(ir.getCodigoProducto());
            det.setCantidad(ir.getCantidad());
            det.setPrecioUnitario(ir.getPrecioUnitario());
            det.setPedido(pedido);
            detalles.add(det);
        }

        pedido.setDetalles(detalles);

        if (request.getTotal() != null)
            pedido.setTotal(request.getTotal());

        return pedidoRepository.save(pedido);
    }

    public Pedido findById(long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }
}
