package com.example.demo.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "pais_id_seq")
    @SequenceGenerator(name = "pais_id_seq", sequenceName = "pais_id_seq", allocationSize = 1)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "pais")
    private List<Manga> mangas;
}

