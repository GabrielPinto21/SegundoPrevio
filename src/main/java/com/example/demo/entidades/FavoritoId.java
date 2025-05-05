package com.example.demo.entidades;

import java.io.Serializable;
import java.util.Objects;

public class FavoritoId implements Serializable {

    private Integer manga;
    private Integer usuario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoritoId that = (FavoritoId) o;
        return Objects.equals(manga, that.manga) && Objects.equals(usuario, that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manga, usuario);
    }
}