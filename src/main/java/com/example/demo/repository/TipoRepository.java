package com.example.demo.repository;

import com.example.demo.entidades.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoRepository extends JpaRepository<Tipo, Integer> {
    Optional<Tipo> findByNombre(String nombre);
}