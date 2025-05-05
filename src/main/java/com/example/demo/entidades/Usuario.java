package com.example.demo.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "usuario_id_seq")
    @SequenceGenerator(name = "usuario_id_seq", sequenceName = "usuario_id_seq", allocationSize = 1)
    private Long id;

    private String username;

    private String nombre;

    @Column(unique = true)
    private String email;

    private String password;

    @ManyToMany
    @JoinTable(
        name = "favorito",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "manga_id")
    )
    private Set<Manga> favoritos;
}
