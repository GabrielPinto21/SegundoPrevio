package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entidades.Favorito;
import com.example.demo.entidades.FavoritoId;
import com.example.demo.entidades.Manga;
import com.example.demo.entidades.Usuario;

public interface FavoritoRepository extends JpaRepository<Favorito, FavoritoId> {
	void deleteByManga(Manga manga);
	List<Favorito> findByUsuario(Usuario usuario);
	Favorito findByUsuarioAndManga(Usuario usuario, Manga manga);

}