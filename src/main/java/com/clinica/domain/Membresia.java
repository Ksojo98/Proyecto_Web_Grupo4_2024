package com.clinica.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "membresias")
public class Membresia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMembresia;

    private String nombre;
    private String descripcion;
    private Double precio;

    @Column(name = "evaluacion_inicial")
    private boolean evaluacionInicial;

    @Column(name = "fisioterapia_semanal")
    private boolean fisioterapiaSemanal;

    @Column(name = "sala_ejercicios")
    private boolean salaEjercicios;

    @Column(name = "terapia_manual")
    private boolean terapiaManual;

    @Column(name = "equipos_especializados")
    private boolean equiposEspecializados;

    @Column(name = "consultas_nutricion")
    private boolean consultasNutricion;

    @Column(name = "masajes_terapeuticos")
    private boolean masajesTerapeuticos;

    @Column(name = "fisioterapia_domicilio")
    private boolean fisioterapiaDomicilio;
}
