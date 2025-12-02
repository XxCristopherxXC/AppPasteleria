package com.pasteleria.clientes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @Column(nullable = false, unique = true)
    private Integer rut;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = true)
    private String telefono;

    @Column(name = "correo_electronico", nullable = true)
    private String correoElectronico;

    @Column(nullable = true)
    private String direccion;

}
