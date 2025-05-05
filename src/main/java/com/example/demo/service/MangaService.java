package com.example.demo.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Manga;
import com.example.demo.repository.MangaRepository;

@Service
public class MangaService {
	@Autowired
	private MangaRepository mRepo;
	
	public List<Manga> listMangas() {
		return mRepo.findAll();
	}

	public Manga getMangaById(int id) {
		return mRepo.getReferenceById(id);
	}

	public Manga crearManga(Manga manga) {
        return mRepo.save(manga);
    }

    public Manga actualizarManga(int id, Manga manga) {
        Optional<Manga> existingManga = mRepo.findById(id);
        if (existingManga.isPresent()) {
            Manga updatedManga = existingManga.get();
            updatedManga.setNombre(manga.getNombre());
            updatedManga.setFechaLanzamiento(manga.getFechaLanzamiento());
            updatedManga.setTemporadas(manga.getTemporadas());
            updatedManga.setAnime(manga.getAnime());
            updatedManga.setJuego(manga.getJuego());
            updatedManga.setPelicula(manga.getPelicula());
            updatedManga.setPais(manga.getPais());
            updatedManga.setTipo(manga.getTipo());
            return mRepo.save(updatedManga);
        }
        return null; // Or throw an exception if preferred
    }

    public void eliminarManga(int id) {
        if (mRepo.existsById(id)) {
            mRepo.deleteById(id);
        }
    }
}