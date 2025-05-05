package com.example.demo.service;

import com.example.demo.dtos.FavoritoDTO;
import com.example.demo.entidades.Favorito;
import com.example.demo.entidades.Manga;
import com.example.demo.entidades.Usuario;
import com.example.demo.repository.FavoritoRepository;
import com.example.demo.repository.MangaRepository;
import com.example.demo.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoritoService {

	@Autowired
    private FavoritoRepository favoritoRepository;
	@Autowired
    private UsuarioRepository usuarioRepository;
	@Autowired
	private MangaRepository mangaRepository;
	
	public void addUserFavorite(String username, Integer mangaId) {
		Usuario usuario = usuarioRepository.findByUsername(username);
		Manga manga = mangaRepository.getReferenceById(mangaId);
		Favorito fav = new Favorito(manga, usuario);
		favoritoRepository.save(fav);
	}

    public List<FavoritoDTO> getFavoritosByUsername(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);

        List<Favorito> favoritos = favoritoRepository.findByUsuario(usuario);

        return favoritos.stream()
                .map(FavoritoDTO::new)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public void deleteFavoritoByUsernameAndMangaId(String username, Integer mangaId) {
        Usuario usuario = usuarioRepository.findByUsername(username);

        Manga manga = mangaRepository.getReferenceById(mangaId);

        Favorito favorito = favoritoRepository.findByUsuarioAndManga(usuario, manga);

        favoritoRepository.delete(favorito);
    }
}