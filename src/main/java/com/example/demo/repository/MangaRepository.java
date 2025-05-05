package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entidades.Manga;

public interface MangaRepository extends JpaRepository<Manga, Integer> {

}