package com.pasteleria.clientes;

import com.pasteleria.clientes.model.Cliente;
import com.pasteleria.clientes.repository.ClienteRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        // Generar clientes de prueba
        for (int i = 0; i < 20; i++) {
            Cliente cliente = new Cliente();
            // Asignar un rut único para cada cliente (Integer)
            cliente.setRut(faker.number().numberBetween(10000000, 99999999));
            cliente.setNombre(faker.name().firstName());
            cliente.setApellido(faker.name().lastName());
            // telefono en la entidad es String -> convertir el número a cadena
            cliente.setTelefono(String.valueOf(faker.number().numberBetween(100000000, 999999999)));
            clienteRepository.save(cliente);
        }
        System.out.println("Datos de prueba de clientes generados exitosamente.");
    }
}
