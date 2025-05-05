package com.example.demo.service;

import com.example.demo.dtos.MangaDTO;
import com.example.demo.entidades.Manga;
import com.example.demo.entidades.Pais;
import com.example.demo.entidades.Tipo;
import com.example.demo.repository.FavoritoRepository;
import com.example.demo.repository.MangaRepository;
import com.example.demo.repository.PaisRepository;
import com.example.demo.repository.TipoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MangaService {

	@Autowired
    private MangaRepository mangaRepository;
	@Autowired
    private PaisRepository paisRepository;
	@Autowired
    private TipoRepository tipoRepository;
	@Autowired
	private FavoritoRepository favoritoRepository;
	
	public List<Manga> listMangas() {
		return mangaRepository.findAll();
	}

    // Obtener un manga por ID
    public MangaDTO getMangaById(Integer id) {
        Manga manga = mangaRepository.getReferenceById(id);
                
        return new MangaDTO(manga);
    }

    // Añadir un nuevo manga
    public MangaDTO addManga(MangaDTO dto) {
        Manga manga = new Manga();
        fillMangaFromDTO(manga, dto);
        Manga saved = mangaRepository.save(manga);
        return new MangaDTO(saved);
    }

    // Actualizar manga existente
    public void updateManga(Integer id, MangaDTO dto) {
        Manga manga = mangaRepository.getReferenceById(id);
        fillMangaFromDTO(manga, dto);
        mangaRepository.save(manga);
    }

    // Eliminar manga por ID
    @Transactional
    public void deleteManga(Integer id) {
    	Manga m = mangaRepository.getReferenceById(id);
    	favoritoRepository.deleteByManga(m);
        mangaRepository.deleteById(id);
    }

    // Utilidad: Rellenar entidad desde el DTO
    private void fillMangaFromDTO(Manga manga, MangaDTO dto) {
        manga.setNombre(dto.getNombre());
        manga.setFechaLanzamiento(dto.getFechaLanzamiento());
        manga.setTemporadas(dto.getTemporadas());
        manga.setAnime(dto.getAnime() != null && dto.getAnime() ? 1 : 0);
        manga.setJuego(dto.getJuego() != null && dto.getJuego() ? 1 : 0);
        manga.setPelicula(dto.getPelicula() != null && dto.getPelicula() ? 1 : 0);

        // Establecer país si está presente en DTO
        if (dto.getPais() != null) {
            Optional<Pais> paisOpt = paisRepository.findByNombre(dto.getPais());
            paisOpt.ifPresentOrElse(
                manga::setPais,
                () -> { throw new RuntimeException("País no encontrado: " + dto.getPais()); }
            );
        } else {
            manga.setPais(null);
        }

        // Establecer tipo si está presente en DTO
        if (dto.getTipo() != null) {
            Optional<Tipo> tipoOpt = tipoRepository.findByNombre(dto.getTipo());
            tipoOpt.ifPresentOrElse(
                manga::setTipo,
                () -> { throw new RuntimeException("Tipo no encontrado: " + dto.getTipo()); }
            );
        } else {
            manga.setTipo(null);
        }
    }
}
