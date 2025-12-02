package com.pasteleria.clientes.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pasteleria.clientes.model.Cliente;
import com.pasteleria.clientes.repository.ClienteRepository;

import java.util.Map;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "http://localhost:5173")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Map<String, Object> payload) {
        try {
            // Normalizar rut: aceptar número o string con formato "12345678-9" y guardar solo los dígitos antes del guion
            Integer rut = null;
            Object rawRut = payload.get("rut");
            if (rawRut != null) {
                if (rawRut instanceof Number) {
                    rut = ((Number) rawRut).intValue();
                } else if (rawRut instanceof String) {
                    String s = ((String) rawRut).trim();
                    String beforeHyphen = s.contains("-") ? s.split("-")[0] : s;
                    String digits = beforeHyphen.replaceAll("\\D", "");
                    if (!digits.isEmpty()) {
                        rut = Integer.parseInt(digits);
                    }
                }
            }

            // fallback: buscar campos alternativos 'id' o 'clienteId'
            if (isNull(rut)) {
                Object alt = payload.get("id");
                if (alt == null) alt = payload.get("clienteId");
                if (alt instanceof Number) rut = ((Number) alt).intValue();
                else if (alt instanceof String) {
                    String s = ((String) alt).trim();
                    String beforeHyphen = s.contains("-") ? s.split("-")[0] : s;
                    String digits = beforeHyphen.replaceAll("\\D", "");
                    if (!digits.isEmpty()) rut = Integer.parseInt(digits);
                }
            }

            if (isNull(rut)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo 'rut' (o 'id') es requerido");
            }

            // Construir entidad Cliente
            Cliente c = new Cliente();
            c.setRut(rut);
            Object nombre = payload.get("nombre");
            Object apellido = payload.get("apellido");
            Object telefono = payload.get("telefono");
            Object direccion = payload.get("direccion");
            Object correo = payload.get("correo") != null ? payload.get("correo") : payload.get("correoElectronico");

            c.setNombre(nombre == null ? "" : nombre.toString());
            c.setApellido(apellido == null ? "" : apellido.toString());
            c.setTelefono(telefono == null ? null : telefono.toString());
            c.setDireccion(direccion == null ? null : direccion.toString());
            c.setCorreoElectronico(correo == null ? null : correo.toString());

            Cliente saved = clienteRepository.save(c);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar cliente: " + ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtener(@PathVariable Integer id) {
        return clienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
