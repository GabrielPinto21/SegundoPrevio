package com.example.demo.dtos;


import com.example.demo.entidades.Favorito;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FavoritoDTO {
    private MangaDTO manga;
    private UsuarioDTO usuario;

    public FavoritoDTO(Favorito favorito) {
        this.manga = new MangaDTO(favorito.getManga());
        this.usuario = new UsuarioDTO(favorito.getUsuario());
    }
}