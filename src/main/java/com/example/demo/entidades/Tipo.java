package com.example.demo.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "tipo_id_seq")
    @SequenceGenerator(name = "tipo_id_seq", sequenceName = "tipo_id_seq", allocationSize = 1)
    private Long id;

    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "tipo")
    private List<Manga> mangas;
}



