package com.pasteleria.clientes;

import com.pasteleria.clientes.model.Cliente;
import com.pasteleria.clientes.repository.ClienteRepository;
import com.pasteleria.clientes.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Cliente cliente1 = new Cliente();
        cliente1.setRut(1);
        cliente1.setNombre("Juan");
        cliente1.setApellido("Pérez");
        cliente1.setTelefono("123456789");

        Cliente cliente2 = new Cliente();
        cliente2.setRut(2);
        cliente2.setNombre("Ana");
        cliente2.setApellido("Gómez");
        cliente2.setTelefono("987654321");
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

        List<Cliente> clientes = clienteService.findAll();
        assertEquals(2, clientes.size());
        assertEquals("Juan", clientes.get(0).getNombre());
    }

    @Test
    void testFindById() {
        Cliente cliente = new Cliente();
        cliente.setRut(1);
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        cliente.setTelefono("123456789");
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        Cliente found = clienteService.findById(1);
        assertNotNull(found);
        assertEquals("Juan", found.getNombre());
    }

    @Test
    void testSave() {
        Cliente cliente = new Cliente();
        cliente.setRut(1);
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        cliente.setTelefono("123456789");
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente saved = clienteService.save(cliente);
        assertNotNull(saved);
        assertEquals("Juan", saved.getNombre());
    }

    @Test
    void testDelete() {
        doNothing().when(clienteRepository).deleteById(1);
        clienteService.delete(1);
        verify(clienteRepository, times(1)).deleteById(1);
    }
}
