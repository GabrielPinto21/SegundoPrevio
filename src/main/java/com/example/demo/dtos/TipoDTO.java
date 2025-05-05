package com.example.demo.dtos;

import com.example.demo.entidades.Tipo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TipoDTO {
    private Integer id;
    private String nombre;

    public TipoDTO(Tipo tipo) {
        this.id = tipo.getId();
        this.nombre = tipo.getNombre();
    }
}
