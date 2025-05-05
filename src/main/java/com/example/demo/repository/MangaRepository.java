package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Manga;

import java.util.List;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Integer> {
    List<Manga> findByNombreContainingIgnoreCase(String nombre);
}

