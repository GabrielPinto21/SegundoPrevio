package com.example.demo.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "manga_id_seq")
    @SequenceGenerator(name = "manga_id_seq", sequenceName = "manga_id_seq", allocationSize = 1)
    private int id;

    private String nombre;

    @Column(name = "fecha_lanzamiento")
    private Date fechaLanzamiento;

    private Integer temporadas;
    private Integer anime;
    private Integer juego;
    private Integer pelicula;

    @ManyToOne
    @JoinColumn(name = "pais_id")
    private Pais pais;

    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private Tipo tipo;

    @ManyToMany(mappedBy = "favoritos")
    private Set<Usuario> usuariosFavoritos;
}


