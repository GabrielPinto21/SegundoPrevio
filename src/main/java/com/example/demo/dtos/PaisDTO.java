package com.example.demo.dtos;

import com.example.demo.entidades.Pais;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaisDTO {
    private Integer id;
    private String nombre;

    public PaisDTO(Pais pais) {
        this.id = pais.getId();
        this.nombre = pais.getNombre();
    }
}
