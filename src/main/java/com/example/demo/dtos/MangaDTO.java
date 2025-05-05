package com.example.demo.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.example.demo.entidades.Manga;

@Data
@NoArgsConstructor
public class MangaDTO {
    private Integer id;
    private String nombre;
    private LocalDate fechaLanzamiento;
    private Integer temporadas;
    private String pais; // solo nombre del país
    private String tipo; // solo nombre del tipo
    private Boolean anime;
    private Boolean juego;
    private Boolean pelicula;

    public MangaDTO(Manga manga) {
        this.id = manga.getId();
        this.nombre = manga.getNombre();
        this.fechaLanzamiento = manga.getFechaLanzamiento();
        this.temporadas = manga.getTemporadas();
        this.pais = manga.getPais() != null ? manga.getPais().getNombre() : null;
        this.tipo = manga.getTipo() != null ? manga.getTipo().getNombre() : null;
        this.anime = manga.getAnime() != null && manga.getAnime() == 1;
        this.juego = manga.getJuego() != null && manga.getJuego() == 1;
        this.pelicula = manga.getPelicula() != null && manga.getPelicula() == 1;
    }
}